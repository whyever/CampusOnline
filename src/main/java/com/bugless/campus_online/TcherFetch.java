package com.bugless.campus_online;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class TcherFetch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private String tcherID;
    private String Time;
    private int flag;
    private String stuID;

    public String getStuID() {
        return stuID;
    }

    public void setStuID(String stuID) {
        this.stuID = stuID;
    }



    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }


    public String getTcherID() {
        return tcherID;
    }

    public void setTcherID(String tcherID) {
        this.tcherID = tcherID;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }



}
