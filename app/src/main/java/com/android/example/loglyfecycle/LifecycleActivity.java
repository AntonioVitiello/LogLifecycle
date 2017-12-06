package com.android.example.loglyfecycle;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class LifecycleActivity extends AppCompatActivity {
    public static final int CLOSE_AFTER_MILLISEC = 1500;
    private static final String BUNDLE_TITLE_KEY = "title";
    private Toolbar myToolbar;
    private PreferenceHelper mPeferenceHelper;
    private String mTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);

        myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // Enable the Up button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        logMe();
    }

    public void logMe() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement element = null;
        for (StackTraceElement e : stackTrace) {
            if (e.getFileName().startsWith(getClass().getSimpleName())) {
                if (e.getMethodName().startsWith("on")) {
//                    System.out.println("MainActivity.logMe = " + e.getMethodName());
                    element = e;
                    break;
                }
            }
        }

        if (mPeferenceHelper == null) {
            mPeferenceHelper = ((MyApplication) getApplication()).getPreferenceHelper();
        }

        if (element != null) {
            String methodName = element.getMethodName();
            int lineNumber = element.getLineNumber();
            String fileName = element.getFileName();
            mPeferenceHelper.addLog(methodName + "(" + fileName + ":" + lineNumber + ")");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        logMe();
/*
        myToolbar.postDelayed(new Runnable() {
            @Override
            public void run() {
                setResult(RESULT_OK);
                finish();
            }
        }, CLOSE_AFTER_MILLISEC);
*/
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(BUNDLE_TITLE_KEY, getString(R.string.activity_lyfecycle));
        logMe();
    }

    // This callback is called only when there is a saved instance previously saved using
    // onSaveInstanceState(). We restore some state in onCreate() while we can optionally restore
    // other state here, possibly usable after onStart() has completed.
    // The savedInstanceState Bundle is same as the one used in onCreate().
    // see: https://developer.android.com/guide/components/activities/activity-lifecycle.html
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mTitle = savedInstanceState.getString(BUNDLE_TITLE_KEY);
        logMe();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        logMe();
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void closeActivity(View view) {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        logMe();
    }

    @Override
    protected void onPause() {
        super.onPause();
        logMe();
    }

    @Override
    protected void onStop() {
        super.onStop();
        logMe();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        logMe();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        logMe();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        logMe();
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        logMe();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        logMe();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        logMe();
    }

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);
        logMe();
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        logMe();
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
        logMe();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        logMe();
    }

    /*
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        logMe();
        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public void onPanelClosed(int featureId, Menu menu) {
        super.onPanelClosed(featureId, menu);
        logMe();
    }

    @Override
    public void onSupportActionModeStarted(@NonNull ActionMode mode) {
        super.onSupportActionModeStarted(mode);
        logMe();
    }

    @Override
    public void onSupportActionModeFinished(@NonNull ActionMode mode) {
        super.onSupportActionModeFinished(mode);
        logMe();
    }

    @Nullable
    @Override
    public ActionMode onWindowStartingSupportActionMode(@NonNull ActionMode.Callback callback) {
        logMe();
        return super.onWindowStartingSupportActionMode(callback);
    }

    @Override
    public void onCreateSupportNavigateUpTaskStack(@NonNull TaskStackBuilder builder) {
        super.onCreateSupportNavigateUpTaskStack(builder);
        logMe();
    }

    @Override
    public void onPrepareSupportNavigateUpTaskStack(@NonNull TaskStackBuilder builder) {
        super.onPrepareSupportNavigateUpTaskStack(builder);
        logMe();
    }

    @Override
    public boolean onSupportNavigateUp() {
        logMe();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onSupportContentChanged() {
        logMe();
        super.onSupportContentChanged();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        logMe();
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        logMe();
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        logMe();
        super.onBackPressed();
    }

    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode) {
        logMe();
        super.onMultiWindowModeChanged(isInMultiWindowMode);
    }

    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode) {
        logMe();
        super.onPictureInPictureModeChanged(isInPictureInPictureMode);
    }

    @Override
    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        logMe();
        return super.onCreatePanelMenu(featureId, menu);
    }

    @Override
    public void onLowMemory() {
        logMe();
        super.onLowMemory();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        logMe();
        super.onNewIntent(intent);
    }

    @Override
    public void onStateNotSaved() {
        super.onStateNotSaved();
        logMe();
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        logMe();
    }

    @Override
    public boolean onPreparePanel(int featureId, View view, Menu menu) {
        logMe();
        return super.onPreparePanel(featureId, view, menu);
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        logMe();
        return super.onRetainCustomNonConfigurationInstance();
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        logMe();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        logMe();
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        logMe();
        return super.onCreateView(parent, name, context, attrs);
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        logMe();
        return super.onCreateView(name, context, attrs);
    }

    @Override
    public void addContentView(View view, ViewGroup.LayoutParams params) {
        super.addContentView(view, params);
        logMe();
    }

    @Override
    public void onLocalVoiceInteractionStarted() {
        super.onLocalVoiceInteractionStarted();
        logMe();
    }

    @Override
    public void onLocalVoiceInteractionStopped() {
        super.onLocalVoiceInteractionStopped();
        logMe();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        logMe();
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        logMe();
    }

    @Override
    public boolean onCreateThumbnail(Bitmap outBitmap, Canvas canvas) {
        logMe();
        return super.onCreateThumbnail(outBitmap, canvas);
    }

    @Nullable
    @Override
    public CharSequence onCreateDescription() {
        logMe();
        return super.onCreateDescription();
    }

    @Override
    public void onProvideAssistData(Bundle data) {
        super.onProvideAssistData(data);
        logMe();
    }

    @Override
    public void onProvideAssistContent(AssistContent outContent) {
        super.onProvideAssistContent(outContent);
        logMe();
    }

    @Override
    public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> data, Menu menu, int deviceId) {
        super.onProvideKeyboardShortcuts(data, menu, deviceId);
        logMe();
    }

    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode, Configuration newConfig) {
        super.onMultiWindowModeChanged(isInMultiWindowMode, newConfig);
        logMe();
    }

    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode, Configuration newConfig) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig);
        logMe();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        logMe();
    }

    @Override
    public void onAttachFragment(android.app.Fragment fragment) {
        super.onAttachFragment(fragment);
        logMe();
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        logMe();
        return super.onKeyLongPress(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        logMe();
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) {
        logMe();
        return super.onKeyMultiple(keyCode, repeatCount, event);
    }

    @Override
    public boolean onKeyShortcut(int keyCode, KeyEvent event) {
        logMe();
        return super.onKeyShortcut(keyCode, event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        logMe();
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onTrackballEvent(MotionEvent event) {
        logMe();
        return super.onTrackballEvent(event);
    }

    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {
        logMe();
        return super.onGenericMotionEvent(event);
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        logMe();
    }

    @Override
    public void onWindowAttributesChanged(WindowManager.LayoutParams params) {
        super.onWindowAttributesChanged(params);
        logMe();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        logMe();
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        logMe();
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        logMe();
    }

    @Nullable
    @Override
    public View onCreatePanelView(int featureId) {
        logMe();
        return super.onCreatePanelView(featureId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        logMe();
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        logMe();
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onNavigateUp() {
        logMe();
        return super.onNavigateUp();
    }

    @Override
    public boolean onNavigateUpFromChild(Activity child) {
        logMe();
        return super.onNavigateUpFromChild(child);
    }

    @Override
    public void onCreateNavigateUpTaskStack(android.app.TaskStackBuilder builder) {
        super.onCreateNavigateUpTaskStack(builder);
        logMe();
    }

    @Override
    public void onPrepareNavigateUpTaskStack(android.app.TaskStackBuilder builder) {
        super.onPrepareNavigateUpTaskStack(builder);
        logMe();
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        super.onOptionsMenuClosed(menu);
        logMe();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        logMe();
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        logMe();
        return super.onContextItemSelected(item);
    }

    @Override
    public void onContextMenuClosed(Menu menu) {
        super.onContextMenuClosed(menu);
        logMe();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        logMe();
        return super.onCreateDialog(id);
    }

    @Nullable
    @Override
    protected Dialog onCreateDialog(int id, Bundle args) {
        logMe();
        return super.onCreateDialog(id, args);
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        super.onPrepareDialog(id, dialog);
        logMe();
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog, Bundle args) {
        super.onPrepareDialog(id, dialog, args);
        logMe();
    }

    @Override
    public boolean onSearchRequested(@Nullable SearchEvent searchEvent) {
        logMe();
        return super.onSearchRequested(searchEvent);
    }

    @Override
    public boolean onSearchRequested() {
        logMe();
        return super.onSearchRequested();
    }

    @Override
    protected void onApplyThemeResource(Resources.Theme theme, int resid, boolean first) {
        super.onApplyThemeResource(theme, resid, first);
        logMe();
    }

    @Override
    public Uri onProvideReferrer() {
        return super.onProvideReferrer();
    }

    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
        logMe();
    }

    @Override
    protected void onChildTitleChanged(Activity childActivity, CharSequence title) {
        super.onChildTitleChanged(childActivity, title);
        logMe();
    }

    @Override
    public void onVisibleBehindCanceled() {
        super.onVisibleBehindCanceled();
        logMe();
    }

    @Override
    public void onEnterAnimationComplete() {
        super.onEnterAnimationComplete();
        logMe();
    }

    @Nullable
    @Override
    public android.view.ActionMode onWindowStartingActionMode(android.view.ActionMode.Callback callback) {
        logMe();
        return super.onWindowStartingActionMode(callback);
    }

    @Nullable
    @Override
    public android.view.ActionMode onWindowStartingActionMode(android.view.ActionMode.Callback callback, int type) {
        logMe();
        return super.onWindowStartingActionMode(callback, type);
    }

    @Override
    public void onActionModeStarted(android.view.ActionMode mode) {
        super.onActionModeStarted(mode);
        logMe();
    }

    @Override
    public void onActionModeFinished(android.view.ActionMode mode) {
        super.onActionModeFinished(mode);
        logMe();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        logMe();
    }
*/
}
