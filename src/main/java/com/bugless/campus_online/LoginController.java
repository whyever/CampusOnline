package com.bugless.campus_online;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private StuLoginRepository stuLoginRepo;
    private TcherLoginRepository tcherLoginRepo;

    public LoginController(StuLoginRepository stuLoginRepo, TcherLoginRepository tcherLoginRepo) {
        this.stuLoginRepo = stuLoginRepo;
        this.tcherLoginRepo = tcherLoginRepo;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String loginGET(Model model) {
        System.out.println("GET");
        return "login";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String loginPOST(LoginFetch loginFetch) {
        String ID = loginFetch.getID();
        System.out.println(ID);
        String passwd = loginFetch.getPasswd();
        System.out.println(passwd);
        int flag = loginFetch.getFlag();
        System.out.println(flag);

        if (flag == 1) {
            TcherLogin tcherLogin = tcherLoginRepo.findByTcherID(ID);
            if (passwd.equals(tcherLogin.getTcherPasswd())) {
                return "redirect:/teacher";
            }
        }
        if (flag == 0) {
            System.out.println("1");
            StuLogin stuLogin = stuLoginRepo.findByStuID(ID);
            System.out.println("2");

            if (passwd.equals(stuLogin.getStuPasswd())) {
                System.out.println("3");
                return "redirect:/student";
            }
        }
        System.out.println("4");
        return "redirect:/login";
    }
}
