package com.wongxd.partymanage.partycontact.bean;

/**
 * Created by zyj on 2017/7/31.
 */

public class ContactDetailBean {
    /**
     * msg : 成功
     * code : 100
     * data : {"id":17,"userId":1,"time":"2017-07-11","target":"Hhhhhhh","helpRecord":"Hhhhh","implement":"Hhhhh","unitId":1,"unitPid":2}
     */

    private String msg;
    private String code;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 17
         * userId : 1
         * time : 2017-07-11
         * target : Hhhhhhh
         * helpRecord : Hhhhh
         * implement : Hhhhh
         * unitId : 1
         * unitPid : 2
         */

        private String id;
        private String userId;
        private String time;
        private String target;
        private String helpRecord;
        private String implement;
        private String unitId;
        private String unitPid;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getTarget() {
            return target;
        }

        public void setTarget(String target) {
            this.target = target;
        }

        public String getHelpRecord() {
            return helpRecord;
        }

        public void setHelpRecord(String helpRecord) {
            this.helpRecord = helpRecord;
        }

        public String getImplement() {
            return implement;
        }

        public void setImplement(String implement) {
            this.implement = implement;
        }

        public String getUnitId() {
            return unitId;
        }

        public void setUnitId(String unitId) {
            this.unitId = unitId;
        }

        public String getUnitPid() {
            return unitPid;
        }

        public void setUnitPid(String unitPid) {
            this.unitPid = unitPid;
        }
    }
}
