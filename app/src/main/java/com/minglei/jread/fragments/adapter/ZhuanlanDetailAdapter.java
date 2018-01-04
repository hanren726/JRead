package com.minglei.jread.fragments.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.minglei.jread.R;
import com.minglei.jread.base.HolderBase;
import com.minglei.jread.beans.zhihu.zhuanlan.Post;
import com.minglei.jread.fragments.adapter.holder.ImagePostHolder;
import com.minglei.jread.fragments.adapter.holder.TextPostHolder;
import com.minglei.jread.utils.JLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by minglei on 2018/1/3.
 */

public class ZhuanlanDetailAdapter extends RecyclerView.Adapter<HolderBase> {

    private static final String TAG = ZhuanlanDetailAdapter.class.getSimpleName();

    private static final int VIEW_TYPE_TEXT = 0;
    private static final int VIEW_TYPE_IMAGE = 1;

    private List<Post> mAllPosts;

    public ZhuanlanDetailAdapter() {
        mAllPosts = new ArrayList<>();
    }

    public void addAllItems(List<Post> list) {
        JLog.i(TAG, "addAllItems list : [%s]", list);
        if (list != null) {
            mAllPosts.addAll(list);
            notifyDataSetChanged();
        }
    }

    @Override
    public HolderBase onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_IMAGE) {
            return new ImagePostHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_image_post, parent, false));
        } else {
            return new TextPostHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_text_post, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(HolderBase holder, int position) {
        if (holder instanceof ImagePostHolder) {
            JLog.i(TAG, "onBindViewHolder 1");
            ((ImagePostHolder) holder).bindHolder(getItem(position));
        } else if (holder instanceof TextPostHolder) {
            JLog.i(TAG, "onBindViewHolder 2");
            ((TextPostHolder) holder).bindHolder(getItem(position));
        }
    }

    private Post getItem(int position) {
        return 0 <= position && position < mAllPosts.size() ? mAllPosts.get(position) : null;
    }

    @Override
    public int getItemViewType(int position) {
        if (TextUtils.isEmpty(getItem(position).getTitleImage())) {
            return VIEW_TYPE_TEXT;
        } else {
            return VIEW_TYPE_IMAGE;
        }
    }

    @Override
    public int getItemCount() {
        return mAllPosts.size();
    }
}
