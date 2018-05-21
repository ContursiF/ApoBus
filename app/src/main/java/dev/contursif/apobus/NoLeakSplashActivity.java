package dev.contursif.apobus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;

import java.lang.ref.WeakReference;


public class NoLeakSplashActivity extends Activity {


    private static final String TAG_LOG = NoLeakSplashActivity.class.getName();
    private static final long MIN_WAIT_INTERVAL = 1500L;
    private static final long MAX_WAIT_INTERVAL = 3000L;
    private static final int GO_AHEAD_WHAT = 1;
    private long mStartTime;
    private boolean mIsDone;
    private UiHandler mHandler;


    private static class UiHandler extends Handler {
        private WeakReference<NoLeakSplashActivity> mActivityRef;

        public UiHandler(final NoLeakSplashActivity srcActivity) {
            this.mActivityRef = new WeakReference<NoLeakSplashActivity>(srcActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            final NoLeakSplashActivity srcActivity = this.mActivityRef.get();
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

    public void onCreate(Bundle saveInstaceState){
        super.onCreate(saveInstaceState);
        setContentView(R.layout.activity_splash);
        View decoderView = getWindow().getDecorView();
        int uiOptons = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decoderView.setSystemUiVisibility(uiOptons);
        mHandler = new UiHandler(this);
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