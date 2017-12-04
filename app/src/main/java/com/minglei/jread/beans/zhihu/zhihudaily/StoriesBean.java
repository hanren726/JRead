package com.minglei.jread.beans.zhihu.zhihudaily;

import java.io.Serializable;
import java.util.List;

/**
 * Created by minglei on 2017/11/13.
 */

public class StoriesBean implements Serializable{
    /**
     * images : ["https://pic3.zhimg.com/v2-c3120669972f6a25f5470175a4bcf622.jpg"]
     * type : 0
     * id : 9656658
     * ga_prefix : 111315
     * title : 江歌遇害后第 294 天，江母和刘鑫终于见面
     * multipic : true
     */

    private int type;
    private int id;
    private String ga_prefix;
    private String title;
    private boolean multipic;
    private List<String> images;

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

    public boolean isMultipic() {
        return multipic;
    }

    public void setMultipic(boolean multipic) {
        this.multipic = multipic;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "StoriesBean{" +
                "type=" + type +
                ", id=" + id +
                ", ga_prefix='" + ga_prefix + '\'' +
                ", title='" + title + '\'' +
                ", multipic=" + multipic +
                ", images=" + images +
                '}';
    }
}
