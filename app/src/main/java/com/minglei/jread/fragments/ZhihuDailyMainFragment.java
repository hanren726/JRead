package com.minglei.jread.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.minglei.jread.R;
import com.minglei.jread.base.BaseFragment;
import com.minglei.jread.beans.zhihu.zhihudaily.ZhihuLatestNews;
import com.minglei.jread.fragments.basefragments.LoadingFragment;
import com.minglei.jread.pages.PageState;
import com.minglei.jread.pages.RetryListener;
import com.minglei.jread.utils.FragmentUtil;
import com.minglei.jread.utils.JLog;
import com.minglei.jread.utils.NetworkUtils;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static com.minglei.jread.presenter.ZhihuDailyPresenter.mZhihuApi;


/**
 * Created by minglei on 2018/1/8.
 */

public class ZhihuDailyMainFragment extends BaseFragment implements RetryListener{

    private PageState mPageState;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zhihu_daily_main, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fetchData();
    }

    private void fetchData() {
        Observable.defer(new Func0<Observable<Boolean>>() {
            @Override
            public Observable<Boolean> call() {
                return Observable.just(NetworkUtils.isNetworkAvailable());
            }
        })
                .map(new Func1<Boolean, Boolean>() {

                    @Override
                    public Boolean call(Boolean aBoolean) {
                        JLog.i(TAG, "fetchData : isNetworkAvailable=[%b]", aBoolean);
                        if (!aBoolean) {
                            changeToPage(PageState.Error, ErrorFragment.newInstance(ZhihuDailyMainFragment.class.getSimpleName()));
                        }
                        return aBoolean;
                    }
                })
                .filter(new Func1<Boolean, Boolean>() {
                    @Override
                    public Boolean call(Boolean aBoolean) {
                        JLog.i(TAG, "fetchData : filter isNetworkAvailable=[%b]", aBoolean);
                        if (aBoolean) {
                            changeToPage(PageState.Loading, LoadingFragment.newInstance(ZhihuDailyMainFragment.class.getSimpleName()));

                        }
                        return aBoolean;
                    }
                })
                .flatMap(new Func1<Boolean, Observable<ZhihuLatestNews>>() {
                    @Override
                    public Observable<ZhihuLatestNews> call(Boolean aBoolean) {
                        return mZhihuApi.getLatestNews();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ZhihuLatestNews>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        changeToPage(PageState.Error, ErrorFragment.newInstance(ZhihuDailyMainFragment.class.getSimpleName()));
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(ZhihuLatestNews zhihuLatestNews) {
                        if (zhihuLatestNews != null) {
                            JLog.i(TAG, "fetchData : ZhihuLatestNews=[%s]", zhihuLatestNews);
                            ZhihuDailyFragment zhihuDailyFragment = ZhihuDailyFragment.newInstance(zhihuLatestNews);
                            changeToPage(PageState.Normal, zhihuDailyFragment);
                        }
                    }
                });
    }


    private void changeToPage(PageState state, BaseFragment fragment) {
        this.mPageState = state;
        if (fragment instanceof ErrorFragment) {
            ((ErrorFragment) fragment).setRetryListener(this);
        }
        FragmentUtil.replaceFragment(getChildFragmentManager(), R.id.container, fragment);
    }

    @Override
    public void retry() {
        fetchData();
    }
}
