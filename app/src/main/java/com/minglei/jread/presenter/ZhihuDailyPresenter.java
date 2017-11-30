package com.minglei.jread.presenter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.minglei.jread.activity.ZhihuDailyNewsWebActivity;
import com.minglei.jread.beans.zhihu.StoriesBean;
import com.minglei.jread.beans.zhihu.ZhihuLatestNews;
import com.minglei.jread.fragments.adapter.ZhihudailyAdapter;
import com.minglei.jread.fragments.interfaces.IZhihuDailyView;
import com.minglei.jread.net.ApiFactory;
import com.minglei.jread.net.ZhihuDailyApi;
import com.minglei.jread.utils.JLog;

import java.util.ArrayList;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by minglei on 2017/11/13.
 */

public class ZhihuDailyPresenter{

    public static final String TAG = ZhihuDailyPresenter.class.getSimpleName();

    public static final ZhihuDailyApi mZhihuApi = ApiFactory.getZhihuDailyApi();

    private IZhihuDailyView iZhihuDailyView;
    private Context mContext;

    private RecyclerView mRecyclerView;
    private ZhihudailyAdapter mAdapter;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();

    private ArrayList<ZhihuLatestNews> allNews = new ArrayList<>();

    private String mLatestTime;

    public ZhihuDailyPresenter(Context context, IZhihuDailyView iZhihuDailyView) {
        this.mContext = context;
        this.iZhihuDailyView = iZhihuDailyView;
    }

    public void getDailyNews() {
        mRecyclerView = iZhihuDailyView.getRecyclerView();
        mAdapter = iZhihuDailyView.getAdapter();
        Subscription subscription = mZhihuApi.getLatestNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ZhihuLatestNews>() {
                    @Override
                    public void onCompleted() {
                        JLog.i(TAG, "getDailyNews onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        JLog.e(TAG, "getDailyNews onError : [%s]", e);
                        iZhihuDailyView.getRefreshLayout().setVisibility(View.GONE);
                        iZhihuDailyView.getErrorView().setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onNext(ZhihuLatestNews zhihuLatestNews) {
                        JLog.i(TAG, "zhihuLatestNews :[%s], time : [%s]", zhihuLatestNews,
                                zhihuLatestNews.getDate());
                        iZhihuDailyView.getRefreshLayout().setVisibility(View.VISIBLE);
                        iZhihuDailyView.getErrorView().setVisibility(View.GONE);
                        allNews.clear();
                        allNews.add(zhihuLatestNews);
                        mAdapter.setData(allNews);
                        mAdapter.mShowSelectedDay = false;
                        mRecyclerView.setAdapter(mAdapter);
                        mLatestTime = zhihuLatestNews.getDate();
                    }
                });
        mSubscriptions.add(subscription);
    }

    public void getBeforeNews() {
        mRecyclerView = iZhihuDailyView.getRecyclerView();
        mAdapter = iZhihuDailyView.getAdapter();
        Subscription subscription = mZhihuApi.getBeforetNews(mLatestTime)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ZhihuLatestNews>() {
                    @Override
                    public void onCompleted() {
                        JLog.i(TAG, "getBeforeNews onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        JLog.e(TAG, "getBeforeNews onError : [%s]", e);
                        iZhihuDailyView.getRefreshLayout().setVisibility(View.GONE);
                        iZhihuDailyView.getErrorView().setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onNext(ZhihuLatestNews zhihuLatestNews) {
                        JLog.i(TAG, "zhihuLatestNews :[%s], time : [%s]", zhihuLatestNews,
                                zhihuLatestNews.getDate());
                        iZhihuDailyView.getRefreshLayout().setVisibility(View.VISIBLE);
                        iZhihuDailyView.getErrorView().setVisibility(View.GONE);
                        allNews.add(zhihuLatestNews);
                        mAdapter.updateData(allNews);
                        mAdapter.notifyDataSetChanged();
                        mLatestTime = zhihuLatestNews.getDate();
                    }
                });
        mSubscriptions.add(subscription);
    }

    public void getSelectedDayNews(String day) {
        mRecyclerView = iZhihuDailyView.getRecyclerView();
        mAdapter = iZhihuDailyView.getAdapter();
        Subscription subscription = mZhihuApi.getBeforetNews(day)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ZhihuLatestNews>() {
                    @Override
                    public void onCompleted() {
                        JLog.i(TAG, "getBeforeNews onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        JLog.e(TAG, "getBeforeNews onError : [%s]", e);
                    }

                    @Override
                    public void onNext(ZhihuLatestNews zhihuLatestNews) {
                        JLog.i(TAG, "zhihuLatestNews :[%s], time : [%s]", zhihuLatestNews,
                                zhihuLatestNews.getDate());
                        allNews.clear();
                        allNews.add(zhihuLatestNews);
                        mAdapter.updateData(allNews);
                        mAdapter.mShowSelectedDay = true;
                        mAdapter.notifyDataSetChanged();
                        mLatestTime = zhihuLatestNews.getDate();
                    }
                });
        mSubscriptions.add(subscription);
    }

    public void viewNews(StoriesBean storiesBean) {
        Intent intent = ZhihuDailyNewsWebActivity.getStartIntent();
        intent.putExtra(ZhihuDailyNewsWebActivity.BUNDLE_ID, storiesBean.getId());
        mContext.startActivity(intent);
    }

    public void onUnInit() {
        Log.i(TAG, "onUnInit :");
        this.mSubscriptions.clear();
    }

}
