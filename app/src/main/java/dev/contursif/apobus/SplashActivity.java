package dev.contursif.apobus;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG_LOG = SplashActivity.class.getName();

    private static final long MIN_WAIT_INTERVAL = 1500L;
    private static final long MAX_WAIT_INTERFAL = 3000L;
    private static final int GO_AHEAD_WHAT = 1;

    private long mStartTime;
    private boolean mIsDone;
    private Handler mHandler = new Handler(){

        @Override
        public void handleMessage(Message msg){
            switch (msg.what){
                case GO_AHEAD_WHAT:
                    long elapsedTime = SystemClock.uptimeMillis() - mStartTime;
                    if (elapsedTime >= MIN_WAIT_INTERVAL && !mIsDone){
                        mIsDone = true;
                        goAhead();
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);
    }

    public void onStart(){
        super.onStart();
        mStartTime =SystemClock.uptimeMillis();
        final Message goAheadMessage = mHandler.obtainMessage(GO_AHEAD_WHAT);
        mHandler.sendMessageAtTime(goAheadMessage, mStartTime + MAX_WAIT_INTERFAL);
        Log.d(TAG_LOG, "Handler Message Sent!");
    }

    private  void goAhead(){
        final Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}