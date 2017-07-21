package com.wongxd.partymanage.party.bean;

import java.util.List;

/**
 * Created by wxd1 on 2017/6/20.
 */

public class PartyBean {

    /**
     * msg : 上传成功
     * code : 100
     * data : [{"id":1,"name":"常用应用","url":"#","pid":0,"icon":"#","isshow":1,"pwmenuList":[{"id":5,"name":"三会一课","url":"/admin/aaaa","pid":1,"icon":"/img/icon/ThreeCourse.png","isshow":1,"pwmenuList":[]},{"id":6,"name":"党员承诺","url":"/admin/bbbb","pid":1,"icon":"/img/icon/PartyPromise.png","isshow":1,"pwmenuList":[]},{"id":7,"name":"学习心得","url":"/admin/cccc","pid":1,"icon":"/img/icon/StudyNote.png","isshow":1,"pwmenuList":[]},{"id":8,"name":"群众联系表","url":"/admin/bbbb","pid":1,"icon":"/img/icon/MassContactList.png","isshow":1,"pwmenuList":[]},{"id":9,"name":"民主谈心","url":"/admin/eeee","pid":1,"icon":"/img/icon/DemocraticTalk.png","isshow":1,"pwmenuList":[]},{"id":10,"name":"党费","url":"/admin/ffff","pid":1,"icon":"/img/icon/PartyCost.png","isshow":1,"pwmenuList":[]},{"id":11,"name":"投票","url":"/admin/gggg","pid":1,"icon":"/img/icon/Vote.png","isshow":1,"pwmenuList":[]},{"id":12,"name":"交流互动","url":"/admin/hhhh","pid":1,"icon":"/img/icon/Interaction.png","isshow":1,"pwmenuList":[]},{"id":13,"name":"党员风采","url":"/admin/iiii","pid":1,"icon":"/img/icon/PartyMemberStyle.png","isshow":1,"pwmenuList":[]},{"id":14,"name":"创先争优","url":"/admin/jjjj","pid":1,"icon":"/img/icon/innovateGood.png","isshow":1,"pwmenuList":[]}]},{"id":2,"name":"党支部","url":"#","pid":0,"icon":"#","isshow":1,"pwmenuList":[{"id":15,"name":"组织架构","url":"/admin/kkkk","pid":2,"icon":"/img/icon/organization.png","isshow":1,"pwmenuList":[]},{"id":16,"name":"支部手册","url":"/admin/llll","pid":2,"icon":"/img/icon/innovateGood.png","isshow":1,"pwmenuList":[]},{"id":17,"name":"文件制度","url":"/admin/mmmm","pid":2,"icon":"/img/icon/DocumentSystem.png","isshow":1,"pwmenuList":[]},{"id":18,"name":"发布会议","url":"/admin/oooo","pid":2,"icon":"/img/icon/ReleaseConference.png","isshow":1,"pwmenuList":[]}]},{"id":3,"name":"学习乐园","url":"#","pid":0,"icon":"#","isshow":1,"pwmenuList":[{"id":19,"name":"专题教育","url":"/admin/nnnn","pid":3,"icon":"/img/icon/ThematicEducation.png","isshow":1,"pwmenuList":[]},{"id":20,"name":"学习资料","url":"/admin/jhkk","pid":3,"icon":"/img/icon/LearningMaterials.png","isshow":1,"pwmenuList":[]}]},{"id":4,"name":"组织建设","url":"#","pid":0,"icon":"#","isshow":1,"pwmenuList":[{"id":21,"name":"组织架构","url":"/admin/skdka","pid":4,"icon":"/img/icon/organizational.png","isshow":1,"pwmenuList":[]},{"id":22,"name":"组织关系","url":"/admin/asdsa","pid":4,"icon":"/img/icon/OrganizationalRelationship.png","isshow":1,"pwmenuList":[]},{"id":23,"name":"党员发展","url":"/admin/adlj","pid":4,"icon":"/img/icon/PartyMemberDevelopment.png","isshow":1,"pwmenuList":[]}]}]
     */

    private String msg;
    private String code;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * name : 常用应用
         * url : #
         * pid : 0
         * icon : #
         * isshow : 1
         * pwmenuList : [{"id":5,"name":"三会一课","url":"/admin/aaaa","pid":1,"icon":"/img/icon/ThreeCourse.png","isshow":1,"pwmenuList":[]},{"id":6,"name":"党员承诺","url":"/admin/bbbb","pid":1,"icon":"/img/icon/PartyPromise.png","isshow":1,"pwmenuList":[]},{"id":7,"name":"学习心得","url":"/admin/cccc","pid":1,"icon":"/img/icon/StudyNote.png","isshow":1,"pwmenuList":[]},{"id":8,"name":"群众联系表","url":"/admin/bbbb","pid":1,"icon":"/img/icon/MassContactList.png","isshow":1,"pwmenuList":[]},{"id":9,"name":"民主谈心","url":"/admin/eeee","pid":1,"icon":"/img/icon/DemocraticTalk.png","isshow":1,"pwmenuList":[]},{"id":10,"name":"党费","url":"/admin/ffff","pid":1,"icon":"/img/icon/PartyCost.png","isshow":1,"pwmenuList":[]},{"id":11,"name":"投票","url":"/admin/gggg","pid":1,"icon":"/img/icon/Vote.png","isshow":1,"pwmenuList":[]},{"id":12,"name":"交流互动","url":"/admin/hhhh","pid":1,"icon":"/img/icon/Interaction.png","isshow":1,"pwmenuList":[]},{"id":13,"name":"党员风采","url":"/admin/iiii","pid":1,"icon":"/img/icon/PartyMemberStyle.png","isshow":1,"pwmenuList":[]},{"id":14,"name":"创先争优","url":"/admin/jjjj","pid":1,"icon":"/img/icon/innovateGood.png","isshow":1,"pwmenuList":[]}]
         */

        private int id;
        private String name;
        private String url;
        private int pid;
        private String icon;
        private int isshow;
        private List<PwmenuListBean> pwmenuList;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getIsshow() {
            return isshow;
        }

        public void setIsshow(int isshow) {
            this.isshow = isshow;
        }

        public List<PwmenuListBean> getPwmenuList() {
            return pwmenuList;
        }

        public void setPwmenuList(List<PwmenuListBean> pwmenuList) {
            this.pwmenuList = pwmenuList;
        }

        public static class PwmenuListBean {
            /**
             * id : 5
             * name : 三会一课
             * url : /admin/aaaa
             * pid : 1
             * icon : /img/icon/ThreeCourse.png
             * isshow : 1
             * pwmenuList : []
             */

            private int id;
            private String name;
            private String url;
            private int pid;
            private String icon;
            private int isshow;
            private List<?> pwmenuList;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getPid() {
                return pid;
            }

            public void setPid(int pid) {
                this.pid = pid;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public int getIsshow() {
                return isshow;
            }

            public void setIsshow(int isshow) {
                this.isshow = isshow;
            }

            public List<?> getPwmenuList() {
                return pwmenuList;
            }

            public void setPwmenuList(List<?> pwmenuList) {
                this.pwmenuList = pwmenuList;
            }
        }
    }
}
