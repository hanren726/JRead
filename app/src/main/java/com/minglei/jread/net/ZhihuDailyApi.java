package com.minglei.jread.net;

import com.minglei.jread.beans.zhihu.ZhihuDailyContentBean;
import com.minglei.jread.beans.zhihu.ZhihuLatestNews;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by minglei on 2017/11/10.
 */

public interface ZhihuDailyApi {

    String baseUrl = "http://news-at.zhihu.com/api/4/";

    @GET("news/latest")
    Observable<ZhihuLatestNews> getLatestNews();

    @GET("news/before/{time}")
    Observable<ZhihuLatestNews> getBeforetNews(@Path("time") String time);

    @GET("news/{id}")
    Observable<ZhihuDailyContentBean> getDetailNews(@Path("id") String id);
}
