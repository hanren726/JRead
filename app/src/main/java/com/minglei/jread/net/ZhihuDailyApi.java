package com.minglei.jread.net;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by minglei on 2017/11/10.
 */

public interface ZhihuDailyApi {

    String baseUrl = "http://news-at.zhihu.com/api/4/";

//    @GET("start-image/1080*1920")
//    Observable<SplashImage> getSplashImage();
//
//    @GET("news/latest")
//    Observable<NewsTimeLine> getLatestNews();
//
//    @GET("news/before/{time}")
//    Observable<NewsTimeLine> getBeforetNews(@Path("time") String time);
//
//    @GET("news/{id}")
//    Observable<News> getDetailNews(@Path("id") String id);
}
