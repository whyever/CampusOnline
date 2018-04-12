package com.bugless.campus_online;

import sun.security.jgss.wrapper.GSSNameElement;

import javax.persistence.*;

@Entity
@Table(name = "login")
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    //ID在提交到数据库前要做检查,数据必须为9位数字
    @Column(name = "id", nullable = false)
    private String ID;

    //用于区分老师与学生ID的标识,1为老师,0为学生
    @Column(name = "flag", nullable = false)
    private int flag;

    //密码提交前必须使用SHA256处理,得16进制64位字符串
    @Column(name = "password", nullable = false)
    private String password;

    public String getID() {
        return ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }

    public int getFlag() {
        return flag;
    }
    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
