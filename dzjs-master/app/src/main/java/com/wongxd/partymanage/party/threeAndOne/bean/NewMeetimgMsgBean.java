package com.wongxd.partymanage.party.threeAndOne.bean;

import java.util.List;

/**
 * Created by wxd1 on 2017/6/22.
 */

public class NewMeetimgMsgBean {

    /**
     * code : 100
     * data : [{"id":51,"name":"管理第一支部民主评议","noticeName":"关于民主评议的通知","time":"2017-08-30 08:00:00","address":"A4会议室","content":"<p>各位支部党员：<\/p><p>&nbsp; &nbsp; &nbsp; &nbsp;请提前做好党员个人总结计划等会议准备，按时参会！<\/p>","meetingType":4,"meetingRecord":"<p>规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划<\/p><p><br><\/p>","userId":4,"status":1,"code":"705315","untilId":1,"meetingId":"","unitPid":2,"endtime":"2017-08-30 09:00:00","longtime":1,"creattime":"2017-06-20 10:43:26","state":2,"publishere":2,"publishereUser":5,"userName":"","meetingTypeName":""},{"id":52,"name":"第一次会议","noticeName":"会议","time":"2017-06-30 08:00:00","address":"操场","content":"<p>会议通知<\/p>","meetingType":1,"meetingRecord":"<p><img src=\"../static/upload/img/c8827153-35e0-4106-9899-f454937c0008.png\" alt=\"7党费-\"><br><\/p><p><br><\/p>","userId":4,"status":0,"code":"0","untilId":1,"meetingId":"","unitPid":2,"endtime":"2017-06-30 10:00:00","longtime":2,"creattime":"2017-06-20 15:48:27","state":2,"publishere":2,"publishereUser":5,"userName":"","meetingTypeName":""}]
     */

    private int code;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 51
         * name : 管理第一支部民主评议
         * noticeName : 关于民主评议的通知
         * time : 2017-08-30 08:00:00
         * address : A4会议室
         * content : <p>各位支部党员：</p><p>&nbsp; &nbsp; &nbsp; &nbsp;请提前做好党员个人总结计划等会议准备，按时参会！</p>
         * meetingType : 4
         * meetingRecord : <p>规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划</p><p><br></p>
         * userId : 4
         * status : 1
         * code : 705315
         * untilId : 1
         * meetingId :
         * unitPid : 2
         * endtime : 2017-08-30 09:00:00
         * longtime : 1
         * creattime : 2017-06-20 10:43:26
         * state : 2
         * publishere : 2
         * publishereUser : 5
         * userName :
         * meetingTypeName :
         */

        private int id;
        private String name;
        private String noticeName;
        private String time;
        private String address;
        private String content;
        private int meetingType;
        private String meetingRecord;
        private int userId;
        private int status;
        private String code;
        private int untilId;
        private String meetingId;
        private int unitPid;
        private String endtime;
        private int longtime;
        private String creattime;
        private int state;
        private int publishere;
        private int publishereUser;
        private String userName;
        private String meetingTypeName;
        private int isRead; // 是否已读 1：未读，2：已读

        public int getIsRead() {
            return isRead;
        }

        public void setIsRead(int isRead) {
            this.isRead = isRead;
        }
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNoticeName() {
            return noticeName;
        }

        public void setNoticeName(String noticeName) {
            this.noticeName = noticeName;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getMeetingType() {
            return meetingType;
        }

        public void setMeetingType(int meetingType) {
            this.meetingType = meetingType;
        }

        public String getMeetingRecord() {
            return meetingRecord;
        }

        public void setMeetingRecord(String meetingRecord) {
            this.meetingRecord = meetingRecord;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getUntilId() {
            return untilId;
        }

        public void setUntilId(int untilId) {
            this.untilId = untilId;
        }

        public String getMeetingId() {
            return meetingId;
        }

        public void setMeetingId(String meetingId) {
            this.meetingId = meetingId;
        }

        public int getUnitPid() {
            return unitPid;
        }

        public void setUnitPid(int unitPid) {
            this.unitPid = unitPid;
        }

        public String getEndtime() {
            return endtime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }

        public int getLongtime() {
            return longtime;
        }

        public void setLongtime(int longtime) {
            this.longtime = longtime;
        }

        public String getCreattime() {
            return creattime;
        }

        public void setCreattime(String creattime) {
            this.creattime = creattime;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getPublishere() {
            return publishere;
        }

        public void setPublishere(int publishere) {
            this.publishere = publishere;
        }

        public int getPublishereUser() {
            return publishereUser;
        }

        public void setPublishereUser(int publishereUser) {
            this.publishereUser = publishereUser;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getMeetingTypeName() {
            return meetingTypeName;
        }

        public void setMeetingTypeName(String meetingTypeName) {
            this.meetingTypeName = meetingTypeName;
        }
    }
}
