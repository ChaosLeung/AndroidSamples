package org.chaos.demos.viewdraghelper;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * @author Chaos
 *         24/01/2017
 */

public class VDRelativeLayout extends RelativeLayout {

    private static final String TAG = "VDRelativeLayout";

    private ViewDragHelper mViewDragHelper;

    public VDRelativeLayout(Context context) {
        super(context);
        init();
    }

    public VDRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public VDRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mViewDragHelper = ViewDragHelper.create(this, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                Log.d(TAG, "tryCaptureView() called with: child = [" + child + "], pointerId = [" + pointerId + "]");
                return true;
            }

            @Override
            public void onViewDragStateChanged(int state) {
                Log.d(TAG, "onViewDragStateChanged() called with: state = [" + state + "]");
                super.onViewDragStateChanged(state);
            }

            @Override
            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                Log.d(TAG, "onViewPositionChanged() called with: changedView = [" + changedView + "], left = [" + left + "], top = [" + top + "], dx = [" + dx + "], dy = [" + dy + "]");
                super.onViewPositionChanged(changedView, left, top, dx, dy);
            }

            @Override
            public void onViewCaptured(View capturedChild, int activePointerId) {
                Log.d(TAG, "onViewCaptured() called with: capturedChild = [" + capturedChild + "], activePointerId = [" + activePointerId + "]");
                super.onViewCaptured(capturedChild, activePointerId);
            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                Log.d(TAG, "onViewReleased() called with: releasedChild = [" + releasedChild + "], xvel = [" + xvel + "], yvel = [" + yvel + "]");
                super.onViewReleased(releasedChild, xvel, yvel);
            }

            @Override
            public void onEdgeTouched(int edgeFlags, int pointerId) {
                Log.d(TAG, "onEdgeTouched() called with: edgeFlags = [" + edgeFlags + "], pointerId = [" + pointerId + "]");
                super.onEdgeTouched(edgeFlags, pointerId);
            }

            @Override
            public boolean onEdgeLock(int edgeFlags) {
                Log.d(TAG, "onEdgeLock() called with: edgeFlags = [" + edgeFlags + "]");
                return super.onEdgeLock(edgeFlags);
            }

            @Override
            public void onEdgeDragStarted(int edgeFlags, int pointerId) {
                Log.d(TAG, "onEdgeDragStarted() called with: edgeFlags = [" + edgeFlags + "], pointerId = [" + pointerId + "]");
                super.onEdgeDragStarted(edgeFlags, pointerId);
            }

            @Override
            public int getOrderedChildIndex(int index) {
                Log.d(TAG, "getOrderedChildIndex() called with: index = [" + index + "]");
                return super.getOrderedChildIndex(index);
            }

            @Override
            public int getViewHorizontalDragRange(View child) {
                Log.d(TAG, "getViewHorizontalDragRange() called with: child = [" + child + "]");
                return super.getViewHorizontalDragRange(child);
            }

            @Override
            public int getViewVerticalDragRange(View child) {
                Log.d(TAG, "getViewVerticalDragRange() called with: child = [" + child + "]");
                return super.getViewVerticalDragRange(child);
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                Log.d(TAG, "clampViewPositionHorizontal() called with: child = [" + child + "], left = [" + left + "], dx = [" + dx + "]");
                return left;
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                Log.d(TAG, "clampViewPositionVertical() called with: child = [" + child + "], top = [" + top + "], dy = [" + dy + "]");
                return top;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }
}
