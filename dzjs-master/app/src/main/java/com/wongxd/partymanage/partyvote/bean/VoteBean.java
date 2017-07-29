package com.wongxd.partymanage.partyvote.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyj on 2017/7/28.
 */

public class VoteBean implements Serializable{


    /**
     * msg : 请求成功！
     * code : 100
     * data : [{"id":8,"voteId":14,"name":"支月英(扎根乡村36年的最美教师)","img":"static/upload/82872090-8f25-446f-bc81-908f1ae36ba5.png","votes":0,"falg":1},{"id":9,"voteId":14,"name":"秦玥飞(耶鲁村官)","img":"static/upload/be9fa2c5-cdc2-48b4-9bfe-7f49bd208ced.png","votes":0,"falg":1},{"id":10,"voteId":14,"name":"张超(壮烈牺牲的歼15飞行员)","img":"static/upload/70260d0b-02e0-43f9-b04d-1d18ab4a4f22.png","votes":0,"falg":1},{"id":11,"voteId":14,"name":"李万君(大国工匠)","img":"static/upload/b934411c-3b0a-4786-8a38-1d4861587786.png","votes":1,"falg":1},{"id":17,"voteId":14,"name":"孙家栋(功勋科学家)","img":"static/upload/ce487832-5aa8-4c67-92a8-a4e53116a5d0.png","votes":1,"falg":2},{"id":18,"voteId":14,"name":"王锋(火海救人英雄)","img":"static/upload/59bb4d07-14ff-4cda-bdde-ca7e2dc49069.png","votes":1,"falg":2}]
     * havecast : 2
     * data1 : {"id":14,"title":"感动中国十大人物","content":"","img":"static/upload/5d50974c-c289-4c9b-b2d0-fed4f70a54b1.jpg","number":2,"startTime":"2017-07-21 09:14:19","lastTime":"2017-07-30 00:00:00","isRegistered":1,"unitId":1,"unitPid":null,"isReception":1,"launchId":1,"state":1,"showState":1,"releaseState":2,"time":"2017-07-28 17:25:34"}
     */

    private String msg;
    private String code;
    private int havecast;
    private Data1Bean data1;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getHavecast() {
        return havecast;
    }

    public void setHavecast(int havecast) {
        this.havecast = havecast;
    }

    public Data1Bean getData1() {
        return data1;
    }

    public void setData1(Data1Bean data1) {
        this.data1 = data1;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class Data1Bean {
        /**
         * id : 14
         * title : 感动中国十大人物
         * content :
         * img : static/upload/5d50974c-c289-4c9b-b2d0-fed4f70a54b1.jpg
         * number : 2
         * startTime : 2017-07-21 09:14:19
         * lastTime : 2017-07-30 00:00:00
         * isRegistered : 1
         * unitId : 1
         * unitPid : null
         * isReception : 1
         * launchId : 1
         * state : 1
         * showState : 1
         * releaseState : 2
         * time : 2017-07-28 17:25:34
         */

        private int id;
        private String title;
        private String content;
        private String img;
        private int number;
        private String startTime;
        private String lastTime;
        private int isRegistered;
        private int unitId;
        private Object unitPid;
        private int isReception;
        private int launchId;
        private int state;
        private int showState;
        private int releaseState;
        private String time;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getLastTime() {
            return lastTime;
        }

        public void setLastTime(String lastTime) {
            this.lastTime = lastTime;
        }

        public int getIsRegistered() {
            return isRegistered;
        }

        public void setIsRegistered(int isRegistered) {
            this.isRegistered = isRegistered;
        }

        public int getUnitId() {
            return unitId;
        }

        public void setUnitId(int unitId) {
            this.unitId = unitId;
        }

        public Object getUnitPid() {
            return unitPid;
        }

        public void setUnitPid(Object unitPid) {
            this.unitPid = unitPid;
        }

        public int getIsReception() {
            return isReception;
        }

        public void setIsReception(int isReception) {
            this.isReception = isReception;
        }

        public int getLaunchId() {
            return launchId;
        }

        public void setLaunchId(int launchId) {
            this.launchId = launchId;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getShowState() {
            return showState;
        }

        public void setShowState(int showState) {
            this.showState = showState;
        }

        public int getReleaseState() {
            return releaseState;
        }

        public void setReleaseState(int releaseState) {
            this.releaseState = releaseState;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }

    public static class DataBean {
        /**
         * id : 8
         * voteId : 14
         * name : 支月英(扎根乡村36年的最美教师)
         * img : static/upload/82872090-8f25-446f-bc81-908f1ae36ba5.png
         * votes : 0
         * falg : 1
         */

        private boolean isSelected = false;
        private int id;
        private int voteId;
        private String name;
        private String img;
        private int votes;
        private int falg;

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getVoteId() {
            return voteId;
        }

        public void setVoteId(int voteId) {
            this.voteId = voteId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getVotes() {
            return votes;
        }

        public void setVotes(int votes) {
            this.votes = votes;
        }

        public int getFalg() {
            return falg;
        }

        public void setFalg(int falg) {
            this.falg = falg;
        }
    }
}
