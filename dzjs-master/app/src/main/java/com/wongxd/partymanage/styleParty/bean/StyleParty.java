package com.wongxd.partymanage.styleParty.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyj on 2017/7/23.
 */

public class StyleParty {

    /**
     * msg : 获取成功
     * code : 100
     * data : {"mienList":[{"id":2,"userId":"","userName":"的第三方的地方是","unitId":1,"unitPid":2,"describe":"4894156","time":"2017-06-30","img":"upload/mien/031c79f7-694c-483d-9be7-e4f9f0342881.png"},{"id":3,"userId":"","userName":"大叔大婶","unitId":1,"unitPid":2,"describe":"<p>大师的大师<\/p>","time":"2017-06-27","img":"upload/mien/674a4f3d-d955-40c9-8250-f3d11cb97d0c.jpg"},{"id":4,"userId":"","userName":"utuut","unitId":1,"unitPid":2,"describe":"<p>图士<\/p>","time":"2017-06-27","img":"upload/mien/0b351717-7490-4cf0-b05c-773c11de9df9.png"},{"id":7,"userId":"","userName":"福利","unitId":1,"unitPid":2,"describe":"<p>探探谈谈拖拖拖拖拖拖拖拖拖拖拖拖拖拖拖拖拖拖<\/p>","time":"2017-07-06","img":"upload/mien/aea172db-07f1-437b-853f-0c5a94334757.jpg"}],"page":{"pageNo":1,"pageSize":10,"totalNum":4,"totalPage":1,"startLoc":0}}
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
         * mienList : [{"id":2,"userId":"","userName":"的第三方的地方是","unitId":1,"unitPid":2,"describe":"4894156","time":"2017-06-30","img":"upload/mien/031c79f7-694c-483d-9be7-e4f9f0342881.png"},{"id":3,"userId":"","userName":"大叔大婶","unitId":1,"unitPid":2,"describe":"<p>大师的大师<\/p>","time":"2017-06-27","img":"upload/mien/674a4f3d-d955-40c9-8250-f3d11cb97d0c.jpg"},{"id":4,"userId":"","userName":"utuut","unitId":1,"unitPid":2,"describe":"<p>图士<\/p>","time":"2017-06-27","img":"upload/mien/0b351717-7490-4cf0-b05c-773c11de9df9.png"},{"id":7,"userId":"","userName":"福利","unitId":1,"unitPid":2,"describe":"<p>探探谈谈拖拖拖拖拖拖拖拖拖拖拖拖拖拖拖拖拖拖<\/p>","time":"2017-07-06","img":"upload/mien/aea172db-07f1-437b-853f-0c5a94334757.jpg"}]
         * page : {"pageNo":1,"pageSize":10,"totalNum":4,"totalPage":1,"startLoc":0}
         */

        private PageBean page;
        private List<MienListBean> mienList;

        public PageBean getPage() {
            return page;
        }

        public void setPage(PageBean page) {
            this.page = page;
        }

        public List<MienListBean> getMienList() {
            return mienList;
        }

        public void setMienList(List<MienListBean> mienList) {
            this.mienList = mienList;
        }

        public static class PageBean {
            /**
             * pageNo : 1
             * pageSize : 10
             * totalNum : 4
             * totalPage : 1
             * startLoc : 0
             */

            private int pageNo;
            private String pageSize;
            private String totalNum;
            private int totalPage;
            private String startLoc;

            public int getPageNo() {
                return pageNo;
            }

            public void setPageNo(int pageNo) {
                this.pageNo = pageNo;
            }

            public String getPageSize() {
                return pageSize;
            }

            public void setPageSize(String pageSize) {
                this.pageSize = pageSize;
            }

            public String getTotalNum() {
                return totalNum;
            }

            public void setTotalNum(String totalNum) {
                this.totalNum = totalNum;
            }

            public int getTotalPage() {
                return totalPage;
            }

            public void setTotalPage(int totalPage) {
                this.totalPage = totalPage;
            }

            public String getStartLoc() {
                return startLoc;
            }

            public void setStartLoc(String startLoc) {
                this.startLoc = startLoc;
            }
        }

        public static class MienListBean implements Serializable{
            /**
             * id : 2
             * userId :
             * userName : 的第三方的地方是
             * unitId : 1
             * unitPid : 2
             * describe : 4894156
             * time : 2017-06-30
             * img : upload/mien/031c79f7-694c-483d-9be7-e4f9f0342881.png
             */

            private String id;
            private String userId;
            private String userName;
            private String unitId;
            private String unitPid;
            private String describe;
            private String time;
            private String img;

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

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
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

            public String getDescribe() {
                return describe;
            }

            public void setDescribe(String describe) {
                this.describe = describe;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }
        }
    }
}
