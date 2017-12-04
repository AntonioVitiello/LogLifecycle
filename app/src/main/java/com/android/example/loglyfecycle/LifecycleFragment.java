package com.android.example.loglyfecycle;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by antlap on 04/12/2017.
 */

public class LifecycleFragment extends Fragment {
    private static final String BUNDLE_TITLE_KEY = "title";
    private PreferenceHelper mPeferenceHelper;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate layout and retrieve component
        View rootView = inflater.inflate(R.layout.dynamic_fragment_lifecycle, container, false);
        logMe();
        return rootView;
    }

    public void logMe() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement element = null;
        for (StackTraceElement e : stackTrace) {
            if (e.getFileName().startsWith(getClass().getSimpleName())) {
                if(e.getMethodName().startsWith("on")){
//                    System.out.println("MainActivity.logMe = " + e.getMethodName());
                    element = e;
                    break;
                }
            }
        }

        if(mPeferenceHelper == null) {
            mPeferenceHelper = ((MyApplication) getActivity().getApplication()).getPreferenceHelper();
        }

        if (element != null) {
            String methodName = element.getMethodName();
            int lineNumber = element.getLineNumber();
            String fileName = element.getFileName();
            mPeferenceHelper.addLog(methodName + "(" + fileName + ":" + lineNumber + ")");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // WORKAROUND_FOR_BUG_19917_KEY
        outState.putString(BUNDLE_TITLE_KEY, getString(R.string.activity_lyfecycle));
        logMe();
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logMe();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        logMe();
    }

    @Override
    public void onInflate(Context context, AttributeSet attrs, Bundle savedInstanceState) {
        super.onInflate(context, attrs, savedInstanceState);
        logMe();
    }

    @Override
    public void onInflate(Activity activity, AttributeSet attrs, Bundle savedInstanceState) {
        super.onInflate(activity, attrs, savedInstanceState);
        logMe();
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
        logMe();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        logMe();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        logMe();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        logMe();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        logMe();
    }

    @Override
    public void onStart() {
        super.onStart();
        logMe();
    }

    @Override
    public void onResume() {
        super.onResume();
        logMe();
    }

    @Override
    public void onPause() {
        super.onPause();
        logMe();
    }

    @Override
    public void onStop() {
        super.onStop();
        logMe();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        logMe();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        logMe();
    }

}
