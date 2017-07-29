package com.wongxd.partymanage.peoplestalk.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyj on 2017/7/19.
 */

public class PeopleTalk implements Serializable {

    /**
     * msg : 获取成功
     * code : 100
     * data : {"conversationList":[{"id":32,"userId":1,"time":"2017-07-11","target":"Kkkkkkkkk","politics":1,"proposal":"Kkkkkkk","autognosis":"Kkkkkkkk","unitId":1,"unitPid":2,"unitName":"","realName":""},{"id":31,"userId":1,"time":"2017-07-11","target":"Gggggggggg","politics":1,"proposal":"Hshgshhss","autognosis":"Gsgsggsgs","unitId":1,"unitPid":2,"unitName":"","realName":""}],"page":{"pageNo":1,"pageSize":10,"totalNum":2,"totalPage":1,"startLoc":0}}
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

    public static class DataBean implements Serializable{
        /**
         * conversationList : [{"id":32,"userId":1,"time":"2017-07-11","target":"Kkkkkkkkk","politics":1,"proposal":"Kkkkkkk","autognosis":"Kkkkkkkk","unitId":1,"unitPid":2,"unitName":"","realName":""},{"id":31,"userId":1,"time":"2017-07-11","target":"Gggggggggg","politics":1,"proposal":"Hshgshhss","autognosis":"Gsgsggsgs","unitId":1,"unitPid":2,"unitName":"","realName":""}]
         * page : {"pageNo":1,"pageSize":10,"totalNum":2,"totalPage":1,"startLoc":0}
         */

        private PageBean page;
        private List<ConversationListBean> conversationList;

        public PageBean getPage() {
            return page;
        }

        public void setPage(PageBean page) {
            this.page = page;
        }

        public List<ConversationListBean> getConversationList() {
            return conversationList;
        }

        public void setConversationList(List<ConversationListBean> conversationList) {
            this.conversationList = conversationList;
        }

        public static class PageBean implements Serializable{
            /**
             * pageNo : 1
             * pageSize : 10
             * totalNum : 2
             * totalPage : 1
             * startLoc : 0
             */

            private int pageNo;
            private int pageSize;
            private int totalNum;
            private int totalPage;
            private int startLoc;

            public int getPageNo() {
                return pageNo;
            }

            public void setPageNo(int pageNo) {
                this.pageNo = pageNo;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public int getTotalNum() {
                return totalNum;
            }

            public void setTotalNum(int totalNum) {
                this.totalNum = totalNum;
            }

            public int getTotalPage() {
                return totalPage;
            }

            public void setTotalPage(int totalPage) {
                this.totalPage = totalPage;
            }

            public int getStartLoc() {
                return startLoc;
            }

            public void setStartLoc(int startLoc) {
                this.startLoc = startLoc;
            }
        }

        public static class ConversationListBean implements Serializable{
            /**
             * id : 32
             * userId : 1
             * time : 2017-07-11
             * target : Kkkkkkkkk
             * politics : 1
             * proposal : Kkkkkkk
             * autognosis : Kkkkkkkk
             * unitId : 1
             * unitPid : 2
             * unitName :
             * realName :
             */

            private int id;
            private int userId;
            private String time;//日期
            private String target;//对象
            private String politics; //政治面貌
            private String proposal;//意见和建议
            private String autognosis;//自我认识和措施
            private int unitId;
            private int unitPid;
            private String unitName;
            private String realName;

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

            public String getPolitics() {
                return politics;
            }

            public void setPolitics(String politics) {
                this.politics = politics;
            }

            public String getProposal() {
                return proposal;
            }

            public void setProposal(String proposal) {
                this.proposal = proposal;
            }

            public String getAutognosis() {
                return autognosis;
            }

            public void setAutognosis(String autognosis) {
                this.autognosis = autognosis;
            }

            public int getUnitId() {
                return unitId;
            }

            public void setUnitId(int unitId) {
                this.unitId = unitId;
            }

            public int getUnitPid() {
                return unitPid;
            }

            public void setUnitPid(int unitPid) {
                this.unitPid = unitPid;
            }

            public String getUnitName() {
                return unitName;
            }

            public void setUnitName(String unitName) {
                this.unitName = unitName;
            }

            public String getRealName() {
                return realName;
            }

            public void setRealName(String realName) {
                this.realName = realName;
            }
        }
    }
}
