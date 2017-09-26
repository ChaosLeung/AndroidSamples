package org.chaos.demos.transition;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout mActivityMain;
    private TextView mBlue;
    private TextView mRed;
    private TextView mYellow;
    private TextView mGreen;

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mActivityMain = (LinearLayout) findViewById(R.id.activity_main);
        mActivityMain.setOnClickListener(this);

        mBlue = (TextView) findViewById(R.id.blue);
        mRed = (TextView) findViewById(R.id.red);
        mYellow = (TextView) findViewById(R.id.yellow);
        mGreen = (TextView) findViewById(R.id.green);

        mImageView = (ImageView) findViewById(R.id.image);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.startActivity(MainActivity.this, new Intent(MainActivity.this, Main2Activity.class),
                        ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, mImageView, mImageView.getTransitionName()).toBundle());
            }
        });
    }

    @Override
    public void onClick(View v) {
        TransitionManager.beginDelayedTransition(mActivityMain, new Slide(Gravity.TOP));
        toggleVisibility(mBlue, mRed, mYellow, mGreen);
    }

    private static void toggleVisibility(View... views) {
        for (View view : views) {
            boolean isVisible = view.getVisibility() == View.VISIBLE;
            view.setVisibility(isVisible ? View.INVISIBLE : View.VISIBLE);
        }
    }
}
