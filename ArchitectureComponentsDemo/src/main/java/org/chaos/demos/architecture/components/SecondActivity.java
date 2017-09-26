package org.chaos.demos.architecture.components;

import android.app.Fragment;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Chaos
 *         31/05/2017
 */

public class SecondActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Fragment f = new SecondFragment();
        getFragmentManager().beginTransaction().add(f, "org.chaos.demos.SecondFragment").commit();
    }

    public static class SecondFragment extends Fragment implements LifecycleRegistryOwner {
        private static final String TAG = "SecondFragment";

        private final LifecycleRegistry mRegistry = new LifecycleRegistry(this);

        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            Log.d(TAG, "onAttach: after");
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Log.d(TAG, "onCreate: after");
            getChildFragmentManager().beginTransaction().add(new ChildFragment(), "org.chaos.demos.ChildFragment").commit();
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            Log.d(TAG, "onCreateView: ");
            return super.onCreateView(inflater, container, savedInstanceState);
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            Log.d(TAG, "onViewCreated: after");
        }

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            Log.d(TAG, "onActivityCreated: after");
        }

        @Override
        public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
            super.onViewStateRestored(savedInstanceState);
            Log.d(TAG, "onViewStateRestored: after");
        }

        @Override
        public void onStart() {
            super.onStart();
            Log.d(TAG, "onStart: after");
        }

        @Override
        public void onResume() {
            super.onResume();
            Log.d(TAG, "onResume: after");
        }

        @Override
        public void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            Log.d(TAG, "onSaveInstanceState: after");
        }

        @Override
        public void onPause() {
            super.onPause();
            Log.d(TAG, "onPause: after");
        }

        @Override
        public void onStop() {
            super.onStop();
            Log.d(TAG, "onStop: after");
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            Log.d(TAG, "onDestroyView: after");
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            Log.d(TAG, "onDestroy: after");
        }

        @Override
        public void onDetach() {
            super.onDetach();
            Log.d(TAG, "onDetach: after");
        }

        @Override
        public LifecycleRegistry getLifecycle() {
            return mRegistry;
        }
    }

    public static class ChildFragment extends Fragment {
        private static final String TAG = "ChildFragment";

        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            Log.d(TAG, "onAttach: after");
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Log.d(TAG, "onCreate: after");

            ((LifecycleRegistryOwner)getParentFragment()).getLifecycle().addObserver(new ChildObserver());
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            Log.d(TAG, "onCreateView: ");
            return super.onCreateView(inflater, container, savedInstanceState);
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            Log.d(TAG, "onViewCreated: after");
        }

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            Log.d(TAG, "onActivityCreated: after");
        }

        @Override
        public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
            super.onViewStateRestored(savedInstanceState);
            Log.d(TAG, "onViewStateRestored: after");
        }

        @Override
        public void onStart() {
            super.onStart();
            Log.d(TAG, "onStart: after");
        }

        @Override
        public void onResume() {
            super.onResume();
            Log.d(TAG, "onResume: after");
        }

        @Override
        public void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            Log.d(TAG, "onSaveInstanceState: after");
        }

        @Override
        public void onPause() {
            super.onPause();
            Log.d(TAG, "onPause: after");
        }

        @Override
        public void onStop() {
            super.onStop();
            Log.d(TAG, "onStop: after");
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            Log.d(TAG, "onDestroyView: after");
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            Log.d(TAG, "onDestroy: after");
        }

        @Override
        public void onDetach() {
            super.onDetach();
            Log.d(TAG, "onDetach: after");
        }
    }
}
