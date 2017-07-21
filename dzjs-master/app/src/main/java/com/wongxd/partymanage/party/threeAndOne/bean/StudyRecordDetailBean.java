package com.wongxd.partymanage.party.threeAndOne.bean;

import java.util.List;

/**
 * Created by wxd1 on 2017/7/3.
 */

public class StudyRecordDetailBean {

    /**
     * msg : 添加成功
     * code : 100
     * data : {"imgs":["upload/experience/img1499071519463_3366.jpg"],"comment":"ahfla","time":"201773","title":"hah"}
     */

    private String msg;
    private int code;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * imgs : ["upload/experience/img1499071519463_3366.jpg"]
         * comment : ahfla
         * time : 201773
         * title : hah
         */

        private String comment;
        private String time;
        private String title;
        private List<String> imgs;

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getImgs() {
            return imgs;
        }

        public void setImgs(List<String> imgs) {
            this.imgs = imgs;
        }
    }
}
