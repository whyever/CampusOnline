package com.bugless.campus_online;

import javax.persistence.*;

@Entity
@Table(name = "tcher_login")
public class TcherLogin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    //ID在提交到数据库前要做检查,数据必须为9位数字
    @Column(name = "tcher_id", nullable = false)
    private String tcherID;

    //密码提交前必须使用SHA256处理,得16进制64位字符串
    @Column(name = "tcher_passwd", nullable = false)
    private String tcherPasswd;

    public String getTcherID() { return tcherID; }
    public void setTcherID(String tcherID) { this.tcherID = tcherID; }

    public String getTcherPasswd() { return tcherPasswd; }
    public void setTcherPasswd(String tcherPasswd) { this.tcherPasswd = tcherPasswd; }
}
