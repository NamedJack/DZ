package com.wongxd.partymanage.home.bean;

import java.util.List;

/**
 * Created by wxd1 on 2017/6/20.
 */

public class NewsDeatilBean {

    /**
     * msg : 请求成功！
     * articleTitle : 习近平会见俄罗斯总统普京：全面深化上海合作组织各领域合作
     * code : 100
     * data : [{"id":5,"userId":2,"unitId":1,"typeId":4,"connId":43,"comment":"2323","title":"2121","time":"2017-06-21 13:21:12","img":"upload/experience/img1496479537169_img_0.jpeg;upload/experience/img1496479537207_img_1.jpeg;upload/experience/img1496479537238_img_2.jpeg;upload/experience/img1496479537297_img_3.jpeg;upload/experience/img1496479537325_img_4.jpeg;upload/experience/img1496479537343_img_5.jpeg;upload/experience/img1496479537374_img_6.jpeg;upload/experience/img1496479537391_img_7.jpeg;upload/experience/img1496479537422_img_8.jpeg","experienceTypeName":"","unitName":"","realName":""}]
     * url : articlefront/detailed?id=43&token=3fbbb51e-2ff6-44b1-a283-ded58fc95f58
     */

    private String msg;
    private String articleTitle;
    private String code;
    private String url;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 5
         * userId : 2
         * unitId : 1
         * typeId : 4
         * connId : 43
         * comment : 2323
         * title : 2121
         * time : 2017-06-21 13:21:12
         * img : upload/experience/img1496479537169_img_0.jpeg;upload/experience/img1496479537207_img_1.jpeg;upload/experience/img1496479537238_img_2.jpeg;upload/experience/img1496479537297_img_3.jpeg;upload/experience/img1496479537325_img_4.jpeg;upload/experience/img1496479537343_img_5.jpeg;upload/experience/img1496479537374_img_6.jpeg;upload/experience/img1496479537391_img_7.jpeg;upload/experience/img1496479537422_img_8.jpeg
         * experienceTypeName :
         * unitName :
         * realName :
         */

        private int id;
        private int userId;
        private int unitId;
        private int typeId;
        private int connId;
        private String comment;
        private String title;
        private String time;
        private String img;
        private String experienceTypeName;
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

        public int getUnitId() {
            return unitId;
        }

        public void setUnitId(int unitId) {
            this.unitId = unitId;
        }

        public int getTypeId() {
            return typeId;
        }

        public void setTypeId(int typeId) {
            this.typeId = typeId;
        }

        public int getConnId() {
            return connId;
        }

        public void setConnId(int connId) {
            this.connId = connId;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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

        public String getExperienceTypeName() {
            return experienceTypeName;
        }

        public void setExperienceTypeName(String experienceTypeName) {
            this.experienceTypeName = experienceTypeName;
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
