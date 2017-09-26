package org.chaos.demos.activitytranslucent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: "+this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: "+this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: "+this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: "+this);
    }

    public void openNewActivity(View v) {
        start(this);
    }
}
