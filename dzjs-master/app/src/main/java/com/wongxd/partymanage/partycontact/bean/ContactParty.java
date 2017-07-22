package com.wongxd.partymanage.partycontact.bean;

import java.io.Serializable;

/**
 * Created by zyj on 2017/7/20.
 */

public class ContactParty implements Serializable{
    private String person;
    private String time;
    private String helpNotes;
    private String action;

    public ContactParty(String person, String time, String helpNotes, String action) {

        this.person = person;
        this.time = time;
        this.helpNotes = helpNotes;
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

    public String getHelpNotes() {
        return helpNotes;
    }

    public void setHelpNotes(String helpNotes) {
        this.helpNotes = helpNotes;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
