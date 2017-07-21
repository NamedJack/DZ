package com.wongxd.partymanage.home.bean;

import java.util.List;

/**
 * Created by wxd1 on 2017/6/29.
 */

public class StudyRecordBean {

    /**
     * msg : 添加成功
     * code : 100
     * data : {"experienceList":[],"page":{"pageNo":1,"pageSize":10,"totalNum":0,"totalPage":0,"startLoc":0}}
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
         * experienceList : []
         * page : {"pageNo":1,"pageSize":10,"totalNum":0,"totalPage":0,"startLoc":0}
         */

        private PageBean page;
        private List<ExperienceBean> experienceList;

        public PageBean getPage() {
            return page;
        }

        public void setPage(PageBean page) {
            this.page = page;
        }

        public List<ExperienceBean> getExperienceList() {
            return experienceList;
        }

        public void setExperienceList(List<ExperienceBean> experienceList) {
            this.experienceList = experienceList;
        }

        public static class PageBean {
            /**
             * pageNo : 1
             * pageSize : 10
             * totalNum : 0
             * totalPage : 0
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


        public static class ExperienceBean {
            private String connId;
            private String typeId;
            private String time;
            private String comment;
            private String id;
            private String title;

            public String getConnId() {
                return connId;
            }

            public void setConnId(String connId) {
                this.connId = connId;
            }

            public String getTypeId() {
                return typeId;
            }

            public void setTypeId(String typeId) {
                this.typeId = typeId;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
