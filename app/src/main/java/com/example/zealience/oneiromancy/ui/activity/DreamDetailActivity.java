package com.example.zealience.oneiromancy.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.zealience.oneiromancy.R;
import com.example.zealience.oneiromancy.entity.DreamEntity;
import com.example.zealience.oneiromancy.ui.DreamDetailAdapter;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.steven.base.app.BaseApp;

import java.util.ArrayList;

public class DreamDetailActivity extends AppCompatActivity {
    private AppBarLayout appBar;
    private CollapsingToolbarLayout toolbarLayout;
    private FloatingActionButton fab;
    private RecyclerView recyclerview_dream_detail_list;
    private DreamDetailAdapter dreamDetailAdapter;
    private TitleBar dream_detail_titlebar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BaseApp.getInstance().addActivity(this);
        setContentView(R.layout.activity_dream_detail);
        DreamEntity entity = (DreamEntity) getIntent().getSerializableExtra("dreamInfo");
        appBar = (AppBarLayout) findViewById(R.id.app_bar);
        toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        dream_detail_titlebar = (TitleBar) findViewById(R.id.dream_detail_titlebar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        recyclerview_dream_detail_list = (RecyclerView) findViewById(R.id.recyclerview_dream_detail_list);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, entity.getTitle(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        toolbarLayout.setTitle(entity.getTitle());
        recyclerview_dream_detail_list.setLayoutManager(new LinearLayoutManager(this));
        dreamDetailAdapter = new DreamDetailAdapter(R.layout.item_dream_detail, new ArrayList<>());
        recyclerview_dream_detail_list.setAdapter(dreamDetailAdapter);
        dreamDetailAdapter.setNewData(entity.getList());
        dream_detail_titlebar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {
                finish();
            }

            @Override
            public void onTitleClick(View v) {

            }

            @Override
            public void onRightClick(View v) {

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}