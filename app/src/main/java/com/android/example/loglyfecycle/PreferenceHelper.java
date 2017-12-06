package com.android.example.loglyfecycle;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import com.util.Log;

import info.metadude.android.typedpreferences.BooleanPreference;
import info.metadude.android.typedpreferences.StringPreference;

/**
 * Created by antlap on 04/12/2017.
 */

public class PreferenceHelper {
    private static final String LOG_TAG = PreferenceHelper.class.getSimpleName();
    private static final String PREF_KEY_START_LOG = "startLog";
    private static final String MSG_SEPARATOR = ";";
    private static final String PREF_KEY_MSG_LOG = "logs";
    private BooleanPreference mStartLogPreference;
    private StringPreference mLogPreference;


    public PreferenceHelper(Context context) {
        init(PreferenceManager.getDefaultSharedPreferences(context));
    }

    public PreferenceHelper(@NonNull final SharedPreferences preferences) {
        init(preferences);
    }

    private void init(@NonNull final SharedPreferences preferences) {
        mStartLogPreference = new BooleanPreference(preferences, PREF_KEY_START_LOG);
        mLogPreference = new StringPreference(preferences, PREF_KEY_MSG_LOG);
    }

    public boolean isStartLogRequested() {
        return mStartLogPreference.get();
    }

    public void setStartLogRequested(boolean startLogRequested) {
        mStartLogPreference.set(startLogRequested);
    }

    public void addLog(@NonNull final String msg) {
        synchronized (mLogPreference) {
            String logMsg = mLogPreference.isSet() ? mLogPreference.get() + MSG_SEPARATOR + msg : msg;
            mLogPreference.set(logMsg);
            Log.d(LOG_TAG, "Adding callback: " + msg);
        }
    }

    public String getLog() {
        String logMsg = mLogPreference.get().replace(MSG_SEPARATOR, System.getProperty("line.separator"));
        return logMsg;
    }

    public void resetLog() {
        mLogPreference.delete();
        mStartLogPreference.set(true);
    }

}
