package com.wongxd.partymanage.mqtt.bean;

import java.io.Serializable;

/**
 * Created by json on 2017/7/13.
 */

public class PushMessage implements Serializable{
    String bigIcon;//大图标
    String smallIcon;//小图标
    String contentTitle;//标题
    String contentText;//内容
    String contentInfo;//右侧时间下面的内容
    String contentNumber;//右侧时间下面的数字  与contentInfo 只能显示一个
    String subText;//第三行显示的内容

    public String getBigIcon() {
        return bigIcon;
    }

    public void setBigIcon(String bigIcon) {
        this.bigIcon = bigIcon;
    }

    public String getSmallIcon() {
        return smallIcon;
    }

    public void setSmallIcon(String smallIcon) {
        this.smallIcon = smallIcon;
    }

    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    public String getContentInfo() {
        return contentInfo;
    }

    public void setContentInfo(String contentInfo) {
        this.contentInfo = contentInfo;
    }

    public String getContentNumber() {
        return contentNumber;
    }

    public void setContentNumber(String contentNumber) {
        this.contentNumber = contentNumber;
    }

    public String getSubText() {
        return subText;
    }

    public void setSubText(String subText) {
        this.subText = subText;
    }
}
