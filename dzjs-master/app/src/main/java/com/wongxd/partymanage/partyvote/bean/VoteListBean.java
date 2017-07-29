package com.wongxd.partymanage.partyvote.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyj on 2017/7/28.
 */

public class VoteListBean implements Serializable {

    /**
     * msg : 请求成功！
     * total : 1
     * code : 100
     * data : [{"lastTime":"2017-07-30 00:00:00","isReception":2,"id":14,"state":1,"title":"感动中国十大人物","login":"admin"}]
     * pageNo : 1
     * totalPage : 1
     */

    private String msg;
    private int total;
    private String code;
    private int pageNo;
    private int totalPage;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * lastTime : 2017-07-30 00:00:00
         * isReception : 2
         * id : 14
         * state : 1
         * title : 感动中国十大人物
         * login : admin
         */

        private String lastTime;
        private int isReception;
        private int id;
        private int state;
        private String title;
        private String login;
        private String realName;

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getLastTime() {
            return lastTime;
        }

        public void setLastTime(String lastTime) {
            this.lastTime = lastTime;
        }

        public int getIsReception() {
            return isReception;
        }

        public void setIsReception(int isReception) {
            this.isReception = isReception;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }
    }
}
