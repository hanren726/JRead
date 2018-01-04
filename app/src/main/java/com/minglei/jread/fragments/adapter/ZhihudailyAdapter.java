package com.minglei.jread.fragments.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.minglei.jread.R;
import com.minglei.jread.activity.ZhihuDailyNewsWebActivity;
import com.minglei.jread.base.BaseViewHolder;
import com.minglei.jread.base.GroupedRecyclerViewAdapter;
import com.minglei.jread.beans.zhihu.zhihudaily.StoriesBean;
import com.minglei.jread.beans.zhihu.zhihudaily.TopStoriesBean;
import com.minglei.jread.beans.zhihu.zhihudaily.ZhihuLatestNews;
import com.minglei.jread.utils.DateUtil;
import com.minglei.jread.utils.DimentionUtil;
import com.minglei.jread.utils.JLog;
import com.minglei.jread.utils.ScreenSizeUtil;
import com.minglei.jread.utils.TypefaceUtil;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by minglei on 2017/11/27.
 */

public class ZhihudailyAdapter extends GroupedRecyclerViewAdapter implements ViewPagerEx.OnPageChangeListener, BaseSliderView.OnSliderClickListener{

    private static final String TAG = ZhihudailyAdapter.class.getSimpleName();

    private ArrayList<ZhihuLatestNews> mZhihuLatestNews;

    private static final int TYPE_TOP_STORY = -1;
    private static final int TYPE_NORMAL = -2;
    private static final String BUNDLE_ID = "id";
    private static final String BUNDLE_TITLE = "title";
    private static final String BUNDLE_URL = "image";

    public boolean mShowSelectedDay = false;

    private CompositeSubscription mSubscriptions = new CompositeSubscription();

    public void setData(ArrayList<ZhihuLatestNews> zhihuLatestNews) {
        this.mZhihuLatestNews = zhihuLatestNews;
        for (int i = 0; i < mZhihuLatestNews.size(); i++) {
            JLog.i(TAG, "setData top story is [%s], story is [%s]",
                    mZhihuLatestNews.get(i).getTop_stories(), mZhihuLatestNews.get(i).getStories());
        }
    }

    public void updateData(ArrayList<ZhihuLatestNews> zhihuLatestNews) {
        this.mZhihuLatestNews = zhihuLatestNews;
        for (int i = 0; i < mZhihuLatestNews.size(); i++) {
            JLog.i(TAG, "updateData top story is [%s], story is [%s]",
                    mZhihuLatestNews.get(i).getTop_stories(), mZhihuLatestNews.get(i).getStories());
        }
    }

    public ZhihudailyAdapter(Context context) {
        super(context);
    }

    @Override
    public int getGroupCount() {
        return mZhihuLatestNews == null ? 0 : mZhihuLatestNews.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (mZhihuLatestNews == null) {
            return 0;
        } else {
            List<StoriesBean> children = mZhihuLatestNews.get(groupPosition).getStories();
            JLog.i(TAG, "getChildrenCount children size is : [%d]", children.size());
            if (children != null) {
                return children.size() + 1;
            }
        }
        return 0;
    }

    @Override
    public boolean hasHeader(int groupPosition) {
        return true;
    }

    @Override
    public boolean hasFooter(int groupPosition) {
        return false;
    }

    @Override
    public int getHeaderLayout(int viewType) {
        return R.layout.zhihu_adapter_header;
    }

    @Override
    public int getFooterLayout(int viewType) {
        return 0;
    }

    @Override
    public int getChildLayout(int viewType) {
        if (viewType == TYPE_TOP_STORY) {
            return R.layout.layout_top_story;
        } else if (viewType == TYPE_NORMAL){
            return R.layout.layout_stories;
        }
        return R.layout.layout_stories;
    }

    @Override
    public void onBindHeaderViewHolder(BaseViewHolder holder, int groupPosition) {
        String date = mZhihuLatestNews.get(groupPosition).getDate();
        if (groupPosition == 0) {
            if (mShowSelectedDay) {
                holder.setText(R.id.tv_header, DateUtil.getFormatDate(date));
            } else {
                holder.setText(R.id.tv_header, "今天有趣的");
            }
        } else {
            holder.setText(R.id.tv_header, DateUtil.getFormatDate(date));
        }
    }

    @Override
    public void onBindFooterViewHolder(BaseViewHolder holder, int groupPosition) {

    }

    @Override
    public void onBindChildViewHolder(final BaseViewHolder holder, int groupPosition, int childPosition) {
        JLog.i(TAG, "onBindChildViewHolder groupPosition:[%d], childPosition:[%d]", groupPosition, childPosition);
        if (getChildViewType(groupPosition, childPosition) == TYPE_TOP_STORY) {
            final View container = holder.get(R.id.top_story_container);
            final SliderLayout slider = (SliderLayout) holder.get(R.id.slider);
            List<TopStoriesBean> topStoriesBeans = mZhihuLatestNews.get(groupPosition).getTop_stories();
            if (topStoriesBeans != null) {
                Log.i(TAG, "topStory size :" + topStoriesBeans.size());
                container.setVisibility(View.VISIBLE);
                RecyclerView.LayoutParams param = (RecyclerView.LayoutParams) container.getLayoutParams();
                param.height = DimentionUtil.dp2px(200);
                param.width = ScreenSizeUtil.getScreenSize().widthPixels;
                container.setLayoutParams(param);
                slider.removeAllSliders();
                Subscription subscription = Observable.from(topStoriesBeans)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<TopStoriesBean>() {
                            @Override
                            public void onCompleted() {
                                slider.setCustomIndicator((PagerIndicator) holder.get(R.id.custom_indicator));
                                slider.setPresetTransformer(SliderLayout.Transformer.Default);
                                slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                                slider.setCustomAnimation(new DescriptionAnimation());
                                slider.setDuration(4000);
                                slider.addOnPageChangeListener(ZhihudailyAdapter.this);
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
                                        .setOnSliderClickListener(ZhihudailyAdapter.this);
                                //add your extra information
                                textSliderView.bundle(new Bundle());
                                textSliderView.getBundle().putString(BUNDLE_TITLE, topStoriesBean.getTitle());
                                textSliderView.getBundle().putString(BUNDLE_URL, topStoriesBean.getImage());
                                textSliderView.getBundle().putInt(BUNDLE_ID, topStoriesBean.getId());
                                slider.addSlider(textSliderView);
                            }
                        });
                mSubscriptions.add(subscription);
            } else {
                RecyclerView.LayoutParams param = (RecyclerView.LayoutParams) container.getLayoutParams();
                container.setVisibility(View.GONE);
                param.height = 0;
                param.width = 0;
                container.setLayoutParams(param);
            }
        } else {
            TextView title = (TextView) holder.get(R.id.story_title);
            TextView multiImage = (TextView) holder.get(R.id.story_count);
            ImageView imageView = (ImageView) holder.get(R.id.story_image);
            TypefaceUtil.setTypefaceWawa(title);
            TypefaceUtil.set55Typeface(multiImage);
            //注意这里是 childPosition-1
            StoriesBean storiesBean = mZhihuLatestNews.get(groupPosition).getStories().get(childPosition-1);
            if (storiesBean != null) {
                title.setText(storiesBean.getTitle());
                Log.i(TAG, "item images count :" + storiesBean.getImages().size());
                multiImage.setVisibility(storiesBean.isMultipic() ? View.VISIBLE : View.INVISIBLE);
                Glide.with(mContext)
                        .load(storiesBean.getImages().get(0))
                        .placeholder(R.drawable.news)
                        .centerCrop()
                        .into(imageView);
            }
        }
    }

    public StoriesBean getChildItem(int groupPosition,  int childPosition) {
        if (mZhihuLatestNews != null) {
            //注意这里是 childPosition-1
            return mZhihuLatestNews.get(groupPosition).getStories().get(childPosition-1);
        }
        return null;
    }

    @Override
    public int getChildViewType(int groupPosition, int childPosition) {
        if (mZhihuLatestNews.get(groupPosition) != null) {
            if (childPosition == 0) {
                return TYPE_TOP_STORY;
            } else {
                return TYPE_NORMAL;
            }
        } else {
            return TYPE_NORMAL;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        int id = slider.getBundle().getInt(BUNDLE_ID);
        ZhihuDailyNewsWebActivity.startActivity(id);
    }
}
