package dev.contursif.apobus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

public class TouchSplashActivity extends Activity{


    private static final String TAG_LOG = TouchSplashActivity.class.getName();
    private static final long MIN_WAIT_INTERVAL = 1500L;
    private static final long MAX_WAIT_INTERVAL = 30000L;
    private static final int GO_AHEAD_WHAT = 1;
    private long mStartTime;
    private boolean mIsDone;
    private UiHandler mHandler;

    private static class UiHandler extends Handler {
        private WeakReference<TouchSplashActivity> mActivityRef;

        public UiHandler(final TouchSplashActivity srcActivity) {
            this.mActivityRef = new WeakReference<TouchSplashActivity>(srcActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            final TouchSplashActivity srcActivity = this.mActivityRef.get();
            if (srcActivity == null) {
                Log.d(TAG_LOG, "Reference to NoLeakSplashActivity lost!");
                return;
            }
            switch (msg.what) {
                case GO_AHEAD_WHAT:
                    long elapsedTime = SystemClock.uptimeMillis() - srcActivity.mStartTime;
                    if (elapsedTime >= MIN_WAIT_INTERVAL && !srcActivity.mIsDone) {
                        srcActivity.mIsDone = true;
                        srcActivity.goAhead();
                    }
                    break;
            }
        }
    }

        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_splash);
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
            decorView.setSystemUiVisibility(uiOptions);
            mHandler = new UiHandler(this);
            final ImageView logoImageView = (ImageView) findViewById(R.id.splash_imageview);
            logoImageView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    long elaspedTime = SystemClock.uptimeMillis() - mStartTime;
                    if (elaspedTime >= MIN_WAIT_INTERVAL && !mIsDone) {
                        mIsDone = true;
                        goAhead();
                    } else {
                        Log.d(TAG_LOG, "Too early!");
                    }
                    return false;
                }
            });
        }

        @Override
        protected void onStart() {
            super.onStart();
            // We set the time to consider as the start
            mStartTime = SystemClock.uptimeMillis();
            // We set the time for going ahead automatically
            final Message goAheadMessage = mHandler.obtainMessage(GO_AHEAD_WHAT);
            mHandler.sendMessageAtTime(goAheadMessage, mStartTime + MAX_WAIT_INTERVAL);
            Log.d(TAG_LOG, "Handler message sent!");
        }

        /**
         * Utility method that manages the transition to the FirstAccessActivity
         */
        private void goAhead() {
            // We create the explicit Intent
            final Intent intent = new Intent(this, MainActivity.class);
            // Launch the Intent
            startActivity(intent);
            // We finish the current Activity
            finish();
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
            mHandler.removeCallbacksAndMessages(null);
        }

}