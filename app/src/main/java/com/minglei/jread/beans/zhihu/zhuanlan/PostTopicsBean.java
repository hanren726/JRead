package com.minglei.jread.beans.zhihu.zhuanlan;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by minglei on 2017/12/1.
 */

public class PostTopicsBean implements Parcelable{
    /**
     * postsCount : 1
     * id : 307
     * name : 生活
     */

    private int postsCount;
    private int id;
    private String name;

    protected PostTopicsBean(Parcel in) {
        postsCount = in.readInt();
        id = in.readInt();
        name = in.readString();
    }

    public static final Creator<PostTopicsBean> CREATOR = new Creator<PostTopicsBean>() {
        @Override
        public PostTopicsBean createFromParcel(Parcel in) {
            return new PostTopicsBean(in);
        }

        @Override
        public PostTopicsBean[] newArray(int size) {
            return new PostTopicsBean[size];
        }
    };

    public int getPostsCount() {
        return postsCount;
    }

    public void setPostsCount(int postsCount) {
        this.postsCount = postsCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(postsCount);
        dest.writeInt(id);
        dest.writeString(name);
    }

    @Override
    public String toString() {
        return "PostTopicsBean{" +
                "postsCount=" + postsCount +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
