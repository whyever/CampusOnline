package com.bugless.campus_online;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Iterator;
import java.util.List;
import java.lang.*;
import java.util.ListIterator;

@Controller
@RequestMapping("/student")
public class StuController {

    //用于存储从LoginController传过来的用户id
    String current_id;

    @Autowired
    private ReserveRepository reserveRepository;

    public StuController(ReserveRepository reserveRepo) {
        this.reserveRepository = reserveRepo;
    }

    //预约时间中的日期处理
    public String timeHandle(String time) {
        String timeStr = time.substring(time.length()-1,time.length());
        String date = time.substring(0,time.length()-2);
        int num = 1;
        try {
            num = Integer.parseInt(timeStr);
        }catch(NumberFormatException e) {
            e.printStackTrace();
        }
        switch(num) {
            case 1: { date += " " + "8:00-9:45"; break; }
            case 2: { date += " " + "10:05-11:50"; break; }
            case 3: { date += " " + "14:00-15:45"; break; }
            case 4: { date += " " + "16:05-17:50"; break; }
            case 5: { date += " " + "19:00-21:00"; break; }
        }
        System.out.println("timeHandle : "+date);
        return date;
    }

    //获取当前可预约项
    @RequestMapping(method = RequestMethod.GET)
    public String getReserve(@ModelAttribute("id")String ID, Model model) {
        current_id = ID;    //接收从LoginController传过来的id
        System.out.println("StuController : "+current_id);

        //处理可预约项
        List<Reservation> availableReserve = reserveRepository.findByResrvFlag(0);    //reserv_flag为0代表可以预约
        System.out.println("StuController : FindAvailableReserveDone");
        for(Reservation reservation : availableReserve) {
            String timeTemp=reservation.getResrvTime();
            timeTemp=timeHandle(timeTemp);
            reservation.setResrvTime(timeTemp);
        }
        model.addAttribute("stuReserves",availableReserve);
        System.out.println("StuController : FinishedAvailableReserve");

        //处理已有预约
        List<Reservation> currentReserve = reserveRepository.findByStuID(ID);
        System.out.println("StuController : FindCurrentReserveDone");
        for(Reservation reservation : currentReserve) {
            String timeTemp=reservation.getResrvTime();
            timeTemp=timeHandle(timeTemp);
            reservation.setResrvTime(timeTemp);
        }
        model.addAttribute("currentReserves", currentReserve);
        System.out.println("StuController : FinishedCurrentReserve");

        System.out.println("StuController : Finished GET");
        return "student";
    }

    public String getCurrent_id() {
        return current_id;
    }

    //提交预约
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String submitReserve(StuFetch stuFetch) {
        String availaTcherID = stuFetch.getAvailableTcherID();
        System.out.println("StuController-submit-tcherid : "+availaTcherID);
        String availaTime = stuFetch.getAvailableTime();
        System.out.println("StuController-submit-tcherid : "+availaTime);
        //提交预约即在已有表项中写入学生学号及预约标记
        reserveRepository.submitReserve(current_id,1,availaTcherID,availaTime);
        System.out.println("StuController : Submitted the reservation");

        return "student";
    }

    //编辑当前预约
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editReserve(StuFetch stuFetch) {
        String reserveTime = stuFetch.getReserveTime();
        System.out.println("StuController-edit-reserveTime : "+reserveTime);
        String reserveTcher = stuFetch.getReserveTcherID();
        System.out.println("StuController-edit-reserveTcher : "+reserveTcher);
        reserveRepository.editReserve(reserveTcher,reserveTime,current_id);
        System.out.println("StuController : Modified the reservation");

        return "student";
    }

    //删除当前预约
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteReserve() {
        reserveRepository.deleteReserve(current_id);
        System.out.println("StuController : Deleted the reservation");

        return "student";
    }
}
