package com.android.example.loglyfecycle;

import android.app.Application;

/**
 * Created by antlap on 04/12/2017.
 */

public class MyApplication extends Application {
    protected PreferenceHelper mPreferenceHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        mPreferenceHelper = new PreferenceHelper(this);
    }

    public PreferenceHelper getPreferenceHelper() {
        return mPreferenceHelper;
    }

}
