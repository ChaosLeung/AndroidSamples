package org.chaos.demos.architecture.components;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.util.Log;

/**
 * @author Chaos
 *         27/05/2017
 */

public class DataObserver implements LifecycleObserver {

    private static final String TAG = "DataObserver";

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    public void onAny() {
        Log.d(TAG, "onAny() called");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        Log.d(TAG, "onCreate() called");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        Log.d(TAG, "onStart() called");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        Log.d(TAG, "onResume() called");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        Log.d(TAG, "onPause() called");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        Log.d(TAG, "onStop() called");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        Log.d(TAG, "onDestroy() called");
    }
}
