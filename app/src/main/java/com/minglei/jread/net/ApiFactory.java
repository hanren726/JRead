package com.minglei.jread.net;

/**
 * Created by minglei on 2017/11/10.
 */

public class ApiFactory {

    protected static final Object monitor = new Object();

    private static ZhihuDailyApi zhihuDailyApi = null;
    private static QiuBaiApi qiuBaiApi = null;
    private static GanHuoApi ganHuoApi = null;

    public static ZhihuDailyApi getZhihuDailyApi() {
        synchronized (monitor) {
            if (zhihuDailyApi == null) {
                zhihuDailyApi = RetrofitHelper.getInstance().getService(ZhihuDailyApi.class);
            }
            return zhihuDailyApi;
        }
    }

    public static QiuBaiApi getQiuBaiApi() {
        synchronized (monitor) {
            if (qiuBaiApi == null) {
                qiuBaiApi = RetrofitHelper.getInstance().getService(QiuBaiApi.class);
            }
            return qiuBaiApi;
        }
    }

    public static GanHuoApi getGanHuoApi() {
        synchronized (monitor) {
            if (ganHuoApi == null) {
                ganHuoApi = RetrofitHelper.getInstance().getService(GanHuoApi.class);
            }
            return ganHuoApi;
        }
    }
}
