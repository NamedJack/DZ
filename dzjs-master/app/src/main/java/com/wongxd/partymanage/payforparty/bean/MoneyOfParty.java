package com.wongxd.partymanage.payforparty.bean;

import java.io.Serializable;

/**
 * Created by zyj on 2017/7/22.
 */

public class MoneyOfParty implements Serializable {

    /**
     * id : 101
     * number : BH00002
     * name : bbb
     * payable : 11.5
     * paid : 11.5
     * payState : 2
     * time : 2017-06
     * payTime : 2017-06-03
     * state : 3
     * unitname : 第一党支部
     * unitid : 1
     * address : xx
     * timeid : null
     */

    private int id;
    private String number;
    private String name;
    private String payable;
    private String paid;
    private int payState;
    private String time;
    private String payTime;
    private int state;
    private String unitname;
    private int unitid;
    private String address;
    private String timeid;

    public MoneyOfParty(String payable, String paid, int state, String payTime, String time, String address) {
        this.payable = payable;
        this.paid = paid;
        this.state = state;
        this.time = time;
        this.address = address;
        this.payTime = payTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPayable() {
        return payable;
    }

    public void setPayable(String payable) {
        this.payable = payable;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public int getPayState() {
        return payState;
    }

    public void setPayState(int payState) {
        this.payState = payState;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getUnitname() {
        return unitname;
    }

    public void setUnitname(String unitname) {
        this.unitname = unitname;
    }

    public int getUnitid() {
        return unitid;
    }

    public void setUnitid(int unitid) {
        this.unitid = unitid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTimeid() {
        return timeid;
    }

    public void setTimeid(String timeid) {
        this.timeid = timeid;
    }
}
