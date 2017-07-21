package com.wongxd.partymanage.partycontact.bean;

import java.io.Serializable;

/**
 * Created by zyj on 2017/7/20.
 */

public class ContactParty implements Serializable{
    private String person;
    private String time;
    private String help;
    private String action;

    public ContactParty(String person, String time, String help, String action) {

        this.person = person;
        this.time = time;
        this.help = help;
        this.action = action;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
