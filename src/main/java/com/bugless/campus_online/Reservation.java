package com.bugless.campus_online;

import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "id" , nullable=false)
    private int id;

    //教师ID
    @Column(name = "tcher_id" ,nullable = false)
    private String tcherID;

    //预约时间,格式为2018.05.07.1
    @Column(name = "resrv_time",nullable = false)
    private String resrvTime;

    //此时间被预约则为1,未被预约则为0
    @Column(name = "resrv_flag",nullable = false)
    private int resrvFlag;

    //学生ID
    @Column(name = "stu_id")
    private String stuID;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTcherID() { return tcherID; }
    public void setTcherID(String tcherID) { this.tcherID = tcherID; }

    public String getResrvTime() { return resrvTime; }
    public void setResrvTime(String resrvTime) { this.resrvTime = resrvTime; }

    public int getResrvFlag() { return resrvFlag; }
    public void setResrvFlag(int resrvFlag) { this.resrvFlag = resrvFlag; }

    public String getStuID() { return stuID; }
    public void setStuID(String stuID) { this.stuID = stuID; }
}
