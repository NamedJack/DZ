package com.wongxd.partymanage.doBetter.bean;

import java.io.Serializable;

/**
 * Created by zyj on 2017/7/27.
 */

public class HonerBean implements Serializable {
    private String title;
    private String time;
    private String Story;
    private String img;
    private String belong;


    public HonerBean(String title, String time, String story, String img, String belong) {
        this.title = title;
        this.time = time;
        Story = story;
        this.img = img;
        this.belong = belong;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStory() {
        return Story;
    }

    public void setStory(String story) {
        Story = story;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getBelong() {
        return belong;
    }

    public void setBelong(String belong) {
        this.belong = belong;
    }
}
