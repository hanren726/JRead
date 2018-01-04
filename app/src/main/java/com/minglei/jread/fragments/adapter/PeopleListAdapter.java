package com.minglei.jread.fragments.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.minglei.jread.R;
import com.minglei.jread.base.HolderBase;
import com.minglei.jread.beans.zhihu.zhuanlan.UserEntity;
import com.minglei.jread.fragments.adapter.holder.PeopleViewHolder;
import com.minglei.jread.utils.JLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by minglei on 2017/12/1.
 */

public class PeopleListAdapter extends RecyclerView.Adapter<HolderBase> {

    private static final String TAG = PeopleListAdapter.class.getSimpleName();

    private List<UserEntity> mUsers;

    public PeopleListAdapter() {
        mUsers = new ArrayList<>();
    }

    public void setData(@NonNull List<UserEntity> list) {
        JLog.i(TAG, "setData, list size is [%d]", list.size());
        mUsers = list;
        notifyDataSetChanged();
    }

    public void add(@NonNull UserEntity user) {
        JLog.i(TAG, "add user is [%d]", user);
        mUsers.add(user);
        notifyItemInserted(mUsers.size() - 1);
    }

    public void update(@NonNull UserEntity user) {
        int index = mUsers.indexOf(user);
        JLog.i(TAG, "add : user=[%s], index=[%d]", user, index);
        if (index >= 0) {
            mUsers.set(index, user);
        }
    }

    @Override
    public HolderBase onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PeopleViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_people_list, parent, false));
    }

    @Override
    public void onBindViewHolder(HolderBase holder, int position) {
        JLog.i(TAG, "onBindViewHolder getItem(position) : [%s]", getItem(position));
        holder.bindHolder(getItem(position));
    }

    public UserEntity getItem(int position) {
        return position < mUsers.size() ? mUsers.get(position) : null;
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

}
