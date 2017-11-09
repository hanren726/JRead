package com.minglei.jread.utils;

import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;

import com.minglei.jread.JApplication;

import java.util.Locale;

/**
 * Created by minglei on 2017/11/8.
 */

public class DimentionUtil {
    private static float ratio;
    private static int sWidth;
    private static int sHeight;
    public static final String MODEL = Build.MODEL.toLowerCase(Locale.US);

    static {
        calcuateRatio();
    }

    public static int getDimen(int resId) {
        float size = JApplication.getAppContext().getResources()
                .getDimension(resId);
        return (int) size;
    }

    public static int getTextSize(int resId) {
        float size = JApplication.getAppContext().getResources()
                .getDimension(resId);
        return scaleDimen(size);
    }

    public static int scaleDimen(float size) {
        return Math.round(size * ratio);
    }

    public static void calcuateRatio() {
        ratio = 1;
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windM = (WindowManager) JApplication.getAppContext()
                .getSystemService(Context.WINDOW_SERVICE);
        windM.getDefaultDisplay().getMetrics(metrics);
        float xdpi = metrics.xdpi;
        float ydpi = metrics.ydpi;
        int xpixel = metrics.widthPixels;
        int ypixel = metrics.heightPixels;
        sWidth = xpixel;
        sHeight = ypixel;
        double diagPixel2 = Math.pow((double) xpixel, 2.0) + Math.pow((double) ypixel, 2.0);
        int diagPixel = (int) Math.pow(
                diagPixel2, 0.5);
        double xsize = ((double) xpixel) / (double) xdpi;
        double ysize = (double) ypixel / (double) ydpi;
        double diagnalSize2 = Math.pow(xsize, 2.0) + Math.pow(ysize, 2.0);
        float diagnalSize = (float) Math.pow(
                diagnalSize2, 0.5);
        double realDPI = Math.pow(diagPixel2 / diagnalSize2, 0.5);
        double usedDensity = metrics.densityDpi;
        if (metrics.densityDpi == 480 || metrics.densityDpi == metrics.DENSITY_XHIGH) {
            usedDensity = metrics.densityDpi == 480 ? 440 : metrics.densityDpi;
            ratio = (float) ((realDPI) / usedDensity);

            if (ratio < 0.9 || ratio > 1.12) {
                ratio = 1;
            }
        }
        String.format(Locale.US,
                "display attrs: xdpi: %f, ydpi: %f, xpixel: %d, ypixel: %d, diagPixel: %d, xsize: %f, ysize: %f, diagSize: %f, realDPI: %f, densityDPI: %d, ratio: %f",
                xdpi, ydpi, xpixel, ypixel, diagPixel, xsize, ysize,
                diagnalSize, realDPI, metrics.densityDpi, ratio);
        if (MODEL.equals("m35x") || MODEL.startsWith("gt-n7") ||
                MODEL.equals("nx40x") || MODEL.equals("shv-e250k") ||
                MODEL.equals("sch-n719") || MODEL.equals("m356")) {
            ratio = 1.07f;
        }

        if (MODEL.startsWith("gt-i93")) {
            ratio = 1.07f;
        }

        if (MODEL.equals("gt-i9200")) {
            ratio = 1.20f;
        }

        if (MODEL.equals("tcl y910t")) {
            ratio = 0.93f;
        }

        //TODO 自己包装的prefUtil
//        int userSetting = PrefUtil.getKeyInt(PrefKeys.CHARACTER_SIZE, 0);
//        if (userSetting == -1) {
//            ratio*=0.9;
//        } else if(userSetting == 1) {
//            ratio*=1.1;
//        }else if (userSetting == 2) {
//            ratio*=1.2;
//        }
    }

    public static int getFullWidth() {
        return sWidth;
    }

    public static int getFullHeight() {
        return sHeight;
    }

    public static int dp2px(float dpValue) {
        float scale = JApplication.getAppContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5F);
    }

    public static int dp2px(int dp) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                JApplication.getAppContext().getResources().getDisplayMetrics()
        );
    }

    public static int px2dp(int px) {
        return (int) (px / JApplication.getAppContext().getResources().getDisplayMetrics().density);
    }
}
