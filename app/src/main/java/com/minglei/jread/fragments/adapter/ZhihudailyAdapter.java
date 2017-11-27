package com.minglei.jread.fragments.adapter;

import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
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
import com.minglei.jread.base.BaseViewHolder;
import com.minglei.jread.base.GroupedRecyclerViewAdapter;
import com.minglei.jread.beans.zhihu.StoriesBean;
import com.minglei.jread.beans.zhihu.TopStoriesBean;
import com.minglei.jread.beans.zhihu.ZhihuLatestNews;
import com.minglei.jread.utils.DateUtil;
import com.minglei.jread.utils.JLog;
import com.minglei.jread.utils.TypefaceUtil;

import java.util.ArrayList;
import java.util.Date;
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

    private CompositeSubscription mSubscriptions = new CompositeSubscription();

    public void setData(ArrayList<ZhihuLatestNews> zhihuLatestNews) {
        this.mZhihuLatestNews = zhihuLatestNews;
        JLog.i(TAG, "mZhihuLatestNews is [%s]", mZhihuLatestNews);
        for (int i=0; i<mZhihuLatestNews.size(); i++) {
            JLog.i(TAG, "top story size is [%d], story size is [%d]",
                    mZhihuLatestNews.get(i).getTop_stories().size(), mZhihuLatestNews.get(i).getStories().size());
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
            holder.setText(R.id.tv_header, "今天有趣的");
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
            final SliderLayout mSlider = (SliderLayout) holder.get(R.id.slider);
            List<TopStoriesBean> topStoriesBeans = mZhihuLatestNews.get(groupPosition).getTop_stories();
            Log.i(TAG, "topSory size :" + topStoriesBeans.size());
            mSlider.removeAllSliders();
            Subscription subscription = Observable.from(topStoriesBeans)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<TopStoriesBean>() {
                        @Override
                        public void onCompleted() {
                            mSlider.setCustomIndicator((PagerIndicator) holder.get(R.id.custom_indicator));
                            mSlider.setPresetTransformer(SliderLayout.Transformer.Default);
                            mSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                            mSlider.setCustomAnimation(new DescriptionAnimation());
                            mSlider.setDuration(4000);
                            mSlider.addOnPageChangeListener(ZhihudailyAdapter.this);
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
                            mSlider.addSlider(textSliderView);
                        }
                    });
            mSubscriptions.add(subscription);
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
        Log.d(TAG, "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        int id = slider.getBundle().getInt(BUNDLE_ID);
        //TODO 点击跳转至webview
    }
}
