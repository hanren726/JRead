package com.minglei.jread.utils;

import android.text.TextUtils;
import android.util.Log;

import java.util.IllegalFormatException;

/**
 * Created by minglei on 2017/11/27.
 */

public class JLog {

    public static void v(String tag, String msg, Object...args) {
        String logContent = makeLogContent(msg, args);
        Log.v(tag, logContent);
    }

    public static void i(String tag, String msg, Object...args) {
        String logContent = makeLogContent(msg, args);
        Log.i(tag, logContent);
    }

    public static void d(String tag, String msg, Object...args) {
        String logContent = makeLogContent(msg, args);
        Log.d(tag, logContent);
    }

    public static void w(String tag, String msg, Object...args) {
        String logContent = makeLogContent(msg, args);
        Log.w(tag, logContent);
    }

    public static void e(String tag, String msg, Object...args) {
        String logContent = makeLogContent(msg, args);
        Log.e(tag, logContent);
    }


    public static String makeLogContent(String msg, Object... args) {
        StringBuilder sb = new StringBuilder();

        if(args != null && args.length != 0) {
            try {
                sb.append(String.format(msg, args));
            } catch (Exception var8) {
                sb.append("#LOG Exception#").append(" ");
                sb.append(var8.getClass().getSimpleName()).append(" ");
                if(TextUtils.isEmpty(var8.getMessage())) {
                    sb.append(var8.getMessage()).append(" ").append(" ");
                }

                if(var8 instanceof IllegalFormatException && args != null) {
                    sb.append("args=[");

                    for(int i = 0; i < args.length; ++i) {
                        Object object = args[i];
                        sb.append("# " + object);
                    }

                    sb.append("]").append(" ");
                }

                sb.append(String.format("originLog=[%s]", new Object[]{msg}));
            }
        } else {
            sb.append(msg);
        }

        return sb.toString();
    }
}
