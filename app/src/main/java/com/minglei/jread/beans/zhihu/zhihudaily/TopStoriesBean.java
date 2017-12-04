package com.minglei.jread.beans.zhihu.zhihudaily;

import java.io.Serializable;

/**
 * Created by minglei on 2017/11/13.
 */

public class TopStoriesBean implements Serializable{
    /**
     * image : https://pic2.zhimg.com/v2-471b8b3243c6b3d6ac4c061e81402c69.jpg
     * type : 0
     * id : 9656658
     * ga_prefix : 111315
     * title : 江歌遇害后第 294 天，江母和刘鑫终于见面
     */

    private String image;
    private int type;
    private int id;
    private String ga_prefix;
    private String title;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "TopStoriesBean{" +
                "image='" + image + '\'' +
                ", type=" + type +
                ", id=" + id +
                ", ga_prefix='" + ga_prefix + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
