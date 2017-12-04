package com.minglei.jread.fragments.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.minglei.jread.JApplication;
import com.minglei.jread.R;
import com.minglei.jread.base.BaseViewHolder;
import com.minglei.jread.base.HolderBase;
import com.minglei.jread.beans.zhihu.zhuanlan.User;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by minglei on 2017/12/1.
 */

public class PeopleViewHolder extends HolderBase<User>{

    private final CircleImageView mPhotoView;
    private final TextView mName;
    private final TextView mFollow;
    private final TextView mPost;
    private final TextView mDescription;
    private Context mContext;

    public PeopleViewHolder(View itemView) {
        super(itemView);
        mPhotoView = (CircleImageView) itemView.findViewById(R.id.avatar_civ);
        mName = (TextView) itemView.findViewById(R.id.tv_name);
        mFollow = (TextView) itemView.findViewById(R.id.tv_follower);
        mPost = (TextView) itemView.findViewById(R.id.tv_post_count);
        mDescription = (TextView) itemView.findViewById(R.id.tv_description);
        mContext = itemView.getContext();
    }

    @Override
    public void bindHolder(User user) {
        super.bindHolder(user);
        Glide.with(mContext)
                .load(user.getAvatar().getTemplate())
                .crossFade()
                .into(mPhotoView);
        mName.setText(user.getName());
        mFollow.setText(String.format(mContext.getResources().getString(R.string.zhihu_zhuanlan_peoplelist_follow), user.getFollowersCount()));
        mPost.setText(String.format(mContext.getResources().getString(R.string.zhihu_zhuanlan_peoplelist_post), user.getPostsCount()));
        mDescription.setText(user.getDescription());
    }
}
