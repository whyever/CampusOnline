package com.bugless.campus_online;

import javax.persistence.*;

@Entity
@Table(name = "stu_login")
public class StuLogin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    //ID在提交到数据库前要做检查,数据必须为9位数字
    @Column(name = "stu_id", nullable = false)
    private String stuID;

    //学生姓名
    @Column(name = "stu_name", nullable = false)
    private String stuName;

    //密码提交前必须使用SHA256处理,得16进制64位字符串
    @Column(name = "stu_passwd", nullable = false)
    private String stuPasswd;

    public String getStuID() { return stuID; }
    public void setStuID(String stuID) { this.stuID = stuID; }

    public String getStuName() { return stuName; }
    public void setStuName(String stuName) { this.stuName = stuName; }

    public String getStuPasswd() { return stuPasswd; }
    public void setStuPasswd(String stuPasswd) { this.stuPasswd = stuPasswd; }
}
