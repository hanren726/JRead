package com.minglei.jread.fragments.adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.minglei.jread.R;
import com.minglei.jread.base.HolderBase;
import com.minglei.jread.beans.zhihu.TopStoriesBean;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


/**
 * Created by minglei on 2017/11/13.
 */

public class HolderTopStories extends HolderBase<List<TopStoriesBean>> implements ViewPagerEx.OnPageChangeListener, BaseSliderView.OnSliderClickListener{

    public static final String TAG = HolderTopStories.class.getSimpleName();
    private SliderLayout mSlider;
    private Context mContext;

    private static final String BUNDLE_ID = "id";
    private static final String BUNDLE_TITLE = "title";
    private static final String BUNDLE_URL = "image";

    private CompositeSubscription mSubscriptions = new CompositeSubscription();

    public HolderTopStories(View itemView) {
        super(itemView);
        mContext = itemView.getContext();
        mSlider = (SliderLayout)itemView.findViewById(R.id.slider);
    }

    @Override
    public void bindHolder(List<TopStoriesBean> topStoriesBeans) {
        super.bindHolder(topStoriesBeans);
        Log.i(TAG, "topSory size :" + topStoriesBeans.size());
        mSlider.removeAllSliders();
        Subscription subscription = Observable.from(topStoriesBeans)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<TopStoriesBean>() {
                    @Override
                    public void onCompleted() {
                        mSlider.setCustomIndicator((PagerIndicator) itemView.findViewById(R.id.custom_indicator));
                        mSlider.setPresetTransformer(SliderLayout.Transformer.Default);
                        mSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                        mSlider.setCustomAnimation(new DescriptionAnimation());
                        mSlider.setDuration(4000);
                        mSlider.addOnPageChangeListener(HolderTopStories.this);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "error!!!");
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(TopStoriesBean topStoriesBean) {
                        TextSliderView textSliderView = new TextSliderView(mContext);
                        textSliderView
                                .description(topStoriesBean.getTitle())
                                .image(topStoriesBean.getImage())
                                .setScaleType(BaseSliderView.ScaleType.Fit)
                                .setOnSliderClickListener(HolderTopStories.this);
                        //add your extra information
                        textSliderView.bundle(new Bundle());
                        textSliderView.getBundle().putString(BUNDLE_TITLE, topStoriesBean.getTitle());
                        textSliderView.getBundle().putString(BUNDLE_URL, topStoriesBean.getImage());
                        textSliderView.getBundle().putInt(BUNDLE_ID, topStoriesBean.getId());
                        mSlider.addSlider(textSliderView);
                    }
                });
        mSubscriptions.add(subscription);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Log.d(TAG, "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void unbindHolder() {
        super.unbindHolder();
        mSubscriptions.clear();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        int id = slider.getBundle().getInt(BUNDLE_ID);
        //TODO 点击跳转至webview
    }
}
