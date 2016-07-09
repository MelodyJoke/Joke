package com.melody.joke.business.main;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.melody.base.template.activity.HandlerActivity;
import com.melody.base.util.BuildUtility;
import com.melody.base.util.LogUtility;
import com.melody.joke.R;
import com.melody.joke.bean.Demo;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("WeakerAccess, deprecation")
public class MainActivity extends HandlerActivity implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipeRefreshLayout;

    private RecyclerView recyclerView;

    private DemoAdapter adapter;

    private List<Demo> demos;

    private static List<Demo> demoList = new ArrayList<>();

    static {
        Collections.addAll(demoList,
                new Demo(1, "divider", "divider", null),
                new Demo(2, "Activity", "title", new Intent()),
                new Demo(0, "", "", new Intent()),
                new Demo(0, "", "", new Intent()),
                new Demo(0, "", "", new Intent()),
                new Demo(0, "", "", new Intent()),
                new Demo(0, "", "", new Intent()),
                new Demo(0, "", "", new Intent()),
                new Demo(2, "Fragment", "title", new Intent()),
                new Demo(0, "", "", new Intent()),
                new Demo(0, "", "", new Intent()),
                new Demo(2, "CoordinatorLayout", "title", new Intent()),
                new Demo(0, "", "", new Intent()),
                new Demo(0, "", "", new Intent()),
                new Demo(2, "RecyclerView", "title", new Intent()),
                new Demo(0, "", "", new Intent()),
                new Demo(0, "", "", new Intent()),
                new Demo(2, "Http-Volley", "title", new Intent()),
                new Demo(0, "", "", new Intent()),
                new Demo(2, "Http-Retrofit", "title", new Intent()),
                new Demo(0, "", "", new Intent()),
                new Demo(1, "divider", "divider", null));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LogUtility.e("a", "1");
        getBundle(getIntent());
        initViews();
        bindListeners();
    }

    @Override
    protected void getBundle(@NotNull Intent intent) {
        demos = new ArrayList<>();
        demos.addAll(demoList);
    }

    @Override
    protected void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        if (swipeRefreshLayout != null) {
            if (BuildUtility.isRequired(Build.VERSION_CODES.M))
                swipeRefreshLayout.setColorSchemeColors(
                        getColor(R.color.colorPrimary),
                        getColor(R.color.colorPrimaryGreen),
                        getColor(R.color.colorPrimaryOrange),
                        getColor(R.color.colorPrimaryPurple)
                );
            else
                swipeRefreshLayout.setColorSchemeColors(
                        getResources().getColor(R.color.colorPrimary),
                        getResources().getColor(R.color.colorPrimaryGreen),
                        getResources().getColor(R.color.colorPrimaryOrange),
                        getResources().getColor(R.color.colorPrimaryPurple)
                );
        }

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            initAdapter();
        }
    }

    private void initAdapter() {
        adapter = new DemoAdapter(mContext, demos);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void bindListeners() {
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                demos.clear();
                demos.addAll(demoList);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                        if (swipeRefreshLayout.isRefreshing())
                            swipeRefreshLayout.setRefreshing(false);
                    }
                }, 300);
            }
        }).start();
    }

    @Override
    protected void handleMessage(HandlerActivity activity, Message msg) {

    }
}
