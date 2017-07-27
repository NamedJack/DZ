package com.wongxd.partymanage.styleParty.bean;

/**
 * Created by zyj on 2017/7/23.
 */

public class StyleParty {
    private String img;
    private String name;
    private String story;

    public StyleParty(String img, String name, String story) {
        this.img = img;
        this.name = name;
        this.story = story;
    }


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }
}
