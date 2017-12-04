package com.minglei.jread.beans.zhihu.zhuanlan;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by minglei on 2017/12/1.
 */

public class LinksBean implements Parcelable{
    /**
     * comments : /api/posts/19877106/comments
     */

    private String comments;

    protected LinksBean(Parcel in) {
        comments = in.readString();
    }

    public static final Creator<LinksBean> CREATOR = new Creator<LinksBean>() {
        @Override
        public LinksBean createFromParcel(Parcel in) {
            return new LinksBean(in);
        }

        @Override
        public LinksBean[] newArray(int size) {
            return new LinksBean[size];
        }
    };

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(comments);
    }

    @Override
    public String toString() {
        return "LinksBean{" +
                "comments='" + comments + '\'' +
                '}';
    }
}
