package com.minglei.jread.fragments.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.minglei.jread.R;
import com.minglei.jread.base.HolderBase;
import com.minglei.jread.beans.zhihu.StoriesBean;
import com.minglei.jread.beans.zhihu.ZhihuLatestNews;

/**
 * Created by minglei on 2017/11/13.
 */

public class ZhihuDailyListAdapter extends RecyclerView.Adapter<HolderBase> {

    private Context mContext;
    private ZhihuLatestNews mZhihuLatestNews;

    private static final int TYPE_TOP_STORY = -1;
    private static final int TYPE_NORMAL = -2;

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
            return new HolderStories(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_stories, null));
        }
    }

    @Override
    public void onBindViewHolder(HolderBase holder, int position) {
        if (holder instanceof HolderTopStories) {
            holder.bindHolder(mZhihuLatestNews.getTop_stories());
        } else {
            holder.bindHolder(getItem(position));
        }
    }

    private StoriesBean getItem(int position) {
        return position < mZhihuLatestNews.getStories().size() ? mZhihuLatestNews.getStories().get(position) : null;
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
        return mZhihuLatestNews.getStories().size() + (mZhihuLatestNews.getTop_stories() == null ? 0 : 1);
    }

    @Override
    public void onViewRecycled(HolderBase holder) {
        super.onViewRecycled(holder);
        holder.unbindHolder();
    }
}
