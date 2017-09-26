package com.texchi.demos.launchmode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public abstract class BaseActivity extends AppCompatActivity {

    public void openStandard(View v) {
        startAct(DefaultStandard.class);
    }

    public void openSingleTask(View v) {
        startAct(DefaultSingleTask.class);
    }

    public void openSingleTop(View v) {
        startAct(DefaultSingleTop.class);
    }

    public void openSingleInstance(View v) {
        startAct(DefaultSingleInstance.class);
    }

    public void openRemoteStandard(View v) {
        startAct(RemoteStandard.class);
    }

    public void openRemoteSingleTask(View v) {
        startAct(RemoteSingleTask.class);
    }

    public void openRemoteSingleTop(View v) {
        startAct(RemoteSingleTop.class);
    }

    public void openRemoteSingleInstance(View v) {
        startAct(RemoteSingleInstance.class);
    }

    public void openAffinityStandard(View v) {
        startAct(AffinityStandard.class);
    }

    public void openAffinitySingleTask(View v) {
        startAct(AffinitySingleTask.class);
    }

    public void openAffinitySingleTop(View v) {
        startAct(AffinitySingleTop.class);
    }

    public void openAffinitySingleInstance(View v) {
        startAct(AffinitySingleInstance.class);
    }

    private void startAct(Class<? extends Activity> clazz) {
        startActivity(new Intent(this, clazz));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(toString());
        Log.e(getTag(), "onCreate: " + this + " taskId = " + getTaskId());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e(getTag(), "onNewIntent: " + this + " taskId = " + getTaskId());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(getTag(), "onDestroy: " + this + " taskId = " + getTaskId());
    }

    abstract String getTag();

    @Override
    public String toString() {
        return getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + "[" + getTaskId() + "]";
    }
}
