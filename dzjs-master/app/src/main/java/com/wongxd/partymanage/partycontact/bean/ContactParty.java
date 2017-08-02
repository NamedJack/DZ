package com.wongxd.partymanage.partycontact.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyj on 2017/7/20.
 */

public class ContactParty implements Serializable {

    /**
     * msg : 上传成功
     * code : 100
     * data : {"contactList":[{"id":17,"time":"2017-07-11","helpRecord":"Hhhhh","target":"Hhhhhhh"},{"id":16,"time":"2017-07-11","helpRecord":"Wwwwww","target":"Wwww"},{"id":15,"time":"2017-07-11","helpRecord":"Hhhhhh","target":"Hhhhh"},{"id":12,"time":"2017-07-07","helpRecord":"公共关系wywywwywww最重要w最重要yw最重要yww最重要一样w最重要一样zw最重要一样zww最重要一样zwww最重要一样zwwww最重要一样小心翼翼w最","target":"将61"},{"id":11,"time":"2017-07-07","helpRecord":"你一直公共卫生学院营养价","target":"健健康康m哦，mogmimmimmmin\u2006nmmin"},{"id":9,"time":"2017-07-07","helpRecord":"命名为省级文物保护单位？我\n螺旋部至","target":"啊，m哦，m哦！"},{"id":8,"time":"2017-07-07","helpRecord":"公共膜","target":"个，d的，"},{"id":5,"time":"2017-06-21","helpRecord":"kdaksdskkdkcjhsdechenfhjdhfkhsfjkhjhjkhjkhkjhkjhkkkkkkkkkjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj家盛大空间等哈看圣诞节jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjdfssjsjdfk","target":"Hffg "},{"id":4,"time":"2017-06-21","helpRecord":"kdaksdskkdkcjhsdechenfhjdhfkhsfjkhjhjkhjkhkjhkjhkkkkkkkkkjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjdfssjsjdfk","target":"Sssss"},{"id":3,"time":"2017-06-21","helpRecord":"kdaksdskkdkcjhsdechenfhjdhfkhsfjkhjhjkhjkhkjhkjhkkkkkkkkkjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj俺都快来受打击了卡三等奖jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjdfssjsjdfk","target":"Sssss"}],"page":{"pageNo":1,"pageSize":10,"totalNum":13,"totalPage":2,"startLoc":0}}
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
         * contactList : [{"id":17,"time":"2017-07-11","helpRecord":"Hhhhh","target":"Hhhhhhh"},{"id":16,"time":"2017-07-11","helpRecord":"Wwwwww","target":"Wwww"},{"id":15,"time":"2017-07-11","helpRecord":"Hhhhhh","target":"Hhhhh"},{"id":12,"time":"2017-07-07","helpRecord":"公共关系wywywwywww最重要w最重要yw最重要yww最重要一样w最重要一样zw最重要一样zww最重要一样zwww最重要一样zwwww最重要一样小心翼翼w最","target":"将61"},{"id":11,"time":"2017-07-07","helpRecord":"你一直公共卫生学院营养价","target":"健健康康m哦，mogmimmimmmin\u2006nmmin"},{"id":9,"time":"2017-07-07","helpRecord":"命名为省级文物保护单位？我\n螺旋部至","target":"啊，m哦，m哦！"},{"id":8,"time":"2017-07-07","helpRecord":"公共膜","target":"个，d的，"},{"id":5,"time":"2017-06-21","helpRecord":"kdaksdskkdkcjhsdechenfhjdhfkhsfjkhjhjkhjkhkjhkjhkkkkkkkkkjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj家盛大空间等哈看圣诞节jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjdfssjsjdfk","target":"Hffg "},{"id":4,"time":"2017-06-21","helpRecord":"kdaksdskkdkcjhsdechenfhjdhfkhsfjkhjhjkhjkhkjhkjhkkkkkkkkkjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjdfssjsjdfk","target":"Sssss"},{"id":3,"time":"2017-06-21","helpRecord":"kdaksdskkdkcjhsdechenfhjdhfkhsfjkhjhjkhjkhkjhkjhkkkkkkkkkjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj俺都快来受打击了卡三等奖jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjdfssjsjdfk","target":"Sssss"}]
         * page : {"pageNo":1,"pageSize":10,"totalNum":13,"totalPage":2,"startLoc":0}
         */

        private PageBean page;
        private List<ContactListBean> contactList;

        public PageBean getPage() {
            return page;
        }

        public void setPage(PageBean page) {
            this.page = page;
        }

        public List<ContactListBean> getContactList() {
            return contactList;
        }

        public void setContactList(List<ContactListBean> contactList) {
            this.contactList = contactList;
        }

        public static class PageBean {
            /**
             * pageNo : 1
             * pageSize : 10
             * totalNum : 13
             * totalPage : 2
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

        public static class ContactListBean implements Serializable{
            /**
             * id : 17
             * time : 2017-07-11
             * helpRecord : Hhhhh
             * target : Hhhhhhh
             */

            private String id;
            private String time;
            private String helpRecord;
            private String target;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getHelpRecord() {
                return helpRecord;
            }

            public void setHelpRecord(String helpRecord) {
                this.helpRecord = helpRecord;
            }

            public String getTarget() {
                return target;
            }

            public void setTarget(String target) {
                this.target = target;
            }
        }
    }
}
