package com.bugless.campus_online;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class StuFetch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    //可预约老师
    private String availableTcherID;
    //可预约时间
    private String availableTime;
    //当前学生ID
    private String stuID;
    //当前学生已预约教师
    private String reserveTcherID;
    //当前学生已预约时间
    private String reserveTime;

    public String getAvailableTcherID() { return availableTcherID; }
    public void setAvailableTcherID(String availableTcherID) { this.availableTcherID = availableTcherID; }

    public String getAvailableTime() { return availableTime; }
    public void setAvailableTime(String availableTime) { this.availableTime = availableTime; }

    public String getStuID() { return stuID; }
    public void setStuID(String stuID) { this.stuID = stuID; }

    public String getReserveTcherID() { return reserveTcherID; }
    public void setReserveTcherID(String reserveTcherID) { this.reserveTcherID = reserveTcherID; }

    public String getReserveTime() { return reserveTime; }
    public void setReserveTime(String reserveTime) { this.reserveTime = reserveTime; }
}
