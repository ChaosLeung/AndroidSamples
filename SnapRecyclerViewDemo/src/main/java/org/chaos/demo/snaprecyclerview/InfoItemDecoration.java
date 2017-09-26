package org.chaos.demo.snaprecyclerview;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

/**
 * @author Chaos
 *         28/06/2017
 */

public class InfoItemDecoration extends RecyclerView.ItemDecoration {

    private static final String TAG = "InfoItemDecoration";

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        int first = 0;
        int last = state.getItemCount() - 1;

        int margin = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12, view.getResources().getDisplayMetrics()));
        float dimen = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12, view.getResources().getDisplayMetrics());
        int width = Math.round(view.getResources().getDisplayMetrics().widthPixels - 4 * dimen);
        int firstLeft = (view.getResources().getDisplayMetrics().widthPixels - width) / 2;

        if (first == last) {
            outRect.set(firstLeft, 0, firstLeft, 0);
        } else if (position == first) {
            outRect.set(firstLeft, 0, margin, 0);
        } else if (position == last) {
            outRect.set(0, 0, firstLeft, 0);
        } else {
            outRect.set(0, 0, margin, 0);
        }
    }
}