package org.chaos.demo.snaprecyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

/**
 * @author Chaos
 *         28/06/2017
 */

public class InfoSnapHelper extends LinearSnapHelper {

    private static final String TAG = "InfoSnapHelper";

    @Override
    public int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager layoutManager, @NonNull View targetView) {
        int[] out = super.calculateDistanceToFinalSnap(layoutManager, targetView);
        if (layoutManager.canScrollHorizontally()) {
            int distance = out[0];
            int position = layoutManager.getPosition(targetView);
            if (position != 0 && position != layoutManager.getItemCount()) {

                int margin = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12, targetView.getResources().getDisplayMetrics()));
                float dimen = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12, targetView.getResources().getDisplayMetrics());
                int width = Math.round(targetView.getResources().getDisplayMetrics().widthPixels - 4 * dimen);
                int firstLeft = (targetView.getResources().getDisplayMetrics().widthPixels - width) / 2;

                // 因为第一个 item 的左边距为 firstLeft，右边距为 margin,
                // 第一个 item 居中是靠左边顶过去的，然而这样会影响到后面的 item,
                // 所以中间的 item 的距离要重新居中，即再移动 (firstLeft - margin) /2
                out[0] = distance - (firstLeft - margin) / 2;
            }
        }
        return out;
    }
}
