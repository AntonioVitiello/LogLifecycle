package com.android.example.loglyfecycle;

import android.app.Application;

import com.util.Log;

/**
 * Created by antlap on 04/12/2017.
 */

public class MyApplication extends Application {
    protected PreferenceHelper mPreferenceHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.init(BuildConfig.DEBUG,
                getString(R.string.log_tag),
                getResources().getBoolean(R.bool.log_tag_concat));
        mPreferenceHelper = new PreferenceHelper(this);
    }

    public PreferenceHelper getPreferenceHelper() {
        return mPreferenceHelper;
    }

}
