package com.finance.commonlib;

import android.app.Application;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

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

    //状态栏是否为黑色,默认为true黑色。
    public boolean darkmode = true;
    public void setStatusBarDrak(Window window, boolean darkmode) {
        setAndroidStatusBarkMode(window, darkmode);
        setMiuiStatusBarDarkMode(window, darkmode);
        setFlyMeStatusBarDarkMode(window, darkmode);
        if(Build.VERSION.SDK_INT >=21){
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private void setAndroidStatusBarkMode(Window window, boolean darkmode) {
        if (Build.VERSION.SDK_INT >= 19) {//19是4.4
            if (darkmode) {
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//深色，一般为黑色
            } else {
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);//浅色，一般为白色
            }
        }
    }

    private boolean setMiuiStatusBarDarkMode(Window window, boolean darkmode) {
        Class<? extends Window> clazz = window.getClass();
        try {
            int darkModeFlag = 0;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(window, darkmode ? darkModeFlag : 0, darkModeFlag);
            return true;
        } catch (Exception e) {
            //Log.e("test", "Miui状态栏字体颜色修改失败:\t" + e.getMessage());
        }
        return false;
    }

    private void setFlyMeStatusBarDarkMode(Window window, boolean darkmode) {
        WindowManager.LayoutParams lp = window.getAttributes();
        try {
            Class<?> instance = Class.forName("android.view.WindowManager$LayoutParams");
            int value = instance.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON").getInt(lp);
            Field field = instance.getDeclaredField("meizuFlags");
            field.setAccessible(true);
            int origin = field.getInt(lp);
            if (darkmode) {
                field.set(lp, origin | value);
            } else {
                field.set(lp, (~value) & origin);
            }
        } catch (Exception e) {
            //Log.e("test", "魅族状态栏字体颜色修改失败:\t" + e.getMessage());
        }
    }
}
