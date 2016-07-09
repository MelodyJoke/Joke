package com.melody.joke.business;

import android.app.Activity;
import android.content.Intent;

import com.melody.base.template.application.BaseApplication;
import com.melody.base.template.application.UncaughtExceptionHandler;
import com.melody.joke.business.main.MainActivity;

/**
 * description: uncaught exception handler
 * author: Melody
 * date: 2016/7/9
 * version: 0.0.0.1
 */
@SuppressWarnings("WeakerAccess")
public class JokeUncaughtExceptionHandler extends UncaughtExceptionHandler {
    @Override
    protected void subPerform() {
        BaseApplication application = JokeApplication.getInstance();
        Activity activity = application.getCurrentActivity();

        Intent intent = new Intent();
        if (activity != null) intent.setClass(application, activity.getClass());
        else intent.setClass(application, MainActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        BaseApplication.getInstance().startActivity(intent);

        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
