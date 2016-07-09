package com.melody.joke.business;

import com.melody.base.template.application.BaseApplication;
import com.melody.base.template.application.UncaughtExceptionHandler;
import com.melody.volley.VolleyManager;

/**
 * description: application
 * author: Melody
 * date: 2016/7/9
 * version: 0.0.0.1
 */
@SuppressWarnings("WeakerAccess")
public class JokeApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        // init volley here
        VolleyManager.init(this);
    }

    @Override
    public UncaughtExceptionHandler initUncaughtExceptionHandler() {
        return new JokeUncaughtExceptionHandler();
    }
}
