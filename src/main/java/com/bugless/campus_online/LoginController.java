package com.bugless.campus_online;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        System.out.println("GET finished");
        return "login";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String loginPOST(LoginFetch loginFetch, RedirectAttributes redirectAttributes) {
        String ID = loginFetch.getID();
        System.out.println("Login : ID is " + ID);
        String passwd = loginFetch.getPasswd();
        System.out.println("Login : Password is " + passwd);
        int flag = loginFetch.getFlag();
        System.out.println("Login : Flag is " + flag);
        redirectAttributes.addFlashAttribute("id",ID);

        if (flag == 1) {
            System.out.println("Login: Teacher");
            TcherLogin tcherLogin = tcherLoginRepo.findByTcherID(ID);
            System.out.println("Login : Get teacher's ID");
            if (passwd.equals(tcherLogin.getTcherPasswd())) {
                System.out.println("Login : Goto teacher page");
                return "redirect:/teacher";
            }
        }
        if (flag == 0) {
            System.out.println("Login : Student");
            StuLogin stuLogin = stuLoginRepo.findByStuID(ID);
            System.out.println("Login : Get student's ID");
            if (passwd.equals(stuLogin.getStuPasswd())) {
                System.out.println("Login: Goto student page");
                return "redirect:/student";
            }
        }
        System.out.println("Login : return to login page");
        return "redirect:/login";
    }
}