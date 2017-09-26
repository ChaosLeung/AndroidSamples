package org.chaos.demo.snaprecyclerview;

import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chaos
 *         28/06/2017
 */

public class InfoAdapter extends RecyclerView.Adapter<InfoHolder> {

    private final List<Info> mInfos = new ArrayList<>();

    public void addAll(List<Info> infos) {
        mInfos.addAll(infos);
        notifyDataSetChanged();
    }

    @Override
    public InfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new InfoHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_map_poi_info, parent, false));
    }

    @Override
    public void onBindViewHolder(InfoHolder holder, int position) {
        Info info = mInfos.get(position);
        holder.name.setText(info.name);
        holder.address.setText(info.address);
        holder.distance.setText(info.distance);
    }

    @Override
    public int getItemCount() {
        return mInfos.size();
    }
}

class InfoHolder extends RecyclerView.ViewHolder {

    TextView name;
    TextView distance;
    TextView address;

    InfoHolder(View itemView) {
        super(itemView);

        ViewGroup.LayoutParams params = itemView.getLayoutParams();
        float dimen = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12, itemView.getResources().getDisplayMetrics());
        params.width = Math.round(itemView.getResources().getDisplayMetrics().widthPixels - 4 * dimen);

        name = (TextView) itemView.findViewById(R.id.poi_name);
        distance = (TextView) itemView.findViewById(R.id.poi_distance);
        address = (TextView) itemView.findViewById(R.id.poi_address);
    }
}
