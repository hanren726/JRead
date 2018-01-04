package com.minglei.jread.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by minglei on 2018/1/3.
 */

public class ZhuanlanUtil {

    public static final String PIC_SIZE_XL = "xl";
    public static final String PIC_SIZE_XS = "xs";


    public static final String TEMPLATE_ID = "{id}";
    public static final String TEMPLATE_SIZE = "{size}";

    private static final int MINUTE = 60;
    private static final int HOUR = MINUTE * 60;
    private static final int DAY = HOUR * 24;
    private static final int MONTH = DAY * 30;
    private static final int YEAR = MONTH * 12;
    private static final DateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

    public static String getAuthorAvatarUrl(String origin, String userId, String size) {
        origin = origin.replace(TEMPLATE_ID, userId);
        return origin.replace(TEMPLATE_SIZE, size);
    }

    public static String convertPublishTime(String time) {
        try {
            long s = TimeUnit.MILLISECONDS.toSeconds(
                    new Date().getTime() - FORMAT.parse(time).getTime());

            long count = s / YEAR;
            if (count != 0) {
                return count + "年前";
            }

            count = s / MONTH;
            if (count != 0) {
                return count + "月前";
            }

            count = s / DAY;
            if (count != 0) {
                return count + "天前";
            }

            return "今天";
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "未知时间";
    }
}
