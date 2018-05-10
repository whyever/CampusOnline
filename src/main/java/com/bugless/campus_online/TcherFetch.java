package com.bugless.campus_online;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class TcherFetch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private String tcherID;
    private String Time;
    //0 / 1 对应flag
    //默认老师能增加可预约（0） 删除某预约（1/0） 更改未被续约(0) 查询本人(0/1)
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
