package com.finance.commonlib.util;

import android.graphics.Bitmap;
import android.view.View;


/**
 * 主要用于封装UtilAssets里的getBitmapBitmapFromAssets 和 setBackGraound()方法。而且是最长的那个setBackGraound()方法。
 * 设置背景图片，背景图片会拉伸到控件同等大小。背景图片始终和控件同等大小。所以只要对控件进行适配即可。图片保持原图。
 * view.setBackground(ninePatchDrawable);经查阅后才知道.9只针对background来进行拉伸
 * Created by 彭治铭 on 2018/1/15.
 */

public class AssetsBuilder {
    public static AssetsBuilder assetsBuilder;

    //调用案例：AssetsBuilder.Buidler(ok).setFileName("dialog/auditDialog.png").execute(activity);

    //view是基础，buider的时候，必须传人。如果只是调用getBitmapBitmapFromAssets()获取位图，则不需要传View，可以为空。
    public static AssetsBuilder Buidler(View view) {
        if (assetsBuilder == null) {
            assetsBuilder = new AssetsBuilder();
        }
        assetsBuilder.resID = 0;
        assetsBuilder.fileName = null;
        if (view != null) {
            assetsBuilder.width = view.getLayoutParams().width;
            if (assetsBuilder.width < view.getWidth()) {
                assetsBuilder.width = view.getWidth();
            }
            assetsBuilder.height = view.getLayoutParams().height;
            if (assetsBuilder.height < view.getHeight()) {
                assetsBuilder.height = view.getHeight();
            }
        }
        assetsBuilder.isRepeatAdapter = false;
        assetsBuilder.isRGB_565 = true;
        assetsBuilder.isadapter = false;//默认对控件不做适配。应该交给activity和dialog自动化去适配


        assetsBuilder.gennerName = null;
        assetsBuilder.pressName = null;
        assetsBuilder.gennerID = 0;
        assetsBuilder.pressID = 0;
        assetsBuilder.onTouchListener = null;

        return assetsBuilder;
    }

    private AssetsBuilder() {
    }

    //设置本地位图
    public void setBackGraoundBitmap(View view) {
        AssetsUtils.getInstance().setBackGraound(view, fileName, resID, width, height, isRepeatAdapter, isRGB_565, isadapter);
    }

    //获取本地位图
    public Bitmap getBitmapBitmapFromAssets() {
        return AssetsUtils.getInstance().getBitmapFromAssets( fileName, resID, isRGB_565);
    }

    /**
     * 获取本地位图
     * @param view 如果View不为空，将位图压缩到和View同等大小。
     * @return
     */
    public Bitmap getBitmapBitmapFromAssets(View view) {
        Bitmap bitmap= AssetsUtils.getInstance().getBitmapFromAssets( fileName, resID, isRGB_565);
        if(view!=null){
            int width=view.getLayoutParams().width>view.getWidth()?view.getLayoutParams().width:view.getWidth();
            int height=view.getLayoutParams().height>view.getHeight()?view.getLayoutParams().height:view.getHeight();
            if(width>0&&height>0){
                bitmap=getBitmapBitmapFromAssets(width,height);
            }
        }
        return bitmap;
    }

    /**
     * 获取本地位图,并对图片进行压缩
     *
     * @param widht   压缩后的宽度
     * @param height  压缩后的高度
     * @return
     */
    public Bitmap getBitmapBitmapFromAssets( int widht, int height) {
        return getBitmapBitmapFromAssets(widht,height,false);
    }

    /**
     * 获取本地位图,并对图片进行压缩
     * @param widht 压缩后的宽度
     * @param height 压缩后的高度
     * @param isAdapterBitmap true对位图进行适配。就以传入的width和height为标准进行适配。false不适配。返回位图的宽和高就是传入的宽和高。
     * @return
     */
    public Bitmap getBitmapBitmapFromAssets( int widht, int height, Boolean isAdapterBitmap) {
        Bitmap bitmap = AssetsUtils.getInstance().getBitmapFromAssets(fileName, resID, isRGB_565);
        if(isAdapterBitmap){
            bitmap= ProportionUtils.getInstance().adapterBitmap(bitmap,widht,height);
        }else {
            bitmap= ProportionUtils.getInstance().scaleBitmap(bitmap, widht, height);
        }
        return bitmap;
    }

    private String fileName;//asstes文件下的图片名称。如:"文件夹名/nicks2.png",或 "nicks2.png" 直接在assets共目录下。
    private int resID = 0;//mipmap等资源下的图片ID。当fileName为空时才有效。
    private int width;//宽度。控件以这个宽度为基础进行适配
    private int height;//高度。控件以这个高度为基础进行适配
    private boolean isRepeatAdapter = false;//是否强制适配。true每次都重新适配。false只适配一次【默认就是false】，isadapter为true时，isRepeatAdapter才有效果。
    private boolean isRGB_565 = true;//图片模式是否为RGB_566，默认就是。true是[内存省一半]。false为ARGB_8888[耗内存，效果好]
    private boolean isadapter = false;//是否需要适配控件大小,true对控件进行适配。false对控件不做适配。默认就是不做适配。适配交给了activity或dialog的自动化适配。isadapter优先级比isRepeatAdapter高。

    //设置触摸时的位图
    public void setOnTouchBitmap(View view) {
        AssetsUtils.getInstance().setOnTouch( view, gennerName, pressName, gennerID, pressID, onTouchListener, isRGB_565, isRepeatAdapter, isadapter);
    }

    private String gennerName;//默认图片,assets文件夹下
    private String pressName;//点击按下图片
    int gennerID = 0;//默认图片，res资源文件夹下，如mipmap，drawable
    int pressID = 0;//点击按下图片
    private View.OnTouchListener onTouchListener = null;//触摸事件，防止原有触摸事件无效，兼容原有触摸事件。

    public AssetsBuilder setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public AssetsBuilder setResID(int resID) {
        this.resID = resID;
        return this;
    }

    public AssetsBuilder setWidth(int width) {
        this.width = width;
        return this;
    }

    public AssetsBuilder setHeight(int height) {
        this.height = height;
        return this;
    }

    public AssetsBuilder setRepeatAdapter(boolean repeatAdapter) {
        isRepeatAdapter = repeatAdapter;
        return this;
    }

    public AssetsBuilder setRGB_565(boolean RGB_565) {
        isRGB_565 = RGB_565;
        return this;
    }

    public AssetsBuilder setIsadapter(boolean isadapter) {
        this.isadapter = isadapter;
        return this;
    }

    public AssetsBuilder setGennerName(String gennerName) {
        this.gennerName = gennerName;
        return this;
    }

    public AssetsBuilder setPressName(String pressName) {
        this.pressName = pressName;
        return this;
    }

    public AssetsBuilder setGennerID(int gennerID) {
        this.gennerID = gennerID;
        return this;
    }

    public AssetsBuilder setPressID(int pressID) {
        this.pressID = pressID;
        return this;
    }

    public AssetsBuilder setOnTouchListener(View.OnTouchListener onTouchListener) {
        this.onTouchListener = onTouchListener;
        return this;
    }
}
