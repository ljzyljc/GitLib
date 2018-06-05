package com.finance.commonlib;

import android.app.Application;

/**
 * Created by Jackie on 2018/6/5.
 */
public class BaseApplication extends Application {

    public static BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
    public static BaseApplication getInstance() {
        return instance;
    }

}
