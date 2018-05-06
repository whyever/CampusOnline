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

    private StuLoginRepository stuLoginRepo;
    private TcherLoginRepository tcherLoginRepo;

    @Autowired
    public LoginController(StuLoginRepository stuLoginRepo, TcherLoginRepository tcherLoginRepo) {
        this.stuLoginRepo = stuLoginRepo;
        this.tcherLoginRepo = tcherLoginRepo;
    }

    /*
    @RequestMapping(method = RequestMethod.GET)
    public String loginGET(Model model) {

        return "login";
    }
    */

    @RequestMapping(method = RequestMethod.POST)
    public String loginPOST(LoginFetch loginFetch) {
        String ID = loginFetch.getID();
        String passwd = loginFetch.getPasswd();
        int flag = loginFetch.getFlag();

        if (flag == 1) {
            TcherLogin tcherLogin = tcherLoginRepo.findByTcherID(ID);
            if (passwd.equals(tcherLogin.getTcherPasswd())) {
                return "redirect:/teacher";
            }
        }
        if (flag == 0) {
            StuLogin stuLogin = stuLoginRepo.findByStuID(ID);
            if (passwd.equals(stuLogin.getStuPasswd())) {
                return "redirect:/student";
            }
        }
        return "redirect:/login";
    }
}
