package com.wongxd.partymanage.home.bean;

import java.util.List;

/**
 * Created by wxd1 on 2017/6/20.
 */

public class HomeNewsBean {

    /**
     * msg : 请求成功！
     * total : 17
     * code : 100
     * data : [{"id":63,"articletypeId":"","unitId":"","articleTitle":"习近平签署命令，授予驻港部队某旅特战一连荣誉称号","articleImg":"static/upload/a13818f2-b546-41ea-b739-e5c2c542fa61.png","articleContact":"","issueTime":"2017-06-14 15:30:53","browseNumber":3,"sort":"","isCarousel":"","adminId":"","unitName":"","adminName":""},{"id":61,"articletypeId":"","unitId":"","articleTitle":"习近平会见俄罗斯总统普京：全面深化上海合作组织各领域合作","articleImg":"static/upload/00a53dce-d4a1-4c97-8290-93c69d30e79b.jpg","articleContact":"","issueTime":"2017-06-09 15:42:11","browseNumber":17,"sort":"","isCarousel":"","adminId":"","unitName":"","adminName":""},{"id":59,"articletypeId":"","unitId":"","articleTitle":"习近平会见俄罗斯总统普京：全面深化上海合作组织各领域合作","articleImg":"static/upload/00a53dce-d4a1-4c97-8290-93c69d30e79b.jpg","articleContact":"","issueTime":"2017-06-08 15:00:35","browseNumber":13,"sort":"","isCarousel":"","adminId":"","unitName":"","adminName":""},{"id":58,"articletypeId":"","unitId":"","articleTitle":"习近平会见俄罗斯总统普京：全面深化上海合作组织各领域合作","articleImg":"static/upload/00a53dce-d4a1-4c97-8290-93c69d30e79b.jpg","articleContact":"","issueTime":"2017-06-07 11:52:03","browseNumber":412,"sort":"","isCarousel":"","adminId":"","unitName":"","adminName":""},{"id":57,"articletypeId":"","unitId":"","articleTitle":"习近平会见俄罗斯总统普京：全面深化上海合作组织各领域合作","articleImg":"static/upload/00a53dce-d4a1-4c97-8290-93c69d30e79b.jpg","articleContact":"","issueTime":"2017-06-07 11:31:06","browseNumber":521,"sort":"","isCarousel":"","adminId":"","unitName":"","adminName":""},{"id":55,"articletypeId":"","unitId":"","articleTitle":"对方公习近平会见俄罗斯总统普京：全面深化上海合作组织各领域合作","articleImg":"static/upload/00a53dce-d4a1-4c97-8290-93c69d30e79b.jpg","articleContact":"","issueTime":"2017-06-02 16:07:10","browseNumber":124,"sort":"","isCarousel":"","adminId":"","unitName":"","adminName":""},{"id":54,"articletypeId":"","unitId":"","articleTitle":"习近平会见俄罗斯总统普京：全面深化上海合作组织各领域合作","articleImg":"static/upload/00a53dce-d4a1-4c97-8290-93c69d30e79b.jpg","articleContact":"","issueTime":"2017-06-02 16:07:02","browseNumber":124,"sort":"","isCarousel":"","adminId":"","unitName":"","adminName":""},{"id":53,"articletypeId":"","unitId":"","articleTitle":"习近平会见俄罗斯总统普京：全面深化上海合作组织各领域合作","articleImg":"static/upload/00a53dce-d4a1-4c97-8290-93c69d30e79b.jpg","articleContact":"","issueTime":"2017-06-02 16:06:44","browseNumber":521,"sort":"","isCarousel":"","adminId":"","unitName":"","adminName":""},{"id":52,"articletypeId":"","unitId":"","articleTitle":"习近平会见俄罗斯总统普京：全面深化上海合作组织各领域合作","articleImg":"static/upload/00a53dce-d4a1-4c97-8290-93c69d30e79b.jpg","articleContact":"","issueTime":"2017-06-02 16:06:30","browseNumber":45,"sort":"","isCarousel":"","adminId":"","unitName":"","adminName":""},{"id":51,"articletypeId":"","unitId":"","articleTitle":"习近平会见俄罗斯总统普京：全面深化上海合作组织各领域合作","articleImg":"static/upload/00a53dce-d4a1-4c97-8290-93c69d30e79b.jpg","articleContact":"","issueTime":"2017-06-02 16:06:23","browseNumber":213,"sort":"","isCarousel":"","adminId":"","unitName":"","adminName":""}]
     * pageNo : 1
     * totalPage : 2
     */

    private String msg;
    private int total;
    private int code;
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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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

    public static class DataBean {
        /**
         * id : 63
         * articletypeId :
         * unitId :
         * articleTitle : 习近平签署命令，授予驻港部队某旅特战一连荣誉称号
         * articleImg : static/upload/a13818f2-b546-41ea-b739-e5c2c542fa61.png
         * articleContact :
         * issueTime : 2017-06-14 15:30:53
         * browseNumber : 3
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
        private int browseNumber;
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

        public int getBrowseNumber() {
            return browseNumber;
        }

        public void setBrowseNumber(int browseNumber) {
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
