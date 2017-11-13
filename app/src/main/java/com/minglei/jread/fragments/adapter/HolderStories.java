package com.minglei.jread.fragments.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.minglei.jread.R;
import com.minglei.jread.base.HolderBase;
import com.minglei.jread.beans.zhihu.StoriesBean;
import com.minglei.jread.utils.TypefaceUtil;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by minglei on 2017/11/13.
 */

public class HolderStories extends HolderBase<StoriesBean> {

    private static final String TAG = HolderStories.class.getSimpleName();

    private TextView mTilte;
    private ImageView mImage;
    private TextView mMultiImage;

    private Context mContext;

    private CompositeSubscription mSubcriptions;

    public HolderStories(View itemView) {
        super(itemView);
        mContext = itemView.getContext();
        mTilte = (TextView) itemView.findViewById(R.id.story_title);
        mImage = (ImageView) itemView.findViewById(R.id.story_image);
        mMultiImage = (TextView) itemView.findViewById(R.id.story_count);
        TypefaceUtil.setTypefaceWawa(mTilte);
    }

    @Override
    public void bindHolder(StoriesBean storiesBean) {
        super.bindHolder(storiesBean);
        Subscription subscription = Observable.just(storiesBean)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<StoriesBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "error!!!");
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(StoriesBean storiesBean) {
                        mTilte.setText(storiesBean.getTitle());
                        mMultiImage.setVisibility(storiesBean.getImages().size() > 1 ? View.VISIBLE : View.INVISIBLE);
                        Glide.with(mContext)
                                .load(storiesBean.getImages().get(0))
                                .centerCrop()
                                .into(mImage);
                    }
                });
        mSubcriptions.add(subscription);
    }

    @Override
    public void unbindHolder() {
        super.unbindHolder();
        mSubcriptions.clear();
    }
}
