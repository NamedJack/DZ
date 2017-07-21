package com.wongxd.partymanage.peoplestalk.bean;

import java.io.Serializable;

/**
 * Created by zyj on 2017/7/19.
 */

public class PeopleTalk implements Serializable {
    private String tlakPersion;
    private String persionPoltics;
    private String advicePersion;
    private String selfPersion;
//    private String tlakYears;
    private String tlakTime;

    public PeopleTalk(String tlakPersion, String persionPoltics, String advicePersion, String selfPersion, String tlakTime) {
        this.tlakPersion = tlakPersion;
        this.persionPoltics = persionPoltics;
        this.advicePersion = advicePersion;
        this.selfPersion = selfPersion;
        this.tlakTime = tlakTime;
//        this.tlakDay = tlakDay;
    }

    public String getTlakPersion() {
        return tlakPersion;
    }

    public void setTlakPersion(String tlakPersion) {
        this.tlakPersion = tlakPersion;
    }

    public String getPersionPoltics() {
        return persionPoltics;
    }

    public void setPersionPoltics(String persionPoltics) {
        this.persionPoltics = persionPoltics;
    }

    public String getAdvicePersion() {
        return advicePersion;
    }

    public void setAdvicePersion(String advicePersion) {
        this.advicePersion = advicePersion;
    }

    public String getSelfPersion() {
        return selfPersion;
    }

    public void setSelfPersion(String selfPersion) {
        this.selfPersion = selfPersion;
    }

    public String getTlakTime() {
        return tlakTime;
    }

    public void setTlakTime(String tlakYears) {
        this.tlakTime = tlakYears;
    }


    @Override
    public String toString() {
        return "PeopleTalk{" +
                "tlakPersion='" + tlakPersion + '\'' +
                ", persionPoltics='" + persionPoltics + '\'' +
                ", advicePersion='" + advicePersion + '\'' +
                ", selfPersion='" + selfPersion + '\'' +
                ", tlakTime='" + tlakTime + '\'' +
                '}';
    }
}
