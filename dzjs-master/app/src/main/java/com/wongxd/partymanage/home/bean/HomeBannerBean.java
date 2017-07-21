package com.wongxd.partymanage.home.bean;

import java.util.List;

/**
 * Created by wxd1 on 2017/6/20.
 */

public class HomeBannerBean {

    /**
     * msg : 请求成功
     * code : 100
     * data : [{"id":60,"articletypeId":"","unitId":"","articleTitle":"习近平会见俄罗斯总统普京：全面深化上海合作组织各领域合作","articleImg":"static/upload/00a53dce-d4a1-4c97-8290-93c69d30e79b.jpg","articleContact":"","issueTime":"","browseNumber":"","sort":"","isCarousel":"","adminId":"","unitName":"","adminName":""}]
     */

    private String msg;
    private int code;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 60
         * articletypeId :
         * unitId :
         * articleTitle : 习近平会见俄罗斯总统普京：全面深化上海合作组织各领域合作
         * articleImg : static/upload/00a53dce-d4a1-4c97-8290-93c69d30e79b.jpg
         * articleContact :
         * issueTime :
         * browseNumber :
         * sort :
         * isCarousel :
         * adminId :
         * unitName :
         * adminName :
         */

        private int id;
        private String articletypeId;
        private String unitId;
        private String articleTitle;
        private String articleImg;
        private String articleContact;
        private String issueTime;
        private String browseNumber;
        private String sort;
        private String isCarousel;
        private String adminId;
        private String unitName;
        private String adminName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getArticletypeId() {
            return articletypeId;
        }

        public void setArticletypeId(String articletypeId) {
            this.articletypeId = articletypeId;
        }

        public String getUnitId() {
            return unitId;
        }

        public void setUnitId(String unitId) {
            this.unitId = unitId;
        }

        public String getArticleTitle() {
            return articleTitle;
        }

        public void setArticleTitle(String articleTitle) {
            this.articleTitle = articleTitle;
        }

        public String getArticleImg() {
            return articleImg;
        }

        public void setArticleImg(String articleImg) {
            this.articleImg = articleImg;
        }

        public String getArticleContact() {
            return articleContact;
        }

        public void setArticleContact(String articleContact) {
            this.articleContact = articleContact;
        }

        public String getIssueTime() {
            return issueTime;
        }

        public void setIssueTime(String issueTime) {
            this.issueTime = issueTime;
        }

        public String getBrowseNumber() {
            return browseNumber;
        }

        public void setBrowseNumber(String browseNumber) {
            this.browseNumber = browseNumber;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getIsCarousel() {
            return isCarousel;
        }

        public void setIsCarousel(String isCarousel) {
            this.isCarousel = isCarousel;
        }

        public String getAdminId() {
            return adminId;
        }

        public void setAdminId(String adminId) {
            this.adminId = adminId;
        }

        public String getUnitName() {
            return unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
        }

        public String getAdminName() {
            return adminName;
        }

        public void setAdminName(String adminName) {
            this.adminName = adminName;
        }
    }
}
