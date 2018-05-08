package com.bugless.campus_online;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.lang.*;

@Controller
@RequestMapping("/student")
public class StuController {

    @Autowired
    private ReserveRepository reserveRepository;
    //可预约老师
    private String availaTcherID;
    //可预约时间
    private String availaTime;
    //当前学生ID
    private String stuID;
    //当前学生已预约教师
    private String reserveTcherID;
    //当前学生已预约时间
    private String reserveTime;

    public StuController(ReserveRepository reserveRepo) {
        this.reserveRepository = reserveRepo;
    }

    //获取当前可预约教师
    @RequestMapping(method = RequestMethod.GET)
    public String stuGET(StuFetch stuFetch, Model model) {
        //availaTcherID = stuFetch.getAvailableTcherID();
        //availaTime = stuFetch.getAvailableTime();
        //Reservation stuReserve = reserveRepository.findByTcherIDAndAndResrvTime(availaTcherID,availaTime);
        List<Reservation> stuReserve = reserveRepository.findDistinctByTag(1);
        System.out.println("GET");
        model.addAttribute("stuReserves",stuReserve);

        return "student";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String stuPOST(Model model) {
        return "student";
    }
}
