package com.minglei.jread.beans.zhihu.zhuanlan;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by minglei on 2017/12/1.
 */

public class MetaBean implements Parcelable{
    /**
     * previous : null
     * next : null
     */

    private Object previous;
    private Object next;

    protected MetaBean(Parcel in) {
    }

    public static final Creator<MetaBean> CREATOR = new Creator<MetaBean>() {
        @Override
        public MetaBean createFromParcel(Parcel in) {
            return new MetaBean(in);
        }

        @Override
        public MetaBean[] newArray(int size) {
            return new MetaBean[size];
        }
    };

    public Object getPrevious() {
        return previous;
    }

    public void setPrevious(Object previous) {
        this.previous = previous;
    }

    public Object getNext() {
        return next;
    }

    public void setNext(Object next) {
        this.next = next;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public String toString() {
        return "MetaBean{" +
                "previous=" + previous +
                ", next=" + next +
                '}';
    }
}
