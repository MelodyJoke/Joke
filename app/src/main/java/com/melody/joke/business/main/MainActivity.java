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
import com.melody.joke.R;
import com.melody.joke.bean.Demo;
import com.melody.joke.business.activity.BasicActivity;
import com.melody.joke.business.activity.EmptyActivity;
import com.melody.joke.business.activity.ScrollingActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("WeakerAccess, deprecation")
public class MainActivity extends HandlerActivity implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipeRefreshLayout;

    private RecyclerView recyclerView;

    private DemoAdapter adapter;

    private List<Demo> demos, demoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getBundle(getIntent());
        initViews();
        bindListeners();
    }

    @Override
    protected void getBundle(@NotNull Intent intent) {
        demos = new ArrayList<>();
        demoList = new ArrayList<>();
        Collections.addAll(demoList,
                new Demo(1, "divider", "divider", null),

                new Demo(2, "Activity Templates", "title", null),
                new Demo(0,
                        "EmptyActivity",
                        "\t\tAn empty activity from Android official.",
                        new Intent(mContext, EmptyActivity.class)),
                new Demo(0,
                        "BasicActivity",
                        "\t\tA basic activity from Android official. Provide a fab.",
                        new Intent(mContext, BasicActivity.class)),
                new Demo(0,
                        "ScrollingActivity",
                        "\t\tA scrolling activity from Android official. Provide an expandable toolbar.",
                        new Intent(mContext, ScrollingActivity.class)),
                new Demo(0,
                        "",
                        "",
                        null),
                new Demo(0,
                        "",
                        "",
                        null),
                new Demo(0,
                        "",
                        "",
                        null),

                new Demo(2, "Fragment", "title", null),
                new Demo(0, "", "", null),
                new Demo(0, "", "", null),
                new Demo(2, "CoordinatorLayout", "title", null),
                new Demo(0, "", "", null),
                new Demo(0, "", "", null),
                new Demo(2, "RecyclerView", "title", null),
                new Demo(0, "", "", null),
                new Demo(0, "", "", null),
                new Demo(2, "Http-Volley", "title", null),
                new Demo(0, "", "", null),
                new Demo(2, "Http-Retrofit", "title", null),
                new Demo(0, "", "", null),
                new Demo(1, "divider", "divider", null));

        demos.addAll(demoList);
    }

    @Override
    protected void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
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

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
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
