package org.chaos.demos.coordinator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.container) FrameLayout mFragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        Log.d(TAG, "mToolbar = " + mToolbar);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, MainFragment.newInstance()).commit();
    }
}
