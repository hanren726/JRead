package com.minglei.jread.fragments.adapter.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.minglei.jread.R;
import com.minglei.jread.base.HolderBase;
import com.minglei.jread.beans.zhihu.zhuanlan.Post;
import com.minglei.jread.utils.ZhuanlanUtil;

/**
 * Created by minglei on 2018/1/3.
 */

public class TextPostHolder extends HolderBase<Post> {

    private final TextView mTitle;
    private final TextView mSummary;
    private final TextView mName;
    private final TextView mDate;
    private final TextView mCommentCount;
    private final TextView mLikeCount;
    private Context mContext;

    public TextPostHolder(View itemView) {
        super(itemView);
        mContext = itemView.getContext();
        mTitle = (TextView) itemView.findViewById(R.id.holder_text_post_tv_title);
        mSummary = (TextView) itemView.findViewById(R.id.holder_text_post_tv_summary);
        mName = (TextView) itemView.findViewById(R.id.holder_text_post_tv_name);
        mDate = (TextView) itemView.findViewById(R.id.holder_text_post_tv_date);
        mCommentCount = (TextView) itemView.findViewById(R.id.holder_text_post_tv_comment_count);
        mLikeCount = (TextView) itemView.findViewById(R.id.holder_text_post_tv_like_count);
    }

    @Override
    public void bindHolder(Post post) {
        super.bindHolder(post);
        mTitle.setText(post.getTitle());
        mSummary.setText(post.getSummary());
        mName.setText(post.getAuthor().getName());
        mDate.setText(ZhuanlanUtil.convertPublishTime(post.getPublishedTime()));
        mCommentCount.setText(mContext.getResources().getString(R.string.comment_count, post.getCommentsCount()));
        mLikeCount.setText(mContext.getResources().getString(R.string.like_count, post.getLikesCount()));
    }
}
