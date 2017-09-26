package org.chaos.demos.valueanimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ValueAnimator mValueAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startAnimation(View v) {
        mValueAnimator = ValueAnimator.ofFloat(0, 100f);
        mValueAnimator.setInterpolator(new DecelerateInterpolator());
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.d(TAG, "onAnimationUpdate() called with: animation = [" + animation.getAnimatedFraction() + "]");
            }
        });
        mValueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationCancel(Animator animation) {
                Log.d(TAG, "onAnimationCancel() called with: animation = [" + animation + "]");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.d(TAG, "onAnimationEnd() called with: animation = [" + animation + "]");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Log.d(TAG, "onAnimationRepeat() called with: animation = [" + animation + "]");
            }

            @Override
            public void onAnimationStart(Animator animation) {
                Log.d(TAG, "onAnimationStart() called with: animation = [" + animation + "]");
            }

            @Override
            public void onAnimationPause(Animator animation) {
                Log.d(TAG, "onAnimationPause() called with: animation = [" + animation + "]");
            }

            @Override
            public void onAnimationResume(Animator animation) {
                Log.d(TAG, "onAnimationResume() called with: animation = [" + animation + "]");
            }
        });
        mValueAnimator.setRepeatMode(ValueAnimator.RESTART);
        mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mValueAnimator.start();
    }

    public void cancelAnimation(View v) {
        mValueAnimator.cancel();
    }

    public void endAnimation(View v) {
        mValueAnimator.end();
    }
}
