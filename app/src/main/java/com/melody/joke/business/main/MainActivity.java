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
import com.melody.joke.business.activity.NavigationDrawerActivity;
import com.melody.joke.business.activity.ScrollingActivity;
import com.melody.joke.business.activity.TabbedActivity;

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
                        "Empty Activity",
                        "\t\tAn empty activity from Android official.",
                        new Intent(mContext, EmptyActivity.class)),
                new Demo(0,
                        "Basic Activity",
                        "\t\tA basic activity from Android official. Provide a fab.",
                        new Intent(mContext, BasicActivity.class)),
                new Demo(0,
                        "Scrolling Activity",
                        "\t\tA scrolling activity from Android official. Provide an expandable toolbar.",
                        new Intent(mContext, ScrollingActivity.class)),
                new Demo(0,
                        "Navigation Drawer Activity",
                        "\t\tA navigation drawer activity from Android official. Provide a drawer swiped out from left. Co-operating with fragments will be better.",
                        new Intent(mContext, NavigationDrawerActivity.class)),
                new Demo(0,
                        "Tabbed Activity",
                        "\t\tA tabbed activity from Android official. Provide a tabbed framework. Co-operating with fragments will be better.",
                        new Intent(mContext, TabbedActivity.class)),
                new Demo(0,
                        "Fullscreen Activity",
                        "\t\tA fullscreen activity from Android official. Hide navigation bar.",
                        null),
                new Demo(0,
                        "Settings Activity",
                        "\t\tA settings activity from Android official. Will have different appearance in different devices.",
                        null),
                new Demo(0,
                        "Login Activity",
                        "\t\tA login activity from Android official. Provide an AsyncTask to login.",
                        null),
                new Demo(0,
                        "Master/Detail Flow",
                        "\t\tA mater or detail flow activity from Android official. Provide an auto-fit activity to show master and detail.",
                        null),

                new Demo(2, "Fragment Samples", "title", null),
                new Demo(0, "", "", null),
                new Demo(0, "", "", null),
                new Demo(0, "", "", null),

                new Demo(2, "RecyclerView Demos", "title", null),
                new Demo(0,
                        "ListView like",
                        "\t\tListView like RecyclerView with refresh and load more. SwipeRefreshLayout to implement refresh operation. Another type of item to implement load-more operation. ItemTouchHelper to handle touch operations.",
                        null),
                new Demo(0,
                        "Chat list",
                        "\t\tA practical implementation of ListView like RecyclerView: Chat list.",
                        null),

                new Demo(2, "Volley Demo", "title", null),
                new Demo(0,
                        "Volley Demo",
                        "\t\tVolley is a lightweight network framework from Google. Suitable for light and frequent net requests. This demo is a simple test for the combination of Volley, OkHttp3 and Gson.",
                        null),

                new Demo(2, "Retrofit Demo", "title", null),
                new Demo(0,
                        "Retrofit Demo",
                        "\t\tRetrofit is a network framework from Square. Work well with OkHttp3 from Square. This demo is a simple test for the combination of Retrofit, OkHttp3 and Gson.",
                        null),

                new Demo(2, "Fresco Demo", "title", null),
                new Demo(0,
                        "Fresco Demo",
                        "\t\tFresco is a image framework from Facebook. This demo is a simple test for Fresco.",
                        null),

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
