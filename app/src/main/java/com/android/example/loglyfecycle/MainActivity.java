package com.android.example.loglyfecycle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int ACTIVITY_REQUEST_CODE = 0;
    private static final int ACTIVITY_WITH_FRAGMENT_REQUEST_CODE = 1;
    private static final String SHOW_LOG_KEY = "showLog";
    private PreferenceHelper mPeferenceHelper;
    private TextView mTextViewLogMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextViewLogMsg = findViewById(R.id.tv_logMsg);

        mPeferenceHelper = ((MyApplication) getApplication()).getPreferenceHelper();

        if (savedInstanceState != null && savedInstanceState.containsKey(SHOW_LOG_KEY)) {
            mTextViewLogMsg.setText(mPeferenceHelper.getLog());
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        boolean isShowLog = mTextViewLogMsg.getText().length() > 0;
        outState.putBoolean(SHOW_LOG_KEY, isShowLog);
    }

    public void startLoggingActivityLifecycle(View view) {
        mPeferenceHelper.resetLog();
        Intent intent = new Intent(this, LifecycleActivity.class);
        startActivityForResult(intent, ACTIVITY_REQUEST_CODE);
    }

    public void startLoggingFragmentLifecycle(View view) {
        mPeferenceHelper.resetLog();
        Intent intent = new Intent(this, LifecycleActivityWithFragment.class);
        startActivityForResult(intent, ACTIVITY_WITH_FRAGMENT_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ACTIVITY_REQUEST_CODE:
            case ACTIVITY_WITH_FRAGMENT_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    mTextViewLogMsg.setText(mPeferenceHelper.getLog());
                    mTextViewLogMsg.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mTextViewLogMsg.setText(mPeferenceHelper.getLog());
                        }
                    }, 1500);
                }
        }
    }

    public void resetLog(View view) {
        mPeferenceHelper.resetLog();
        mTextViewLogMsg.setText("");
    }
}
