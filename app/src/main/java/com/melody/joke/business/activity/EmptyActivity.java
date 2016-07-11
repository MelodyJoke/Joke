package com.melody.joke.business.activity;

import android.content.Intent;
import android.os.Bundle;

import com.melody.base.template.activity.BaseActivity;
import com.melody.joke.R;

import org.jetbrains.annotations.NotNull;

public class EmptyActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);

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
}
