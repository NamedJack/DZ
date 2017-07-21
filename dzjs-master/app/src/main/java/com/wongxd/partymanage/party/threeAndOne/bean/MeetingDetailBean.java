package com.wongxd.partymanage.party.threeAndOne.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wxd1 on 2017/6/26.
 */

public class MeetingDetailBean extends BaseObservable {

    /**
     * code : 100
     * data : {"isDataclerk":"2","dataclerkname":"aaaa","Signstatus":2,"Meetingnotice":{"id":51,"name":"管理第一支部民主评议","noticeName":"关于民主评议的通知","time":"2017-08-30 08:00:00","address":"A4会议室","content":"<p>各位支部党员：<\/p><p>&nbsp; &nbsp; &nbsp; &nbsp;请提前做好党员个人总结计划等会议准备，按时参会！<\/p>","meetingType":4,"meetingRecord":"<p>规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划<\/p><p><br><\/p>","userId":4,"status":1,"code":"705315","untilId":1,"meetingId":"","unitPid":2,"endtime":"2017-08-30 09:00:00","longtime":1,"creattime":"2017-06-20 10:43:26","state":2,"publishere":2,"publishereUser":5,"userName":"","meetingTypeName":""},"username":["aaaa","ccc","eee","tao"]}
     */

    private int code;
    private DataBean data;

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

    public static class DataBean extends BaseObservable implements Serializable {
        /**
         * isDataclerk : 2
         * dataclerkname : aaaa
         * Signstatus : 2
         * Meetingnotice : {"id":51,"name":"管理第一支部民主评议","noticeName":"关于民主评议的通知","time":"2017-08-30 08:00:00","address":"A4会议室","content":"<p>各位支部党员：<\/p><p>&nbsp; &nbsp; &nbsp; &nbsp;请提前做好党员个人总结计划等会议准备，按时参会！<\/p>","meetingType":4,"meetingRecord":"<p>规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划规划<\/p><p><br><\/p>","userId":4,"status":1,"code":"705315","untilId":1,"meetingId":"","unitPid":2,"endtime":"2017-08-30 09:00:00","longtime":1,"creattime":"2017-06-20 10:43:26","state":2,"publishere":2,"publishereUser":5,"userName":"","meetingTypeName":""}
         * username : ["aaaa","ccc","eee","tao"]
         */

        public static class Enclosure implements Serializable {

            /**
             * id : 50
             * filename : 党员承诺.png
             * fileurl : /static/upload/enclosure/14fb4548-60b6-491f-8e98-2ad41e1f6e86.png
             * meetingid : 59
             */

            private int id;
            private String filename;
            private String fileurl;
            private int meetingid;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getFilename() {
                return filename;
            }

            public void setFilename(String filename) {
                this.filename = filename;
            }

            public String getFileurl() {
                return fileurl;
            }

            public void setFileurl(String fileurl) {
                this.fileurl = fileurl;
            }

            public int getMeetingid() {
                return meetingid;
            }

            public void setMeetingid(int meetingid) {
                this.meetingid = meetingid;
            }
        }

        private List<Enclosure> enclosure;

        public List<Enclosure> getEnclosure() {
            return enclosure;
        }

        public void setEnclosure(List<Enclosure> enclosure) {
            this.enclosure = enclosure;
        }

        private String isDataclerk;
        private String dataclerkname;
        private int Signstatus;
        private MeetingnoticeBean Meetingnotice;
        private List<String> username;

        @Bindable
        public String getIsDataclerk() {
            return isDataclerk;
        }

        public void setIsDataclerk(String isDataclerk) {
            this.isDataclerk = isDataclerk;
        }

        @Bindable
        public String getDataclerkname() {
            return dataclerkname;
        }

        public void setDataclerkname(String dataclerkname) {
            this.dataclerkname = dataclerkname;
        }

        @Bindable
        public int getSignstatus() {
            return Signstatus;
        }

        public void setSignstatus(int Signstatus) {
            this.Signstatus = Signstatus;
        }

        @Bindable
        public MeetingnoticeBean getMeetingnotice() {
            return Meetingnotice;
        }

        public void setMeetingnotice(MeetingnoticeBean Meetingnotice) {
            this.Meetingnotice = Meetingnotice;
        }

        @Bindable
        public List<String> getUsername() {
            return username;
        }

        public void setUsername(List<String> username) {
            this.username = username;
        }

        public static class MeetingnoticeBean implements Serializable {
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
             * state : 2  签到状态 0.未开始签到 1.开始签到 2.结束签到(需补课)
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
}
