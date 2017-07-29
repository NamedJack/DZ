package com.wongxd.partymanage.utils.conf;

/**
 * Created by wxd1 on 2017/5/15.
 */

public class UrlConf {

//    public static String HOST = "http://116.62.181.152:8555/";

    public static String HOST = "http://192.168.100.220:8080/";


    public static String LoginUrl = HOST + "userLogin";//登录

    public static String OutLoginUrl = HOST + "user/outlogin";//退出登录

    public static String HomeNewsUrl = HOST + "articlefront/frontList";//首页列表和搜索接口

    public static String HomeBannerUrl = HOST + "articlefront/carouselFigure";//首页轮播图

    public static String HomeNewsDetailUrl = HOST + "articlefront/getDetailed";//首页新闻详情


    public static String PartyWorkListUrl = HOST + "user/partyWorkList";//党务列表

    //三会一课
    public static String NewMeetingMsgUrl = HOST + "user/newMeetingmsg";//会议通知最新消息

    public static String HistoryMeetingMsgUrl = HOST + "user/oldMeetingmsg";//会议通知历史消息

    public static String MeetingDetailUrl = HOST + "user/meetingDetails";//会议通知详情

    public static String GetSignCodelUrl = HOST + "user/getSigncode";//资料员获取签到码

    public static String EndSignlUrl = HOST + "user/endsigning";//资料员结束签到

    public static String SignlUrl = HOST + "user/startsigning";//参会人员签到

    public static String MakingUpClassesUrl = HOST + "user/Makeupsigning";//开始补课

    public static String EndMakingUpClassesUrl = HOST + "user/endMakeupsigning";//结束补课

    /**
     * 心得列表
     */
    public static String StudyRecordListUrl = HOST + "user/experienceList";

    /**
     * 添加心得列表
     */
    public static String AddStudyRecordListUrl = HOST + "user/addexperience";

    /**
     * 心得详情
     */
    public static String StudyRecordDetailUrl = HOST + "user/experience";


    /**
     * 党费
     * :http://localhost:8080/user/costinfo
     */
    public static String PayForPartyUrl = HOST + "user/costlist";
    /**
     * 党员承诺
     */
    public static String PromiseOfParty = HOST + "user/promiselist";
    public static String PersonPromise = HOST + "user/subpromise";
    public static String AddPersonPromise = HOST + "user/promiseuserinfo";

    /**
     * 投票
     */
    public static String PartyVoteTicket = HOST + "votefront/listVote";
    public static String PartyVoteTicketTitle = HOST + "votefront/getOption";

    /**
     * 民主谈心
     */
    public static String PeopleTlak = HOST + "user/conversationList";
    public static String AddPeopleTlak = HOST + "user/subConversation";


}
