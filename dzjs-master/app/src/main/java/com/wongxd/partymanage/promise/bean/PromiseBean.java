package com.wongxd.partymanage.promise.bean;

import java.io.Serializable;

/**
 * Created by json on 2017/7/11.
 */

public class PromiseBean implements Serializable{
    private String name;
    private String department;
    private String partyStudy;

    public String getName() {
        return name;
    }

    public PromiseBean(String name, String department, String partyStudy) {
        this.name = name;
        this.department = department;
        this.partyStudy = partyStudy;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPartyStudy() {
        return partyStudy;
    }

    public void setPartyStudy(String partyStudy) {
        this.partyStudy = partyStudy;
    }
}
