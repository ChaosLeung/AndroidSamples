package org.chaos.demos.rxjava;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.IntEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

/**
 * @author Chaos
 *         10/17/16
 */

public class DialerView extends View {

    private int mRadius = 332;

    private int mLineWidth = 9;
    private int mLineHeight = 4;
    private int mLineDivideWidth = 3;

    private int mStartAngle = 229;
    private int mSweepAngle = 82;

    private Paint mPaint;
    private SweepGradient mShader;
    private RectF mBounds;
    private DashPathEffect mDashPathEffect;

    private float mRotate;

    private Bitmap mMiddleBitmap;
    private Bitmap mLightBitmap;
    private Bitmap mLeftBitmap;
    private Bitmap mRightBitmap;

    private ValueAnimator mSlideAnimator;

    private State mState;

    public DialerView(Context context) {
        super(context);
        init();
    }

    public DialerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DialerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setClickable(true);
        setFocusable(true);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mLineHeight);
        mDashPathEffect = new DashPathEffect(new float[]{mLineWidth, mLineDivideWidth}, 0);
        mPaint.setPathEffect(mDashPathEffect);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        BitmapFactory.decodeResource(getResources(), R.drawable.midpoint_green, options);
        int top = options.outHeight;
        options = new BitmapFactory.Options();
        BitmapFactory.decodeResource(getResources(), R.drawable.midpoint_red, options);
        top = Math.max(top, options.outHeight);

        mBounds = new RectF(0, top, 2 * mRadius, 2 * mRadius + top);

        mSlideAnimator = new SlideAnimator();
        mState = new Active();
        mState.enter();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setShader(mShader);
        canvas.drawArc(mBounds, mStartAngle, mSweepAngle, false, mPaint);

        mState.draw(canvas);
    }

    private void drawBitmapOnVertex(Canvas c, Bitmap b) {
        drawBitmapOnDegreesPoint(c, b, 270);
    }

    private void drawBitmapOnLeftMiddle(Canvas c, Bitmap b) {
        drawBitmapOnDegreesPoint(c, b, 225);
    }

    private void drawBitmapOnRightMiddle(Canvas c, Bitmap b) {
        drawBitmapOnDegreesPoint(c, b, 315);
    }

    private void drawBitmapOnDegreesPoint(Canvas c, Bitmap b, float degrees) {
        float x = getXCoordinatesInArcByDegrees(degrees);
        float y = getYCoordinatesInArcByDegrees(degrees);
        c.drawBitmap(b, x - b.getWidth() / 2, y - b.getHeight() / 2, mPaint);
    }

    private float getXCoordinatesInArcByDegrees(float degrees) {
        return (float) (mRadius * Math.cos(Math.toRadians(degrees)) + mBounds.centerX());
    }

    private float getYCoordinatesInArcByDegrees(float degrees) {
        return (float) (mRadius * Math.sin(Math.toRadians(degrees)) + mBounds.centerY());
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_LEFT:
                if (mState.getClass().equals(Incoming.class)) {
                    Incoming state = (Incoming) mState;
                    if (state.currentState.getClass().equals(Incoming.Normal.class)) {
                        state.switchState(state.ACCEPT);
                    } else if (state.currentState.getClass().equals(Incoming.Decline.class)) {
                        state.switchState(state.NORMAL);
                    }
                }
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                if (mState.getClass().equals(Incoming.class)) {
                    Incoming state = (Incoming) mState;
                    if (state.currentState.getClass().equals(Incoming.Normal.class)) {
                        state.switchState(state.DECLINE);
                    } else if (state.currentState.getClass().equals(Incoming.Accept.class)) {
                        state.switchState(state.NORMAL);
                    }
                }
                break;
            case KeyEvent.KEYCODE_ENTER:
                if (mState.getClass().equals(Incoming.class)) {
                    Incoming state = (Incoming) mState;
                    if (state.currentState.getClass().equals(Incoming.Accept.class)) {
                        state.switchState(state.ACCEPT_CLICK);
                    } else if (state.currentState.getClass().equals(Incoming.Decline.class)) {
                        state.switchState(state.DECLINE_CLICK);
                    }
                }
                break;
            case KeyEvent.KEYCODE_BACK:
            case KeyEvent.KEYCODE_ESCAPE:
                break;
        }
        return super.onKeyUp(keyCode, event);
    }

    private class SlideAnimator extends ValueAnimator implements ValueAnimator.AnimatorUpdateListener {

        private SlideAnimator() {
            setStartDelay(1000);
            setInterpolator(new AccelerateInterpolator());
            setEvaluator(new IntEvaluator());
            addUpdateListener(this);
        }

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            mRotate = (int) animation.getAnimatedValue();
            invalidate();
        }
    }

    private interface State {
        /**
         * perform initialization
         */
        void enter();

        void draw(Canvas c);

        /**
         * perform cleanup
         */
        void exit();
    }

    /**
     * for
     */
    private interface SubState extends State {
        void switchTo(State newState);
    }

    private class Incoming implements State {

        private SubState previousState;
        private SubState currentState;

        private final SubState NORMAL = new Normal();
        private final SubState ACCEPT = new Accept();
        private final SubState DECLINE = new Decline();
        private final SubState ACCEPT_CLICK = new AcceptClick();
        private final SubState DECLINE_CLICK = new DeclineClick();

        @Override
        public void enter() {
            mShader = new SweepGradient(mBounds.centerX(), mBounds.centerY(),
                    new int[]{getResources().getColor(R.color.gradient_cyan),
                            getResources().getColor(R.color.gradient_white),
                            getResources().getColor(R.color.gradient_red)},
                    new float[]{((float) mStartAngle) / 360, ((float) 270) / 360, ((float) (mSweepAngle + mStartAngle)) / 360});
            mPaint.setShader(mShader);
            mPaint.setColor(getResources().getColor(R.color.gradient_cyan));

            mLeftBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.accept);
            mRightBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.decline);
            mMiddleBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.midpoint_green);

            currentState = new Normal();
            currentState.enter();
        }

        @Override
        public void draw(Canvas c) {
            if (previousState != null) {
                previousState.draw(c);
            } else {
                currentState.draw(c);
            }
        }

        @Override
        public void exit() {
            mLeftBitmap.recycle();
            mRightBitmap.recycle();
            mMiddleBitmap.recycle();
        }

        private void switchState(SubState state) {
            if (previousState != null) {
                previousState.exit();
            }
            previousState = currentState;
            previousState.switchTo(state);
            currentState = state;
            if (state != NORMAL) {
                previousState.exit();
                currentState.enter();
                previousState = null;
            }
        }

        private class Normal implements SubState {

            ValueAnimator slide;

            private Normal() {
                slide = new ValueAnimator();
                slide.setDuration(1000);
                slide.setStartDelay(1000);
                slide.setFloatValues(0, -45);
                slide.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        mRotate = (float) animation.getAnimatedValue();
                        invalidate();
                    }
                });
            }

            @Override
            public void enter() {
                mLightBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.light);

                slide.addListener(new SimpleAnimatorListener() {
                    @Override
                    public void onAnimationCancel(Animator animation) {
                        slide.removeListener(this);
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        slide.start();
                    }
                });

                slide.start();
            }

            @Override
            public void draw(Canvas c) {
                c.save();
                c.rotate(mRotate, mBounds.centerX(), mBounds.centerY());
                drawBitmapOnVertex(c, mLightBitmap);
                c.restore();

                drawBitmapOnVertex(c, mMiddleBitmap);

                drawBitmapOnLeftMiddle(c, mLeftBitmap);
                drawBitmapOnRightMiddle(c, mRightBitmap);
            }

            @Override
            public void exit() {
                slide.cancel();

                mLightBitmap.recycle();
            }

            @Override
            public void switchTo(State newState) {
                // no-op, All animations are perform by others SubState
            }
        }

        private class Accept implements SubState {

            private AnimatorSet animatorSet;
            private int selectedAlpha;
            private Bitmap selectedBitmap;

            private Accept() {
                animatorSet = new AnimatorSet();
                animatorSet.setInterpolator(new AccelerateInterpolator());

                ValueAnimator slide = new ValueAnimator();
                slide.setDuration(500);
                slide.setFloatValues(0, -45);
                slide.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        mRotate = (float) animation.getAnimatedValue();
                        invalidate();
                    }
                });

                ValueAnimator alpha = new ValueAnimator();
                alpha.setDuration(250);
                alpha.setIntValues(0, 255);
                alpha.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        selectedAlpha = (int) animation.getAnimatedValue();
                        invalidate();
                    }
                });

                animatorSet.playSequentially(slide, alpha);
            }

            @Override
            public void enter() {
                selectedBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.accept_selected);

                animatorSet.start();
            }

            @Override
            public void draw(Canvas c) {
                c.save();
                c.rotate(mRotate, mBounds.centerX(), mBounds.centerY());
                mPaint.setAlpha(Math.round((1 - (mRotate / -45)) * 255));
                drawBitmapOnVertex(c, mMiddleBitmap);
                mPaint.setAlpha(255);
                c.restore();

                drawBitmapOnLeftMiddle(c, mLeftBitmap);
                drawBitmapOnRightMiddle(c, mRightBitmap);

                c.save();
                mPaint.setAlpha(selectedAlpha);
                drawBitmapOnLeftMiddle(c, selectedBitmap);
                mPaint.setAlpha(255);
                c.restore();
            }

            @Override
            public void exit() {
                selectedAlpha = 0;
                selectedBitmap.recycle();
                animatorSet.cancel();
            }

            @Override
            public void switchTo(State newState) {
                if (NORMAL == newState) {
                    AnimatorSet set = AnimatorUtils.reverseAnimatorSet(animatorSet);
                    set.addListener(new SimpleAnimatorListener() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            previousState.exit();
                            currentState.enter();
                            previousState = null;
                        }
                    });
                    set.start();
                }
            }
        }

        private class Decline implements SubState {

            private AnimatorSet animatorSet;
            private int selectedAlpha;
            private Bitmap selectedBitmap;

            private Decline() {
                animatorSet = new AnimatorSet();
                animatorSet.setInterpolator(new AccelerateInterpolator());

                ValueAnimator slide = new ValueAnimator();
                slide.setDuration(500);
                slide.setFloatValues(0, 45);
                slide.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        mRotate = (float) animation.getAnimatedValue();
                        invalidate();
                    }
                });

                ValueAnimator alpha = new ValueAnimator();
                alpha.setDuration(250);
                alpha.setIntValues(0, 255);
                alpha.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        selectedAlpha = (int) animation.getAnimatedValue();
                        invalidate();
                    }
                });

                animatorSet.playSequentially(slide, alpha);
            }

            @Override
            public void enter() {
                selectedBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.decline_selected);

                animatorSet.start();
            }

            @Override
            public void draw(Canvas c) {
                c.save();
                c.rotate(mRotate, mBounds.centerX(), mBounds.centerY());
                mPaint.setAlpha(Math.round((1 - (mRotate / 45)) * 255));
                drawBitmapOnVertex(c, mMiddleBitmap);
                mPaint.setAlpha(255);
                c.restore();

                drawBitmapOnLeftMiddle(c, mLeftBitmap);
                drawBitmapOnRightMiddle(c, mRightBitmap);

                c.save();
                mPaint.setAlpha(selectedAlpha);
                drawBitmapOnRightMiddle(c, selectedBitmap);
                mPaint.setAlpha(255);
                c.restore();
            }

            @Override
            public void exit() {
                selectedAlpha = 0;
                selectedBitmap.recycle();
                animatorSet.cancel();
            }

            @Override
            public void switchTo(State newState) {
                if (NORMAL == newState) {
                    AnimatorSet set = AnimatorUtils.reverseAnimatorSet(animatorSet);
                    set.addListener(new SimpleAnimatorListener() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            previousState.exit();
                            currentState.enter();
                            previousState = null;
                        }
                    });
                    set.start();
                }
            }
        }

        private class AcceptClick implements SubState {

            private Bitmap selectedBitmap;
            private ValueAnimator alphaAndScale;
            private Paint ripplePaint;

            @Override
            public void enter() {
                selectedBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.accept_selected);

                ripplePaint = new Paint();
                ripplePaint.setStyle(Paint.Style.FILL);
                ripplePaint.setColor(getResources().getColor(R.color.gradient_cyan));

                alphaAndScale = new ValueAnimator();
                alphaAndScale.setFloatValues(0, selectedBitmap.getWidth());
                alphaAndScale.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        invalidate();
                    }
                });
                alphaAndScale.start();
            }

            @Override
            public void draw(Canvas c) {
                drawBitmapOnLeftMiddle(c, selectedBitmap);
                drawBitmapOnRightMiddle(c, mRightBitmap);

                float fraction = alphaAndScale.getAnimatedFraction();
                float radius = fraction * selectedBitmap.getWidth();
                c.save();
                if (fraction <= 0.5) {
                    ripplePaint.setAlpha(Math.round(2 * fraction * 255));
                } else {
                    ripplePaint.setAlpha(Math.round(2 * (1 - fraction) * 255));
                }
                float x = getXCoordinatesInArcByDegrees(225);
                float y = getYCoordinatesInArcByDegrees(225);
                c.scale(fraction, fraction, x, y);
                c.drawCircle(getXCoordinatesInArcByDegrees(225), getYCoordinatesInArcByDegrees(225), radius, ripplePaint);
                c.restore();
            }

            @Override
            public void exit() {
                selectedBitmap.recycle();

                alphaAndScale.cancel();
            }

            @Override
            public void switchTo(State newState) {
                // no -op
            }
        }

        private class DeclineClick implements SubState {

            private Bitmap selectedBitmap;
            private ValueAnimator alphaAndScale;
            private Paint ripplePaint;

            @Override
            public void enter() {
                selectedBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.decline_selected);

                ripplePaint = new Paint();
                ripplePaint.setStyle(Paint.Style.FILL);
                ripplePaint.setColor(getResources().getColor(R.color.gradient_red));

                alphaAndScale = new ValueAnimator();
                alphaAndScale.setFloatValues(0, selectedBitmap.getWidth());
                alphaAndScale.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        invalidate();
                    }
                });
                alphaAndScale.start();
            }

            @Override
            public void draw(Canvas c) {
                drawBitmapOnLeftMiddle(c, mLeftBitmap);
                drawBitmapOnRightMiddle(c, selectedBitmap);

                float fraction = alphaAndScale.getAnimatedFraction();
                float radius = fraction * selectedBitmap.getWidth();
                c.save();
                if (fraction <= 0.5) {
                    ripplePaint.setAlpha(Math.round(2 * fraction * 255));
                } else {
                    ripplePaint.setAlpha(Math.round(2 * (1 - fraction) * 255));
                }
                float x = getXCoordinatesInArcByDegrees(315);
                float y = getYCoordinatesInArcByDegrees(315);
                c.scale(fraction, fraction, x, y);
                c.drawCircle(getXCoordinatesInArcByDegrees(315), getYCoordinatesInArcByDegrees(315), radius, ripplePaint);
                c.restore();
            }

            @Override
            public void exit() {
                selectedBitmap.recycle();

                alphaAndScale.cancel();
            }

            @Override
            public void switchTo(State newState) {
                // no -op
            }
        }
    }

    private class Active implements State {

        @Override
        public void enter() {
            mShader = new SweepGradient(mBounds.centerX(), mBounds.centerY(),
                    new int[]{getResources().getColor(R.color.gradient_red),
                            getResources().getColor(R.color.gradient_white)},
                    new float[]{((float) 270) / 360, ((float) (315)) / 360});
            mPaint.setShader(mShader);
            mPaint.setColor(getResources().getColor(R.color.gradient_red));

            mLightBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.light);
            mLeftBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.midpoint_red);
            mRightBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.decline);

            mSlideAnimator.setIntValues(-42, 42);
            mSlideAnimator.setStartDelay(1000);
            mSlideAnimator.setDuration(1500);
            mSlideAnimator.setRepeatCount(ValueAnimator.INFINITE);
            mSlideAnimator.setRepeatMode(ValueAnimator.RESTART);
            mSlideAnimator.start();
        }

        @Override
        public void draw(Canvas c) {
            c.save();
            c.rotate(mRotate, mBounds.centerX(), mBounds.centerY());
            drawBitmapOnVertex(c, mLightBitmap);
            c.restore();

            drawBitmapOnLeftMiddle(c, mLeftBitmap);
            drawBitmapOnRightMiddle(c, mRightBitmap);
        }

        @Override
        public void exit() {
            mRotate = 0;
            mSlideAnimator.cancel();

            mLightBitmap.recycle();
            mLeftBitmap.recycle();
            mRightBitmap.recycle();
        }

        private class Normal implements SubState {

            @Override
            public void switchTo(State newState) {

            }

            @Override
            public void enter() {

            }

            @Override
            public void draw(Canvas c) {

            }

            @Override
            public void exit() {

            }
        }
    }
}
