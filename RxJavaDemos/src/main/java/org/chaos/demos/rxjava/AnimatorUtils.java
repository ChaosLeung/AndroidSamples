package org.chaos.demos.rxjava;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.view.animation.Interpolator;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Chaos
 *         10/20/16
 */

public class AnimatorUtils {

    public static AnimatorSet reverseAnimatorSet(AnimatorSet animatorSet) {
        AnimatorSet reverse = new AnimatorSet();
        ArrayList<Animator> animators = animatorSet.getChildAnimations();
        Collections.reverse(animators);
        reverse.playSequentially(animators);
        reverse.setStartDelay(animatorSet.getStartDelay());
        final TimeInterpolator interpolator = animatorSet.getInterpolator();
        reverse.setInterpolator(new Interpolator() {
            @Override
            public float getInterpolation(float input) {
                return 1 - interpolator.getInterpolation(input);
            }
        });
        return reverse;
    }
}
