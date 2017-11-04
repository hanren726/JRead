package com.minglei.jread;

import android.app.Application;
import android.content.Context;

/**
 * Created by minglei on 2017/11/4.
 */

public class JApplication extends Application {

    private static final String TAG = JApplication.class.getSimpleName();

    private static Context sAppCtx;

    public static Context getAppContext() {
        return sAppCtx;
    }

    public static void setContext(Context context) {
        sAppCtx = context;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        sAppCtx = this;
    }
}
