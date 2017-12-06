package com.minglei.jread;

import android.app.Application;
import android.content.Context;

import com.minglei.jread.beans.zhihu.zhuanlan.MyObjectBox;

import io.objectbox.BoxStore;


/**
 * Created by minglei on 2017/11/4.
 */

public class JApplication extends Application {

    private static final String TAG = JApplication.class.getSimpleName();

    private static Context sAppCtx;

    public static BoxStore mBoxStore;

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
        mBoxStore = MyObjectBox.builder().androidContext(this).build();
    }

    public BoxStore getBoxStore() {
        return mBoxStore;
    }
}
