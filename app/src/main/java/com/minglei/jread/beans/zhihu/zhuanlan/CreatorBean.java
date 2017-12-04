package com.minglei.jread.beans.zhihu.zhuanlan;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by minglei on 2017/12/1.
 */

public class CreatorBean implements Parcelable{
    /**
     * bio : 无聊就答题
     * isFollowing : false
     * hash : aae3ece94ed646750dc056f2035ab032
     * uid : 40087551213568
     * isOrg : false
     * slug : svenshi
     * isFollowed : false
     * description : 希望自己每天都能变得更好。
     * name : Sven
     * profileUrl : https://www.zhihu.com/people/svenshi
     * avatar : {"id":"bab849a83","template":"https://pic4.zhimg.com/50/{id}_{size}.jpg"}
     * isOrgWhiteList : false
     * isBanned : false
     */

    private String bio;
    private boolean isFollowing;
    private String hash;
    private long uid;
    private boolean isOrg;
    private String slug;
    private boolean isFollowed;
    private String description;
    private String name;
    private String profileUrl;
    private AvatarBean avatar;
    private boolean isOrgWhiteList;
    private boolean isBanned;

    protected CreatorBean(Parcel in) {
        bio = in.readString();
        isFollowing = in.readByte() != 0;
        hash = in.readString();
        uid = in.readLong();
        isOrg = in.readByte() != 0;
        slug = in.readString();
        isFollowed = in.readByte() != 0;
        description = in.readString();
        name = in.readString();
        profileUrl = in.readString();
        avatar = in.readParcelable(AvatarBean.class.getClassLoader());
        isOrgWhiteList = in.readByte() != 0;
        isBanned = in.readByte() != 0;
    }

    public static final Creator<CreatorBean> CREATOR = new Creator<CreatorBean>() {
        @Override
        public CreatorBean createFromParcel(Parcel in) {
            return new CreatorBean(in);
        }

        @Override
        public CreatorBean[] newArray(int size) {
            return new CreatorBean[size];
        }
    };

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public boolean isIsFollowing() {
        return isFollowing;
    }

    public void setIsFollowing(boolean isFollowing) {
        this.isFollowing = isFollowing;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public boolean isIsOrg() {
        return isOrg;
    }

    public void setIsOrg(boolean isOrg) {
        this.isOrg = isOrg;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public boolean isIsFollowed() {
        return isFollowed;
    }

    public void setIsFollowed(boolean isFollowed) {
        this.isFollowed = isFollowed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public AvatarBean getAvatar() {
        return avatar;
    }

    public void setAvatar(AvatarBean avatar) {
        this.avatar = avatar;
    }

    public boolean isIsOrgWhiteList() {
        return isOrgWhiteList;
    }

    public void setIsOrgWhiteList(boolean isOrgWhiteList) {
        this.isOrgWhiteList = isOrgWhiteList;
    }

    public boolean isIsBanned() {
        return isBanned;
    }

    public void setIsBanned(boolean isBanned) {
        this.isBanned = isBanned;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(bio);
        dest.writeByte((byte) (isFollowing ? 1 : 0));
        dest.writeString(hash);
        dest.writeLong(uid);
        dest.writeByte((byte) (isOrg ? 1 : 0));
        dest.writeString(slug);
        dest.writeByte((byte) (isFollowed ? 1 : 0));
        dest.writeString(description);
        dest.writeString(name);
        dest.writeString(profileUrl);
        dest.writeParcelable(avatar, flags);
        dest.writeByte((byte) (isOrgWhiteList ? 1 : 0));
        dest.writeByte((byte) (isBanned ? 1 : 0));
    }

    @Override
    public String toString() {
        return "CreatorBean{" +
                "bio='" + bio + '\'' +
                ", isFollowing=" + isFollowing +
                ", hash='" + hash + '\'' +
                ", uid=" + uid +
                ", isOrg=" + isOrg +
                ", slug='" + slug + '\'' +
                ", isFollowed=" + isFollowed +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", profileUrl='" + profileUrl + '\'' +
                ", avatar=" + avatar +
                ", isOrgWhiteList=" + isOrgWhiteList +
                ", isBanned=" + isBanned +
                '}';
    }
}
