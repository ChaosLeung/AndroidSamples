package org.chaos.demos.mutilprocess.sp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    static SharedPreferences sSharePreferences;

    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = (EditText) findViewById(R.id.text);
    }

    public void save(View v) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        Log.d(tag(), "save: sp = " + sp);
        sp.edit().putString("value", mEditText.getText().toString()).apply();
        Log.d(tag(), "save: " + sSharePreferences);
        Log.d(tag(), "save: sSharePreferences == sp " + (sSharePreferences == sp));
        sSharePreferences = sp;
    }

    public void read(View v) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        Log.d(tag(), "read: sp = " + sp);
        mEditText.setText(sp.getString("value", ""));
        Log.d(tag(), "read: " + sSharePreferences);
        Log.d(tag(), "read: sSharePreferences == sp " + (sSharePreferences == sp));
        sSharePreferences = sp;
    }

    public void open(View v) {
        startActivity(new Intent(this, SecondActivity.class));
    }

    private String tag() {
        return getClass().getSimpleName();
    }
}
