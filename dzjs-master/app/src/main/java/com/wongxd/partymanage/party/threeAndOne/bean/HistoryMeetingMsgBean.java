package com.wongxd.partymanage.party.threeAndOne.bean;

import java.util.List;

/**
 * Created by wxd1 on 2017/6/22.
 */

public class HistoryMeetingMsgBean {

    /**
     * code : 100
     * data : {"Meetinglist":[{"id":50,"name":"开党委会","noticeName":"开会学习","time":"2017-06-20 10:30:00","address":"四楼","content":"<p>党委是党支部的上级党组织。党的基层委员会由党员大会或代表大会选举产生，支部委员会由支部党员大会选举产生。  \r\n党支部委员会应该每月召开一次 ，基层党委会可以每月召开，也可2个月、每季度或半年召开一次。党委民主生活会每半年召开一次<\/p><p><br><\/p>","meetingType":2,"meetingRecord":"<p>一、党委书记要善于当\u201c班长\u201d。 党的委员会有一二十个人，像军队的一个班，书记好比是\u201c班长\u201d。要把这个班带好，的确不容易。目前各中央局、分局都领导很大的地区，担负很繁重的任务。领导工作不仅要决定方针政策，还要制定正确的工作方法，有了正确的方针政策，如果在工作方法上疏忽了，还是要发生问题。党委要完成自己的领导任务，就必须依靠党委这\u201c一班人\u201d，充分发挥他们的作用。书记要当好\u201c班长\u201d，就应该很好地学习和研究。书记、副书记如果不注意向自己的\u201c一班人\u201d作宣传工作和组织工作，不善于处理自己和委员之间的关系，不去研究怎样把会议开好，就很难把这\u201c一班人\u201d指挥好。如果这\u201c一班人\u201d动作不整齐，就休想带领千百万人去作战，去建设。当然，书记和委员之间的关系是少数服从多数，这同班长和战士之间的关系是不一样的。这里不过是一个比方。\n二、要把问题摆到桌面上来 。不仅\u201c班长\u201d要这样做，委员也要这样做。不要在背后议论。有了问题就开会，摆到桌面上来讨论，规定它几条，问题就解决了。有问题而不摆到桌面上来，就会长期不得解决，甚至一拖几年。\u201c班长\u201d和委员还要能互相谅解。书记和委员，中央和各中央局，各中央局和区党委之间的谅解、支援和友谊，比什么都重要。这一点过去大家不注意，七次代表大会以来，在这方面大有进步，友好团结关系大大增进了。今后仍然应该不断注意。\n三、\u201c互通情报\u201d。 就是说，党委各委员之间要把彼此知道的情况互相通知、互相交流。这对于取得共同的语言是很重要的。有些人不是这样做，而是像老子说的\u201c鸡犬之声相闻，老死不相往来\u201d[1]，结果彼此之间就缺乏共同的语言。我们有些高级干部，在马克思列宁主义的基本理论问题上也有不同的语言，原因是学习还不够。现在党内的语言比较一致了，但是，问题还没有完全解决。例如，在土地改革中，对什么是\u201c中农\u201d和什么是\u201c富农\u201d，就还有不同的了解。<\/p><p><br><\/p>","userId":5,"status":0,"code":"0","untilId":1,"meetingId":"","unitPid":2,"endtime":"2017-06-20 11:30:00","longtime":1,"creattime":"2017-06-20 10:24:06","state":2,"publishere":2,"publishereUser":4,"userName":"","meetingTypeName":""}],"page":{"pageNo":1,"pageSize":15,"pagetole":1}}
     */

    private int code;
    private DataBean data;

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
         * Meetinglist : [{"id":50,"name":"开党委会","noticeName":"开会学习","time":"2017-06-20 10:30:00","address":"四楼","content":"<p>党委是党支部的上级党组织。党的基层委员会由党员大会或代表大会选举产生，支部委员会由支部党员大会选举产生。  \r\n党支部委员会应该每月召开一次 ，基层党委会可以每月召开，也可2个月、每季度或半年召开一次。党委民主生活会每半年召开一次<\/p><p><br><\/p>","meetingType":2,"meetingRecord":"<p>一、党委书记要善于当\u201c班长\u201d。 党的委员会有一二十个人，像军队的一个班，书记好比是\u201c班长\u201d。要把这个班带好，的确不容易。目前各中央局、分局都领导很大的地区，担负很繁重的任务。领导工作不仅要决定方针政策，还要制定正确的工作方法，有了正确的方针政策，如果在工作方法上疏忽了，还是要发生问题。党委要完成自己的领导任务，就必须依靠党委这\u201c一班人\u201d，充分发挥他们的作用。书记要当好\u201c班长\u201d，就应该很好地学习和研究。书记、副书记如果不注意向自己的\u201c一班人\u201d作宣传工作和组织工作，不善于处理自己和委员之间的关系，不去研究怎样把会议开好，就很难把这\u201c一班人\u201d指挥好。如果这\u201c一班人\u201d动作不整齐，就休想带领千百万人去作战，去建设。当然，书记和委员之间的关系是少数服从多数，这同班长和战士之间的关系是不一样的。这里不过是一个比方。\n二、要把问题摆到桌面上来 。不仅\u201c班长\u201d要这样做，委员也要这样做。不要在背后议论。有了问题就开会，摆到桌面上来讨论，规定它几条，问题就解决了。有问题而不摆到桌面上来，就会长期不得解决，甚至一拖几年。\u201c班长\u201d和委员还要能互相谅解。书记和委员，中央和各中央局，各中央局和区党委之间的谅解、支援和友谊，比什么都重要。这一点过去大家不注意，七次代表大会以来，在这方面大有进步，友好团结关系大大增进了。今后仍然应该不断注意。\n三、\u201c互通情报\u201d。 就是说，党委各委员之间要把彼此知道的情况互相通知、互相交流。这对于取得共同的语言是很重要的。有些人不是这样做，而是像老子说的\u201c鸡犬之声相闻，老死不相往来\u201d[1]，结果彼此之间就缺乏共同的语言。我们有些高级干部，在马克思列宁主义的基本理论问题上也有不同的语言，原因是学习还不够。现在党内的语言比较一致了，但是，问题还没有完全解决。例如，在土地改革中，对什么是\u201c中农\u201d和什么是\u201c富农\u201d，就还有不同的了解。<\/p><p><br><\/p>","userId":5,"status":0,"code":"0","untilId":1,"meetingId":"","unitPid":2,"endtime":"2017-06-20 11:30:00","longtime":1,"creattime":"2017-06-20 10:24:06","state":2,"publishere":2,"publishereUser":4,"userName":"","meetingTypeName":""}]
         * page : {"pageNo":1,"pageSize":15,"pagetole":1}
         */

        private PageBean page;
        private List<MeetinglistBean> Meetinglist;

        public PageBean getPage() {
            return page;
        }

        public void setPage(PageBean page) {
            this.page = page;
        }

        public List<MeetinglistBean> getMeetinglist() {
            return Meetinglist;
        }

        public void setMeetinglist(List<MeetinglistBean> Meetinglist) {
            this.Meetinglist = Meetinglist;
        }

        public static class PageBean {
            /**
             * pageNo : 1
             * pageSize : 15
             * pagetole : 1
             */

            private int pageNo;
            private int pageSize;
            private int pagetole;

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

            public int getPagetole() {
                return pagetole;
            }

            public void setPagetole(int pagetole) {
                this.pagetole = pagetole;
            }
        }

        public static class MeetinglistBean {
            /**
             * id : 50
             * name : 开党委会
             * noticeName : 开会学习
             * time : 2017-06-20 10:30:00
             * address : 四楼
             * content : <p>党委是党支部的上级党组织。党的基层委员会由党员大会或代表大会选举产生，支部委员会由支部党员大会选举产生。
             党支部委员会应该每月召开一次 ，基层党委会可以每月召开，也可2个月、每季度或半年召开一次。党委民主生活会每半年召开一次</p><p><br></p>
             * meetingType : 2
             * meetingRecord : <p>一、党委书记要善于当“班长”。 党的委员会有一二十个人，像军队的一个班，书记好比是“班长”。要把这个班带好，的确不容易。目前各中央局、分局都领导很大的地区，担负很繁重的任务。领导工作不仅要决定方针政策，还要制定正确的工作方法，有了正确的方针政策，如果在工作方法上疏忽了，还是要发生问题。党委要完成自己的领导任务，就必须依靠党委这“一班人”，充分发挥他们的作用。书记要当好“班长”，就应该很好地学习和研究。书记、副书记如果不注意向自己的“一班人”作宣传工作和组织工作，不善于处理自己和委员之间的关系，不去研究怎样把会议开好，就很难把这“一班人”指挥好。如果这“一班人”动作不整齐，就休想带领千百万人去作战，去建设。当然，书记和委员之间的关系是少数服从多数，这同班长和战士之间的关系是不一样的。这里不过是一个比方。
             二、要把问题摆到桌面上来 。不仅“班长”要这样做，委员也要这样做。不要在背后议论。有了问题就开会，摆到桌面上来讨论，规定它几条，问题就解决了。有问题而不摆到桌面上来，就会长期不得解决，甚至一拖几年。“班长”和委员还要能互相谅解。书记和委员，中央和各中央局，各中央局和区党委之间的谅解、支援和友谊，比什么都重要。这一点过去大家不注意，七次代表大会以来，在这方面大有进步，友好团结关系大大增进了。今后仍然应该不断注意。
             三、“互通情报”。 就是说，党委各委员之间要把彼此知道的情况互相通知、互相交流。这对于取得共同的语言是很重要的。有些人不是这样做，而是像老子说的“鸡犬之声相闻，老死不相往来”[1]，结果彼此之间就缺乏共同的语言。我们有些高级干部，在马克思列宁主义的基本理论问题上也有不同的语言，原因是学习还不够。现在党内的语言比较一致了，但是，问题还没有完全解决。例如，在土地改革中，对什么是“中农”和什么是“富农”，就还有不同的了解。</p><p><br></p>
             * userId : 5
             * status : 0
             * code : 0
             * untilId : 1
             * meetingId :
             * unitPid : 2
             * endtime : 2017-06-20 11:30:00
             * longtime : 1
             * creattime : 2017-06-20 10:24:06
             * state : 2
             * publishere : 2
             * publishereUser : 4
             * userName :
             * meetingTypeName :
             */

            private int id;
            private String name;
            private String noticeName;
            private String time;
            private String address;
            private String content;
            private int meetingType;
            private String meetingRecord;
            private int userId;
            private int status;
            private String code;
            private int untilId;
            private String meetingId;
            private int unitPid;
            private String endtime;
            private int longtime;
            private String creattime;
            private int state;
            private int publishere;
            private int publishereUser;
            private String userName;
            private String meetingTypeName;
            private int isRead; // 是否已读 1：未读，2：已读

            public int getIsRead() {
                return isRead;
            }

            public void setIsRead(int isRead) {
                this.isRead = isRead;
            }
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

            public String getNoticeName() {
                return noticeName;
            }

            public void setNoticeName(String noticeName) {
                this.noticeName = noticeName;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getMeetingType() {
                return meetingType;
            }

            public void setMeetingType(int meetingType) {
                this.meetingType = meetingType;
            }

            public String getMeetingRecord() {
                return meetingRecord;
            }

            public void setMeetingRecord(String meetingRecord) {
                this.meetingRecord = meetingRecord;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public int getUntilId() {
                return untilId;
            }

            public void setUntilId(int untilId) {
                this.untilId = untilId;
            }

            public String getMeetingId() {
                return meetingId;
            }

            public void setMeetingId(String meetingId) {
                this.meetingId = meetingId;
            }

            public int getUnitPid() {
                return unitPid;
            }

            public void setUnitPid(int unitPid) {
                this.unitPid = unitPid;
            }

            public String getEndtime() {
                return endtime;
            }

            public void setEndtime(String endtime) {
                this.endtime = endtime;
            }

            public int getLongtime() {
                return longtime;
            }

            public void setLongtime(int longtime) {
                this.longtime = longtime;
            }

            public String getCreattime() {
                return creattime;
            }

            public void setCreattime(String creattime) {
                this.creattime = creattime;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public int getPublishere() {
                return publishere;
            }

            public void setPublishere(int publishere) {
                this.publishere = publishere;
            }

            public int getPublishereUser() {
                return publishereUser;
            }

            public void setPublishereUser(int publishereUser) {
                this.publishereUser = publishereUser;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getMeetingTypeName() {
                return meetingTypeName;
            }

            public void setMeetingTypeName(String meetingTypeName) {
                this.meetingTypeName = meetingTypeName;
            }
        }
    }
}
