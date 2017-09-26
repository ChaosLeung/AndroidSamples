package org.chaos.demos.architecture.components;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

/**
 * @author Chaos
 *         31/05/2017
 */

public class MainApp extends Application {

    private ActivityLifecycleCallbacks mCallbacks = new ActivityLifecycleCallbacks() {
        private static final String TAG = "ActivityLifecycleCallba";
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            Log.d(TAG, "onActivityCreated: ");
        }

        @Override
        public void onActivityStarted(Activity activity) {
            Log.d(TAG, "onActivityStarted: ");
        }

        @Override
        public void onActivityResumed(Activity activity) {
            Log.d(TAG, "onActivityResumed: ");
        }

        @Override
        public void onActivityPaused(Activity activity) {
            Log.d(TAG, "onActivityPaused: ");
        }

        @Override
        public void onActivityStopped(Activity activity) {
            Log.d(TAG, "onActivityStopped: ");
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            Log.d(TAG, "onActivitySaveInstanceState: ");
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            Log.d(TAG, "onActivityDestroyed: ");
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(mCallbacks);
    }
}
