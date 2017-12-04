package com.minglei.jread.beans.zhihu.zhuanlan;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by minglei on 2017/12/1.
 */

public class AvatarBean implements Parcelable{
    /**
     * id : bab849a83
     * template : https://pic4.zhimg.com/50/{id}_{size}.jpg
     */

    private String id;
    private String template;

    protected AvatarBean(Parcel in) {
        id = in.readString();
        template = in.readString();
    }

    public static final Creator<AvatarBean> CREATOR = new Creator<AvatarBean>() {
        @Override
        public AvatarBean createFromParcel(Parcel in) {
            return new AvatarBean(in);
        }

        @Override
        public AvatarBean[] newArray(int size) {
            return new AvatarBean[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(template);
    }

    @Override
    public String toString() {
        return "AvatarBean{" +
                "id='" + id + '\'' +
                ", template='" + template + '\'' +
                '}';
    }
}
