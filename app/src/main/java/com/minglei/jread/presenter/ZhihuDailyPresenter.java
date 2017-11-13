package com.minglei.jread.presenter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.minglei.jread.beans.zhihu.ZhihuLatestNews;
import com.minglei.jread.fragments.adapter.ZhihuDailyListAdapter;
import com.minglei.jread.fragments.interfaces.IZhihuDailyView;
import com.minglei.jread.net.ApiFactory;
import com.minglei.jread.net.ZhihuDailyApi;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
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
    private ZhihuDailyListAdapter mAdapter;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();

    public ZhihuDailyPresenter(Context context, IZhihuDailyView iZhihuDailyView) {
        this.mContext = context;
        this.iZhihuDailyView = iZhihuDailyView;
    }

    public void getDailyNews() {
        mRecyclerView = iZhihuDailyView.getRecyclerView();
        mAdapter = new ZhihuDailyListAdapter(mContext);
        Subscription subscription = mZhihuApi.getLatestNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ZhihuLatestNews>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ZhihuLatestNews zhihuLatestNews) {
                        mAdapter.setData(zhihuLatestNews);
                        mRecyclerView.setAdapter(mAdapter);
                    }
                });
        mSubscriptions.add(subscription);
    }


    public void onUnInit() {
        Log.i(TAG, "onUnInit :");
        this.mSubscriptions.clear();
    }

}
