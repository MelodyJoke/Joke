package com.melody.joke;

import android.content.Intent;
import android.os.Message;
import android.os.Bundle;

import com.melody.base.template.activity.HandlerActivity;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends HandlerActivity {

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

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void bindListeners() {

    }

    @Override
    protected void handleMessage(HandlerActivity activity, Message msg) {

    }
}
