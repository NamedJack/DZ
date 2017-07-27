package com.wongxd.partymanage.promise.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by json on 2017/7/11.
 */

public class PromiseBean implements Serializable{


    /**
     * code : 100
     * data : [{"id":1,"userId":1,"year":"2017","study":"1  12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠","work":"松鼠有几只，12345；12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠","unitId":1,"styleimage":null,"contact":null,"partParty":null,"unitPid":2,"username":"aaaa","unitname":"第一党支部","user":null,"unit":null},{"id":3,"userId":4,"year":"2017","study":"1请填写学习目标B","work":"请填写工作目标AAAAA","unitId":1,"styleimage":"请填写作风形象目标CCCCC","contact":"请填写联系群众工作目标DDDD","partParty":"请填写参加党组织活动目标EEEE","unitPid":2,"username":"ddd","unitname":"第一党支部","user":null,"unit":null},{"id":2,"userId":1,"year":"2016","study":"2  12345，上山打老虎，老虎打不到，看见小松鼠11","work":"松鼠有几只，12345；","unitId":1,"styleimage":"松鼠有几只，12345；","contact":"松鼠有几只，12345；","partParty":"松鼠有几只，12345；","unitPid":2,"username":"aaaa","unitname":"第一党支部","user":null,"unit":null},{"id":4,"userId":4,"year":"2016","study":"2请填写学习目标s","work":"请填写工作目标a","unitId":1,"styleimage":"请填写作风形象目标s","contact":"请填写联系群众工作目标I","partParty":"请填写参加党组织活动目标g","unitPid":2,"username":"ddd","unitname":"第一党支部","user":null,"unit":null},{"id":5,"userId":4,"year":"2015","study":"3请填写学习目标a","work":"请填写工作目标s","unitId":1,"styleimage":"请填写作风形象目标s","contact":"请填写联系群众工作目标s","partParty":"请填写参加党组织活动目标s","unitPid":2,"username":"ddd","unitname":"第一党支部","user":null,"unit":null}]
     * page : {"pageNo":1,"pagetotle":1,"pageSize":15}
     */

    private int code;
    private PageBean page;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class PageBean implements Serializable{
        /**
         * pageNo : 1
         * pagetotle : 1
         * pageSize : 15
         */

        private int pageNo;
        private int pagetotle;
        private int pageSize;

        public int getPageNo() {
            return pageNo;
        }

        public void setPageNo(int pageNo) {
            this.pageNo = pageNo;
        }

        public int getPagetotle() {
            return pagetotle;
        }

        public void setPagetotle(int pagetotle) {
            this.pagetotle = pagetotle;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }
    }

    public static class DataBean implements Serializable{
        /**
         * id : 1
         * userId : 1
         * year : 2017
         * study : 1  12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠
         * work : 松鼠有几只，12345；12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠12345，上山打老虎，老虎打不到，看见小松鼠
         * unitId : 1
         * styleimage : null
         * contact : null
         * partParty : null
         * unitPid : 2
         * username : aaaa
         * unitname : 第一党支部
         * user : null
         * unit : null
         */

        private int id;
        private int userId;
        private String year;
        private String study;
        private String work;
        private int unitId;
        private String styleimage;
        private String contact;
        private String partParty;
        private int unitPid;
        private String username;
        private String unitname;
        private Object user;
        private Object unit;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getStudy() {
            return study;
        }

        public void setStudy(String study) {
            this.study = study;
        }

        public String getWork() {
            return work;
        }

        public void setWork(String work) {
            this.work = work;
        }

        public int getUnitId() {
            return unitId;
        }

        public void setUnitId(int unitId) {
            this.unitId = unitId;
        }

        public String getStyleimage() {
            return styleimage;
        }

        public void setStyleimage(String styleimage) {
            this.styleimage = styleimage;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getPartParty() {
            return partParty;
        }

        public void setPartParty(String partParty) {
            this.partParty = partParty;
        }

        public int getUnitPid() {
            return unitPid;
        }

        public void setUnitPid(int unitPid) {
            this.unitPid = unitPid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUnitname() {
            return unitname;
        }

        public void setUnitname(String unitname) {
            this.unitname = unitname;
        }

        public Object getUser() {
            return user;
        }

        public void setUser(Object user) {
            this.user = user;
        }

        public Object getUnit() {
            return unit;
        }

        public void setUnit(Object unit) {
            this.unit = unit;
        }
    }
}
