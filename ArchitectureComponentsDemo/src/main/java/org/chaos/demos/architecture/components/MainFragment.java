package org.chaos.demos.architecture.components;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Chaos
 *         27/05/2017
 */

public class MainFragment extends Fragment {

    private static final String TAG = "MainFragment";

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
