package org.chaos.demo.snaprecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mInfoView;
    private InfoAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInfoView = (RecyclerView) findViewById(R.id.infos);
        mInfoView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mAdapter = new InfoAdapter();
        mInfoView.setAdapter(mAdapter);
        mInfoView.addItemDecoration(new InfoItemDecoration());
        new InfoSnapHelper().attachToRecyclerView(mInfoView);

        setupData();
    }

    private void setupData() {
        List<Info> infos = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            String name = "Test " + i;
            String address = "Address " + i;
            String distance = "Distance " + i;
            infos.add(new Info(name, address, distance));
        }
        mAdapter.addAll(infos);
    }
}