package com.wongxd.partymanage.structure.bean;

import java.io.Serializable;

/**
 * Created by zyj on 2017/8/1.
 */

public class StructureBean implements Serializable {

    /**
     * code : 100
     * data : {"id":1,"url":"/static/upload/enclosure/dc2c4747-a18f-440a-b364-b20e73787d44.jpg","unitid":1,"time":"2017-07-13 17:34:18"}
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

    public static class DataBean implements Serializable {
        /**
         * id : 1
         * url : /static/upload/enclosure/dc2c4747-a18f-440a-b364-b20e73787d44.jpg
         * unitid : 1
         * time : 2017-07-13 17:34:18
         */

        private int id;
        private String url;
        private int unitid;
        private String time;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getUnitid() {
            return unitid;
        }

        public void setUnitid(int unitid) {
            this.unitid = unitid;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
