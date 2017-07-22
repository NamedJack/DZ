package com.wongxd.partymanage.payforparty.bean;

import java.io.Serializable;

/**
 * Created by zyj on 2017/7/22.
 */

public class MoneyOfParty implements Serializable {
    private String totalMoney;
    private String paidMoney;
    private String month;
    private String day;
    private String address;
    private boolean havePaid;


    public MoneyOfParty(String totalMoney, String paidMoney, String month, String day, boolean havePaid, String address) {
        this.totalMoney = totalMoney;
        this.paidMoney = paidMoney;
        this.month = month;
        this.day = day;
        this.havePaid = havePaid;
        this.address = address;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getPaidMoney() {
        return paidMoney;
    }

    public void setPaidMoney(String paidMoney) {
        this.paidMoney = paidMoney;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public boolean isHavePaid() {
        return havePaid;
    }

    public void setHavePaid(boolean havePaid) {
        this.havePaid = havePaid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "MoneyOfParty{" +
                "totalMoney='" + totalMoney + '\'' +
                ", paidMoney='" + paidMoney + '\'' +
                ", month='" + month + '\'' +
                ", day='" + day + '\'' +
                ", havePaid=" + havePaid +
                '}';
    }
}
