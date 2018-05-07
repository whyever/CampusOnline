package com.bugless.campus_online;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LoginFetch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

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
