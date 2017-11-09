package com.minglei.jread.net;

import com.minglei.jread.JApplication;

import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by minglei on 2017/11/9.
 */

public class RetrofitHelper {

    private static final int DEFAULT_TIME = 5;
    public static final String BASE_URL = "baseUrl";

    private static HashMap<String, Object> sServicemap = new HashMap<>();

    private static volatile RetrofitHelper mInstance = null;

    private RetrofitHelper() {

    }

    public static RetrofitHelper getInstance() {
        if (mInstance == null) {
            synchronized (RetrofitHelper.class) {
                if (mInstance == null) {
                    mInstance = new RetrofitHelper();
                }
            }
        }
        return mInstance;
    }

    public <S> S getService(Class<S> serviceClass) {
        if (sServicemap.containsKey(serviceClass.getName())) {
            return (S) sServicemap.get(serviceClass.getName());
        } else {
            Object o = createService(serviceClass);
            sServicemap.put(serviceClass.getName(), o);
            return (S) o;
        }
    }

    public <S> S createService(Class<S> serviceClass) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor);
        //time out
        httpClient.connectTimeout(DEFAULT_TIME, TimeUnit.SECONDS);
        httpClient.writeTimeout(DEFAULT_TIME, TimeUnit.SECONDS);
        httpClient.readTimeout(DEFAULT_TIME, TimeUnit.SECONDS);
        //cache
        File httpCacheDirectory = new File(JApplication.getAppContext().getCacheDir(), "OkHttpCache");
        httpClient.cache(new Cache(httpCacheDirectory, 10 * 1024 * 1024));

        return createService(serviceClass, httpClient.build());
    }

    private <S> S createService(Class<S> serviceClass, OkHttpClient okHttpClient) {
        String baseUrl = "";
        try {
            Field field = serviceClass.getField(BASE_URL);
            baseUrl = (String) field.get(serviceClass);
        } catch (NoSuchFieldException e) {
            e.printStackTrace(); //TODO Log工具类
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofit.create(serviceClass);
    }

}
