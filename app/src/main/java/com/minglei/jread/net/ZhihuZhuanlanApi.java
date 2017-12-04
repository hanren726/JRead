package com.minglei.jread.net;

import com.minglei.jread.beans.zhihu.zhuanlan.Post;
import com.minglei.jread.beans.zhihu.zhuanlan.User;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by minglei on 2017/12/1.
 */

public interface ZhihuZhuanlanApi {

    String baseUrl = "https://zhuanlan.zhihu.com";

    @GET("/api/columns/{id}/posts")
    Observable<List<Post>> getPosts(@Path("id") String id, @Query("limit") int limit, @Query("offset") int offset);

    @GET("/api/columns/{id}")
    Observable<User> getUser(@Path("id") String id);
}
