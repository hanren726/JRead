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
import com.minglei.jread.fragments.interfaces.MyItemClickListener;
import com.minglei.jread.utils.ToastUtil;
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

public class HolderStories extends HolderBase<StoriesBean> implements View.OnClickListener{

    private static final String TAG = HolderStories.class.getSimpleName();

    private TextView mTilte;
    private ImageView mImage;
    private TextView mMultiImage;

    private Context mContext;
    private MyItemClickListener mItemClickListener;

    private CompositeSubscription mSubcriptions = new CompositeSubscription();

    public HolderStories(View itemView, MyItemClickListener myItemClickListener) {
        super(itemView);
        mContext = itemView.getContext();
        mTilte = (TextView) itemView.findViewById(R.id.story_title);
        mImage = (ImageView) itemView.findViewById(R.id.story_image);
        mMultiImage = (TextView) itemView.findViewById(R.id.story_count);
        mItemClickListener = myItemClickListener;
        itemView.setOnClickListener(this);
        TypefaceUtil.setTypefaceWawa(mTilte);
        TypefaceUtil.set55Typeface(mMultiImage);
    }

    @Override
    public void bindHolder(StoriesBean storiesBean) {
        super.bindHolder(storiesBean);
        Log.i(TAG, "==========bindHolder==========");
        if (storiesBean == null) {
            Log.e(TAG, "storiesBean is null!!!");
            return;
        }
        mTilte.setText(storiesBean.getTitle());
        Log.i(TAG, "item images count :" + storiesBean.getImages().size());
        mMultiImage.setVisibility(storiesBean.isMultipic() ? View.VISIBLE : View.INVISIBLE);
        Glide.with(mContext)
                .load(storiesBean.getImages().get(0))
                .placeholder(R.drawable.news)
                .centerCrop()
                .into(mImage);
    }

    @Override
    public void unbindHolder() {
        super.unbindHolder();
        mSubcriptions.clear();
    }

    @Override
    public void onClick(View v) {
        ToastUtil.forceToShowToastInCenter("onclick!");
        if (mItemClickListener != null) {
            mItemClickListener.onItemClick(v, getPosition());
        }
    }
}
