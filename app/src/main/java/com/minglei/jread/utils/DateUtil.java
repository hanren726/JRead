package com.minglei.jread.utils;

import android.text.format.DateFormat;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by minglei on 2017/11/27.
 */

public class DateUtil {

    /**
     * 输入20171127
     * 输出2017年11月27日 星期一
     * @param date
     * @return
     */
    public static String getFormatDate(String date) {
        String year = date.substring(0, 4);
        String month = date.substring(4, 6);
        String day = date.substring(6);
        Date time = new Date(Integer.parseInt(year),
                Integer.parseInt(month),
                Integer.parseInt(day));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        int dayIndex = calendar.get(Calendar.DAY_OF_WEEK);
        if (dayIndex < 1 || dayIndex > 7) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(year);
        stringBuffer.append("年");
        stringBuffer.append(month);
        stringBuffer.append("月");
        stringBuffer.append(day);
        stringBuffer.append("日");
        stringBuffer.append("\t");
        return WEEK[dayIndex - 1];
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
