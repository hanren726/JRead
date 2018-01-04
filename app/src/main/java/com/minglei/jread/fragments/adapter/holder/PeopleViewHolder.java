package com.minglei.jread.fragments.adapter.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.minglei.jread.R;
import com.minglei.jread.activity.ZhuanlanDetailActivity;
import com.minglei.jread.base.HolderBase;
import com.minglei.jread.beans.zhihu.zhuanlan.UserEntity;
import com.minglei.jread.utils.JLog;
import com.minglei.jread.utils.ZhuanlanUtil;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by minglei on 2017/12/1.
 */

public class PeopleViewHolder extends HolderBase<UserEntity> implements View.OnClickListener{

    public static final String TAG = PeopleViewHolder.class.getSimpleName();

    private final CircleImageView mPhotoView;
    private final TextView mName;
    private final TextView mFollow;
    private final TextView mPost;
    private final TextView mDescription;
    private Context mContext;
    private UserEntity mUserEntity;

    public PeopleViewHolder(View itemView) {
        super(itemView);
        mPhotoView = (CircleImageView) itemView.findViewById(R.id.avatar_civ);
        mName = (TextView) itemView.findViewById(R.id.tv_name);
        mFollow = (TextView) itemView.findViewById(R.id.tv_follower);
        mPost = (TextView) itemView.findViewById(R.id.tv_post_count);
        mDescription = (TextView) itemView.findViewById(R.id.tv_description);
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    @Override
    public void bindHolder(UserEntity user) {
        super.bindHolder(user);
        mUserEntity = user;
        JLog.i(TAG, "bindHolder user is [%s]", user);
        Glide.with(mContext)
                .load(ZhuanlanUtil.getAuthorAvatarUrl(user.getAvatarTemplate(), user.getAvatarId(), ZhuanlanUtil.PIC_SIZE_XL))
                .crossFade()
                .into(mPhotoView);
        mName.setText(user.getZhuanlanName());
        mFollow.setText(String.format(mContext.getResources().getString(R.string.zhihu_zhuanlan_peoplelist_follow), user.getFollowerCount()));
        mPost.setText(String.format(mContext.getResources().getString(R.string.zhihu_zhuanlan_peoplelist_post), user.getPostCount()));
        mDescription.setText(user.getDescription());
    }

    @Override
    public void onClick(View v) {
        ZhuanlanDetailActivity.startActivity(mUserEntity);
    }
}
