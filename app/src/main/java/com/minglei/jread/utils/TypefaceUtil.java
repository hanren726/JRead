package com.minglei.jread.utils;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by minglei on 2017/11/14.
 */

public class TypefaceUtil {

    private static Typeface typefaceWawa;
    private static Typeface typeface55;

    public static void setTypefaceWawa(TextView textView) {
        if (typefaceWawa == null) {
            typefaceWawa = Typeface.createFromAsset(textView.getContext().getAssets(), "fonts/huakangwawati.ttf");
        }
        textView.setTypeface(typefaceWawa);
    }

    public static void set55Typeface(TextView textView) {
        if (typeface55 == null) {
            typeface55 = Typeface.createFromAsset(textView.getContext().getAssets(), "fonts/lishu.ttf");
        }
        textView.setTypeface(typeface55);
    }

}
