package org.chaos.demos.architecture.components;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Chaos
 *         27/05/2017
 */

public class MainActivity extends FragmentActivity {

    private static final String TAG = "MainActivity";

    private final DataObserver mDataObserver = new DataObserver();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() called with: savedInstanceState = [" + savedInstanceState + "]");
        setContentView(R.layout.activity_main);

        register();
        getSupportFragmentManager().beginTransaction().add(new DestructionReportFragment(), "org.chaos.demos.DestructionReportFragment").commit();

        getLifecycle().addObserver(mDataObserver);
        startService(new Intent(this, MainService.class));
//        ProcessLifecycleOwner.get().getLifecycle().addObserver(new ChildObserver());
        Log.d(TAG, "Process.myPid() = " + Process.myPid());
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy() called");
        unregister();
        getLifecycle().removeObserver(mDataObserver);
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    private void register() {
        getSupportFragmentManager()
                .registerFragmentLifecycleCallbacks(mFragmentCallback, true);
    }

    private void unregister() {
        getSupportFragmentManager().unregisterFragmentLifecycleCallbacks(mFragmentCallback);
    }

    private FragmentCallback mFragmentCallback = new FragmentCallback();

    private static class FragmentCallback extends FragmentManager.FragmentLifecycleCallbacks {
        private static final String TAG = "FragmentCallback";

        @Override
        public void onFragmentPreAttached(FragmentManager fm, Fragment f, Context context) {
            Log.d(TAG, "onFragmentPreAttached: ");
        }

        @Override
        public void onFragmentAttached(FragmentManager fm, Fragment f, Context context) {
            Log.d(TAG, "onFragmentAttached: ");
        }

        @Override
        public void onFragmentCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {
            Log.d(TAG, "onFragmentCreated: ");
        }

        @Override
        public void onFragmentActivityCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {
            Log.d(TAG, "onFragmentActivityCreated: ");
        }

        @Override
        public void onFragmentViewCreated(FragmentManager fm, Fragment f, View v, Bundle savedInstanceState) {
            Log.d(TAG, "onFragmentViewCreated: ");
        }

        @Override
        public void onFragmentStarted(FragmentManager fm, Fragment f) {
            Log.d(TAG, "onFragmentStarted: ");
        }

        @Override
        public void onFragmentResumed(FragmentManager fm, Fragment f) {
            Log.d(TAG, "onFragmentResumed: ");
        }

        @Override
        public void onFragmentPaused(FragmentManager fm, Fragment f) {
            Log.d(TAG, "onFragmentPaused: ");
        }

        @Override
        public void onFragmentStopped(FragmentManager fm, Fragment f) {
            Log.d(TAG, "onFragmentStopped: ");
        }

        @Override
        public void onFragmentSaveInstanceState(FragmentManager fm, Fragment f, Bundle outState) {
            Log.d(TAG, "onFragmentSaveInstanceState: ");
        }

        @Override
        public void onFragmentViewDestroyed(FragmentManager fm, Fragment f) {
            Log.d(TAG, "onFragmentViewDestroyed: ");
        }

        @Override
        public void onFragmentDestroyed(FragmentManager fm, Fragment f) {
            Log.d(TAG, "onFragmentDestroyed: ");
        }

        @Override
        public void onFragmentDetached(FragmentManager fm, Fragment f) {
            Log.d(TAG, "onFragmentDetached: ");
        }
    }

    public static class DestructionReportFragment extends Fragment {
        private static final String TAG = "DestructionReportFragme";

        @Override
        public void onAttach(Context context) {
            Log.d(TAG, "onAttach: before");
            super.onAttach(context);
            Log.d(TAG, "onAttach: after");
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            Log.d(TAG, "onCreate: before");
            super.onCreate(savedInstanceState);
            Log.d(TAG, "onCreate: after");
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            Log.d(TAG, "onCreateView: ");
            return super.onCreateView(inflater, container, savedInstanceState);
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            Log.d(TAG, "onViewCreated: before");
            super.onViewCreated(view, savedInstanceState);
            Log.d(TAG, "onViewCreated: after");
        }

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            Log.d(TAG, "onActivityCreated: before");
            super.onActivityCreated(savedInstanceState);
            Log.d(TAG, "onActivityCreated: after");
        }

        @Override
        public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
            Log.d(TAG, "onViewStateRestored: before");
            super.onViewStateRestored(savedInstanceState);
            Log.d(TAG, "onViewStateRestored: after");
        }

        @Override
        public void onStart() {
            Log.d(TAG, "onStart: before");
            super.onStart();
            Log.d(TAG, "onStart: after");
        }

        @Override
        public void onResume() {
            Log.d(TAG, "onResume: before");
            super.onResume();
            Log.d(TAG, "onResume: after");
        }

        @Override
        public void onSaveInstanceState(Bundle outState) {
            Log.d(TAG, "onSaveInstanceState: before");
            super.onSaveInstanceState(outState);
            Log.d(TAG, "onSaveInstanceState: after");
        }

        @Override
        public void onPause() {
            Log.d(TAG, "onPause: before");
            super.onPause();
            Log.d(TAG, "onPause: after");
        }

        @Override
        public void onStop() {
            Log.d(TAG, "onStop: before");
            super.onStop();
            Log.d(TAG, "onStop: after");
        }

        @Override
        public void onDestroyView() {
            Log.d(TAG, "onDestroyView: before");
            super.onDestroyView();
            Log.d(TAG, "onDestroyView: after");
        }

        @Override
        public void onDestroy() {
            Log.d(TAG, "onDestroy: before");
            super.onDestroy();
            Log.d(TAG, "onDestroy: after");
        }

        @Override
        public void onDetach() {
            Log.d(TAG, "onDetach: before");
            super.onDetach();
            Log.d(TAG, "onDetach: after");
        }
    }
}
