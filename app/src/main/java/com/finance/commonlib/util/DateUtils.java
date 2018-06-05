package com.finance.commonlib.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Jackie on 2018/3/6.
 */

public class DateUtils {

    static Date date;
    static SimpleDateFormat time;
    static int hours;
    static int minutes;
    public static String getCurrentDate(){
        date = new Date();
        time = new SimpleDateFormat("yyyyMMddHHmm");
        return time.format(date);
    }
    public static String getCurrentDateDay(){
        date = new Date();
        time = new SimpleDateFormat("yyyyMMdd");
        return time.format(date);
    }
    public static String getMyDate(Date date){
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        return (df.format(date));
    }
    private static int[] mDateInt = {3};
    public static int[] getDateInt(Date date){
        Calendar ca = Calendar.getInstance();

        ca.setTime(date);

        int day = ca.get(Calendar.DAY_OF_YEAR);//一年中的第几天

//        int week = ca.get(Calendar.WEEK_OF_YEAR);//一年中的第几周

        int month = ca.get(Calendar.MONTH);//第几个月

        int year = ca.get(Calendar.YEAR);//年份数值

        mDateInt[0] = year;
        mDateInt[1] = month;
        mDateInt[2] = day;
        return mDateInt;
    }

    /**
     * 大于9:30
     * @return
     */
    public static boolean isCurrentTime() {
        date = new Date();
        hours = date.getHours();
        minutes = date.getMinutes();
//        Logger.i("jc----------时间",hours+"--------");
//        if ((hours >= 9 && minutes >= 30) || hours >= 10) {
            return true;
//        }
//        return false;
    }

}
