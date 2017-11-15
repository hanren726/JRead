package com.minglei.jread.fragments.adapter;

import android.content.Context;
import android.nfc.Tag;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.minglei.jread.R;
import com.minglei.jread.base.HolderBase;
import com.minglei.jread.beans.zhihu.StoriesBean;
import com.minglei.jread.beans.zhihu.ZhihuLatestNews;
import com.minglei.jread.fragments.interfaces.ItemClickListener;
import com.minglei.jread.net.ZhihuDailyApi;

/**
 * Created by minglei on 2017/11/13.
 */

public class ZhihuDailyListAdapter extends RecyclerView.Adapter<HolderBase> {

    public static final String TAG = ZhihuDailyListAdapter.class.getSimpleName();

    private Context mContext;
    private ZhihuLatestNews mZhihuLatestNews;

    private static final int TYPE_TOP_STORY = -1;
    private static final int TYPE_NORMAL = -2;

    private ItemClickListener mItemClickListener;

    public ZhihuDailyListAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(ZhihuLatestNews zhihuLatestNews) {
        this.mZhihuLatestNews = zhihuLatestNews;
    }

    @Override
    public HolderBase onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_TOP_STORY) {
            return new HolderTopStories(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_top_story, null));
        } else {
            Log.i(TAG, "onCreateViewHolder listener is null:" + (mItemClickListener == null));
            return new HolderStories(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_stories, null), mItemClickListener);
        }
    }

    @Override
    public void onBindViewHolder(HolderBase holder, int position) {
        if (holder instanceof HolderTopStories) {
            Log.i(TAG, String.format("topstory size : [%s]", String.valueOf(mZhihuLatestNews.getTop_stories().size())));
            holder.bindHolder(mZhihuLatestNews.getTop_stories());
        } else {
            holder.bindHolder(getItem(position));
        }
    }

    public StoriesBean getItem(int position) {
        if (mZhihuLatestNews == null) {
            return null;
        } else {
            return position < mZhihuLatestNews.getStories().size() ? mZhihuLatestNews.getStories().get(position) : null;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mZhihuLatestNews.getTop_stories() != null) {
            if (position == 0) {
                return TYPE_TOP_STORY;
            } else {
                return TYPE_NORMAL;
            }
        } else {
            return TYPE_NORMAL;
        }
    }

    @Override
    public int getItemCount() {
        if (mZhihuLatestNews == null) {
            return 0;
        } else {
            return mZhihuLatestNews.getStories().size() + (mZhihuLatestNews.getTop_stories() == null ? 0 : 1);
        }
    }

    @Override
    public void onViewRecycled(HolderBase holder) {
        super.onViewRecycled(holder);
        holder.unbindHolder();
    }

    /**
     * 设置Item点击监听
     * @param listener
     */
    public void setOnItemClickListener(ItemClickListener listener){
        Log.i(TAG, "setOnItemClickListener listener is null:" + (listener == null));
        this.mItemClickListener = listener;
    }
}
