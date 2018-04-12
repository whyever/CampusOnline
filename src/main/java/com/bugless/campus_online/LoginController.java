package com.bugless.campus_online;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginController {
    @Autowired
    private LoginRepository loginRepo;

    public LoginController(LoginRepository loginRepo) {
        this.loginRepo = loginRepo;
    }

    public String login() {
        return "login";
    }
}
