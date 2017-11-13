package com.minglei.jread.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by minglei on 2017/11/13.
 */

public class HolderBase<T> extends RecyclerView.ViewHolder {

    protected String TAG = this.getClass().getSimpleName();

    public HolderBase(View itemView) {
        super(itemView);
        itemView.setTag(this);
    }

    public void bindHolder(T t) {
    }

    public void bindHolder(T t, Object object) {
    }

    public void bindHolder(T t, Object obj0, Object obj1) {
    }

    public void unbindHolder() {
    }
}
