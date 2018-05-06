package com.bugless.campus_online;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.Entity;

@Entity
public class LoginFetch {

    private String ID;
    private String passwd;
    //老师为1,学生为0
    private int flag;

    public String getID() { return ID; }
    public void setID(String ID) { this.ID = ID; }

    public String getPasswd() { return passwd; }
    public void setPasswd(String passwd) { this.passwd = passwd; }

    public int getFlag() { return flag; }
    public void setFlag(int flag) { this.flag = flag; }
}
