package com.minglei.jread.beans.zhihu.zhuanlan;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Transient;

/**
 * Created by minglei on 2017/12/1.
 */
@Entity
public class User implements Parcelable{

    /**
     * followersCount : 6603
     * creator : {"bio":"无聊就答题","isFollowing":false,"hash":"aae3ece94ed646750dc056f2035ab032","uid":40087551213568,"isOrg":false,"slug":"svenshi","isFollowed":false,"description":"希望自己每天都能变得更好。","name":"Sven","profileUrl":"https://www.zhihu.com/people/svenshi","avatar":{"id":"bab849a83","template":"https://pic4.zhimg.com/50/{id}_{size}.jpg"},"isOrgWhiteList":false,"isBanned":false}
     * topics : []
     * activateState : activated
     * href : /api/columns/shizheng
     * acceptSubmission : true
     * firstTime : false
     * postTopics : [{"postsCount":1,"id":307,"name":"生活"},{"postsCount":1,"id":2238,"name":"常识"},{"postsCount":1,"id":3324,"name":"经济学"},{"postsCount":1,"id":4258,"name":"税务"},{"postsCount":2,"id":5582,"name":"社会"},{"postsCount":1,"id":12573,"name":"财富"}]
     * pendingName :
     * avatar : {"id":"329b4ad62","template":"https://pic3.zhimg.com/{id}_{size}.jpg"}
     * canManage : false
     * description : 个人感想
     * pendingTopics : []
     * nameCanEditUntil : 0
     * reason :
     * banUntil : 0
     * slug : shizheng
     * name : 我的太阳
     * url : /shizheng
     * intro : 个人感想
     * topicsCanEditUntil : 0
     * activateAuthorRequested : none
     * commentPermission : anyone
     * following : false
     * postsCount : 21
     * canPost : false
     */
    @Id
    private long id;
    private int followersCount;
    private CreatorBean creator;
    private String activateState;
    private String href;
    private boolean acceptSubmission;
    private boolean firstTime;
    private String pendingName;
    private AvatarBean avatar;
    private boolean canManage;
    private String description;
    private int nameCanEditUntil;
    private String reason;
    private int banUntil;
    private String slug;
    private String name;
    private String url;
    private String intro;
    private int topicsCanEditUntil;
    private String activateAuthorRequested;
    private String commentPermission;
    private boolean following;
    private int postsCount;
    private boolean canPost;
    private List<?> topics;
    private List<PostTopicsBean> postTopics;
    private List<?> pendingTopics;

    protected User(Parcel in) {
        followersCount = in.readInt();
        creator = in.readParcelable(CreatorBean.class.getClassLoader());
        activateState = in.readString();
        href = in.readString();
        acceptSubmission = in.readByte() != 0;
        firstTime = in.readByte() != 0;
        pendingName = in.readString();
        avatar = in.readParcelable(AvatarBean.class.getClassLoader());
        canManage = in.readByte() != 0;
        description = in.readString();
        nameCanEditUntil = in.readInt();
        reason = in.readString();
        banUntil = in.readInt();
        slug = in.readString();
        name = in.readString();
        url = in.readString();
        intro = in.readString();
        topicsCanEditUntil = in.readInt();
        activateAuthorRequested = in.readString();
        commentPermission = in.readString();
        following = in.readByte() != 0;
        postsCount = in.readInt();
        canPost = in.readByte() != 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public int getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }

    public CreatorBean getCreator() {
        return creator;
    }

    public void setCreator(CreatorBean creator) {
        this.creator = creator;
    }

    public String getActivateState() {
        return activateState;
    }

    public void setActivateState(String activateState) {
        this.activateState = activateState;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public boolean isAcceptSubmission() {
        return acceptSubmission;
    }

    public void setAcceptSubmission(boolean acceptSubmission) {
        this.acceptSubmission = acceptSubmission;
    }

    public boolean isFirstTime() {
        return firstTime;
    }

    public void setFirstTime(boolean firstTime) {
        this.firstTime = firstTime;
    }

    public String getPendingName() {
        return pendingName;
    }

    public void setPendingName(String pendingName) {
        this.pendingName = pendingName;
    }

    public boolean isCanManage() {
        return canManage;
    }

    public void setCanManage(boolean canManage) {
        this.canManage = canManage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNameCanEditUntil() {
        return nameCanEditUntil;
    }

    public void setNameCanEditUntil(int nameCanEditUntil) {
        this.nameCanEditUntil = nameCanEditUntil;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getBanUntil() {
        return banUntil;
    }

    public void setBanUntil(int banUntil) {
        this.banUntil = banUntil;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public int getTopicsCanEditUntil() {
        return topicsCanEditUntil;
    }

    public void setTopicsCanEditUntil(int topicsCanEditUntil) {
        this.topicsCanEditUntil = topicsCanEditUntil;
    }

    public String getActivateAuthorRequested() {
        return activateAuthorRequested;
    }

    public void setActivateAuthorRequested(String activateAuthorRequested) {
        this.activateAuthorRequested = activateAuthorRequested;
    }

    public String getCommentPermission() {
        return commentPermission;
    }

    public void setCommentPermission(String commentPermission) {
        this.commentPermission = commentPermission;
    }

    public boolean isFollowing() {
        return following;
    }

    public void setFollowing(boolean following) {
        this.following = following;
    }

    public int getPostsCount() {
        return postsCount;
    }

    public void setPostsCount(int postsCount) {
        this.postsCount = postsCount;
    }

    public boolean isCanPost() {
        return canPost;
    }

    public void setCanPost(boolean canPost) {
        this.canPost = canPost;
    }

    public List<?> getTopics() {
        return topics;
    }

    public void setTopics(List<?> topics) {
        this.topics = topics;
    }

    public List<PostTopicsBean> getPostTopics() {
        return postTopics;
    }

    public void setPostTopics(List<PostTopicsBean> postTopics) {
        this.postTopics = postTopics;
    }

    public List<?> getPendingTopics() {
        return pendingTopics;
    }

    public void setPendingTopics(List<?> pendingTopics) {
        this.pendingTopics = pendingTopics;
    }

    public AvatarBean getAvatar() {
        return avatar;
    }

    public void setAvatar(AvatarBean avatar) {
        this.avatar = avatar;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(followersCount);
        dest.writeParcelable(creator, flags);
        dest.writeString(activateState);
        dest.writeString(href);
        dest.writeByte((byte) (acceptSubmission ? 1 : 0));
        dest.writeByte((byte) (firstTime ? 1 : 0));
        dest.writeString(pendingName);
        dest.writeParcelable(avatar, flags);
        dest.writeByte((byte) (canManage ? 1 : 0));
        dest.writeString(description);
        dest.writeInt(nameCanEditUntil);
        dest.writeString(reason);
        dest.writeInt(banUntil);
        dest.writeString(slug);
        dest.writeString(name);
        dest.writeString(url);
        dest.writeString(intro);
        dest.writeInt(topicsCanEditUntil);
        dest.writeString(activateAuthorRequested);
        dest.writeString(commentPermission);
        dest.writeByte((byte) (following ? 1 : 0));
        dest.writeInt(postsCount);
        dest.writeByte((byte) (canPost ? 1 : 0));
    }

    @Override
    public String toString() {
        return "User{" +
                "followersCount=" + followersCount +
                ", creator=" + creator +
                ", activateState='" + activateState + '\'' +
                ", href='" + href + '\'' +
                ", acceptSubmission=" + acceptSubmission +
                ", firstTime=" + firstTime +
                ", pendingName='" + pendingName + '\'' +
                ", avatar=" + avatar +
                ", canManage=" + canManage +
                ", description='" + description + '\'' +
                ", nameCanEditUntil=" + nameCanEditUntil +
                ", reason='" + reason + '\'' +
                ", banUntil=" + banUntil +
                ", slug='" + slug + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", intro='" + intro + '\'' +
                ", topicsCanEditUntil=" + topicsCanEditUntil +
                ", activateAuthorRequested='" + activateAuthorRequested + '\'' +
                ", commentPermission='" + commentPermission + '\'' +
                ", following=" + following +
                ", postsCount=" + postsCount +
                ", canPost=" + canPost +
                ", topics=" + topics +
                ", postTopics=" + postTopics +
                ", pendingTopics=" + pendingTopics +
                '}';
    }
}
