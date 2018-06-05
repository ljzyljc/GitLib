package com.finance.commonlib.util;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.finance.commonlib.R;


/**
 * Toast util class.
 *
 * @author <A href="http://jb51.net">http://jb51.net</A>
 * @version 2011/11/30
 */
public class ToastUtils {


    public static void show(final Activity activity, final String text) {
        if (activity != null && !activity.isFinishing()) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    showToast(activity, text);
                }
            });
        }
    }

    public static void showToast(Context mContext, String text) {

        // mToast = Toast.makeText(mContext, text, Toast.LENGTH_SHORT);
        Toast mToast = new Toast(mContext);
        mToast.setGravity(Gravity.CENTER | Gravity.BOTTOM, 0, 260);// 显示位置
        mToast.setDuration(Toast.LENGTH_SHORT);// 显示时长，1000为1秒
        View view = getView(mContext, R.layout.layout_toast);
        TextView textView = (TextView) view.findViewById(R.id.crown_tost_text);
        textView.setText(text);
        mToast.setView(view);// 自定义view
        mToast.show();
        mToast = null;
    }

    public static void showToast(Context mContext, int resId) {
        showToast(mContext, mContext.getResources().getString(resId));
    }

    public static View getView(Context context, int layoutId) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(layoutId, null);
        return view;
    }
    public static void showMarketToast(Context mContext){
        Toast toast = new Toast(mContext);
        View view = getView(mContext,R.layout.activity_popup);
        toast.setGravity(Gravity.FILL,0,0);
        toast.setView(view);
        toast.show();
    }
}  
