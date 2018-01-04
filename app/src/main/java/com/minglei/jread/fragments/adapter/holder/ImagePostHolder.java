package com.minglei.jread.fragments.adapter.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.makeramen.roundedimageview.RoundedImageView;
import com.minglei.jread.R;
import com.minglei.jread.base.HolderBase;
import com.minglei.jread.beans.zhihu.zhuanlan.Post;
import com.minglei.jread.utils.JLog;
import com.minglei.jread.utils.ZhuanlanUtil;

/**
 * Created by minglei on 2018/1/3.
 */

public class ImagePostHolder extends HolderBase<Post>{

    private final RoundedImageView mImageView;
    private final TextView mTitile;
    private final TextView mDes;
    private final TextView mNameTime;
    private final TextView mCount;

    private Context mContext;

    public ImagePostHolder(View itemView) {
        super(itemView);
        mContext = itemView.getContext();
        mImageView = (RoundedImageView) itemView.findViewById(R.id.holder_image_post_pic);
        mTitile = (TextView) itemView.findViewById(R.id.holder_image_post_name);
        mDes = (TextView) itemView.findViewById(R.id.holder_image_post_des);
        mNameTime = (TextView) itemView.findViewById(R.id.holder_image_post_zan);
        mCount = (TextView) itemView.findViewById(R.id.holder_image_post_count);
    }

    @Override
    public void bindHolder(Post post) {
        super.bindHolder(post);
        JLog.i(TAG, "bindHolder post is %s", post);
        Glide.with(mContext).load(post.getTitleImage()).centerCrop().diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder(R.drawable.zhuanlan_detail_img_placeholder).into(mImageView);
        mTitile.setText(post.getTitle());
        mDes.setText(post.getContent());
        mNameTime.setText(mContext.getResources().getString(R.string.post_name_time, post.getAuthor().getName(), ZhuanlanUtil.convertPublishTime(post.getPublishedTime())));
        mCount.setText(mContext.getResources().getString(R.string.post_like_comment_count, post.getLikesCount(), post.getCommentsCount()));
    }
}
