package com.minglei.jread.beans.zhihu.zhihudaily;

import java.io.Serializable;
import java.util.List;

/**
 * Created by minglei on 2017/11/27.
 */

public class StoriesEntity implements Serializable {

    private String date;
    private List<StoriesBean> stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    @Override
    public String toString() {
        return "StoriesEntity{" +
                "date='" + date + '\'' +
                ", stories=" + stories +
                '}';
    }
}
