package org.chaos.demos.architecture.components;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import android.support.annotation.Nullable;
import android.util.Log;


/**
 * @author Chaos
 *         27/05/2017
 */

public class MainService extends Service {
    private static final String TAG = "MainService";

    @Override
    public void onCreate() {
        super.onCreate();
//        getLifecycle().addObserver(new DataObserver());
        Log.d(TAG, "Process.myPid() = " + Process.myPid());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
