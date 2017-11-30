package com.minglei.jread.utils;

import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by minglei on 2017/11/27.
 */

public class DateUtil {

    public static final String TAG = DateUtil.class.getSimpleName();

    /**
     * 输入20171127
     * 输出2017年11月27日 星期一
     *
     * @param date
     * @return
     */
    public static String getFormatDate(String date) {

        String year = date.substring(0, 4);
        String month = date.substring(4, 6);
        String day = date.substring(6, 8);
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, Integer.parseInt(year));
        c.set(Calendar.MONTH, Integer.parseInt(month) - 1);
        c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
        String week = "";
        int weekIndex = c.get(Calendar.DAY_OF_WEEK);
        switch (weekIndex) {
            case 1:
                week = "星期日";
                break;
            case 2:
                week = "星期一";
                break;
            case 3:
                week = "星期二";
                break;
            case 4:
                week = "星期三";
                break;
            case 5:
                week = "星期四";
                break;
            case 6:
                week = "星期五";
                break;
            case 7:
                week = "星期六";
                break;
        }

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(year);
        stringBuffer.append("年");
        stringBuffer.append(month);
        stringBuffer.append("月");
        stringBuffer.append(day);
        stringBuffer.append("日");
        stringBuffer.append("\t");
        stringBuffer.append(week);
        return stringBuffer.toString();
    }

    public static String getRealDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date formatDate = sdf.parse(date, new ParsePosition(0));
        int year = formatDate.getYear();
        int month = formatDate.getMonth() + 1;
        int day = formatDate.getDay();
        return createFormatDate(year, month, day);
    }

    public static String createFormatDate(int year, int month, int day) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(year);
        if (month < 10) {
            stringBuffer.append("0");
        }
        stringBuffer.append(month);
        if (day < 10) {
            stringBuffer.append("0");
        }
        stringBuffer.append(day);
        return stringBuffer.toString();
    }

    public static String getTomorrowForOneDay(String day) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = sdf.parse(day, new ParsePosition(0));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        String res = sdf.format(calendar.getTime());
        return res;
    }

    public static boolean compareDate(String date1, String date2) {
        boolean isBigger = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date dt1 = null;
        Date dt2 = null;
        try {
            dt1 = sdf.parse(date1);
            dt2 = sdf.parse(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (dt1.getTime() > dt2.getTime()) {
            isBigger = true;
        } else if (dt1.getTime() < dt2.getTime()) {
            isBigger = false;
        }
        return isBigger;
    }

    public static String[] WEEK = {
            "星期日",
            "星期一",
            "星期二",
            "星期三",
            "星期四",
            "星期五",
            "星期六"
    };
}
