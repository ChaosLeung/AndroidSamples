package org.chaos.demos.coordinator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Chaos
 *         11/11/16
 */

public class MainFragment extends Fragment {
    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @BindView(R.id.recycler) RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setAutoMeasureEnabled(true);

        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(llm);

        mRecyclerView.setAdapter(new Adapter());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    class Adapter extends RecyclerView.Adapter<ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.mTextView.setText("当前为第 " + position + " 项");
        }

        @Override
        public int getItemCount() {
            return 50;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text) TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
