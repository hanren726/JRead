package com.minglei.jread.beans.zhihu.zhuanlan;

import java.io.Serializable;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * Created by minglei on 2017/12/4.
 */

/**
 * @Entity	这个对象需要持久化。
 * @Id	这个对象的主键。
 * @Index	这个对象中的索引。对经常大量进行查询的字段创建索引，会提高你的查询性能。
 * @NameInDb	有的时候数据库中的字段跟你的对象字段不匹配的时候，可以使用此注解。
 * @Transient	如果你有某个字段不想被持久化，可以使用此注解。
 * @Relation	做一对多，多对一的注解。
 */

@Entity
public class UserEntity implements Serializable{

    @Id
    long id;

    int followerCount;

    String description;

    String avatarId;

    String avatarTemplate;

    String authorName;

    String href;

    String slug;

    String name;

    String url;

    int postCount;

    public int getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(int followerCount) {
        this.followerCount = followerCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(String avatarId) {
        this.avatarId = avatarId;
    }

    public String getAvatarTemplate() {
        return avatarTemplate;
    }

    public void setAvatarTemplate(String avatarTemplate) {
        this.avatarTemplate = avatarTemplate;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getZhuanlanName() {
        return name;
    }

    public void setZhuanlanName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPostCount() {
        return postCount;
    }

    public void setPostCount(int postCount) {
        this.postCount = postCount;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", followerCount=" + followerCount +
                ", description='" + description + '\'' +
                ", avatarId='" + avatarId + '\'' +
                ", avatarTemplate='" + avatarTemplate + '\'' +
                ", authorName='" + authorName + '\'' +
                ", href='" + href + '\'' +
                ", slug='" + slug + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", postCount=" + postCount +
                '}';
    }
}
