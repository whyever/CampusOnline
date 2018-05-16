package com.bugless.campus_online;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.lang.*;

@Controller
@RequestMapping("/student")
public class StuController {

    //用于存储从用户id
    String current_id;

    @Autowired
    private ReserveRepository reserveRepository;
    private StuLoginRepository stuLoginRepository;

    public StuController(ReserveRepository reserveRepo, StuLoginRepository stuLoginRepository) {
        this.reserveRepository = reserveRepo;
        this.stuLoginRepository = stuLoginRepository;
    }

    //获取当前可预约项
    @RequestMapping(method = RequestMethod.GET)
    public String getReserve(Model model, HttpSession httpSession) {
        current_id=httpSession.getAttribute("id").toString();   //接收从LoginController传过来的id,使用session的方法
        System.out.println("StuController : "+current_id);
        //处理可预约项
        List<Reservation> availableReserve = reserveRepository.findByResrvFlag(0);    //reserv_flag为0代表可以预约
        System.out.println("StuController : FindAvailableReserveDone");

        for(Reservation reservation : availableReserve) {
            String timeTemp=reservation.getResrvTime();
            String tcherId=reservation.getTcherID();
            reservation.setResrvTime("老师:"+tcherId+" "+"时间:"+timeTemp);
        }

        model.addAttribute("stuReserves",availableReserve);
        System.out.println("StuController : FinishedAvailableReserve");

        //处理已有预约
        List<Reservation> currentReserve = reserveRepository.findByStuID(current_id);
        System.out.println("StuController : FindCurrentReserveDone");
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
    public String submitReserve(HttpServletRequest request, HttpSession httpSession) {
        current_id = httpSession.getAttribute("id").toString();
        System.out.println("StuController-submit : "+current_id+" length : "+current_id.length());
        String submitString = request.getParameter("submitReserve");
        String availaTcherID = submitString.substring(3,12);
        System.out.println("StuController-submit-tcherid : "+availaTcherID+" length : "+availaTcherID.length());
        String availaTime = submitString.substring(16,submitString.length());
        System.out.println("StuController-submit-time : "+availaTime+" length : "+availaTime.length());
        //提交预约即在已有表项中写入学生学号及预约标记
        reserveRepository.submitReserve1(current_id,availaTcherID,availaTime);
        System.out.println("StuController : Insert current_id");
        reserveRepository.submitReserve2(availaTcherID,availaTime);
        System.out.println("StuController : Insert resrv_flag");

        System.out.println("StuController : Submitted the reservation");
        return "redirect:/student";
    }

    //删除当前预约
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteReserve(HttpServletRequest request, HttpSession httpSession) {
        current_id = httpSession.getAttribute("id").toString();
        System.out.println("StuController-delete : "+current_id+" length : "+current_id.length());
        String delTime = request.getParameter("deleteReserve");
        System.out.println("StuController-delete : "+delTime+" length : "+delTime.length());
        reserveRepository.deleteReserve(null, delTime);
        System.out.println("StuController-delete : Deleted the reservation");

        return "redirect:/student";
    }

    //修改密码
    @RequestMapping(value = "/changePass", method = RequestMethod.POST)
    public String changePassword(HttpServletRequest request, HttpSession httpSession) {
        current_id = httpSession.getAttribute("id").toString();
        System.out.println("StuController-changePass : "+current_id);
        String newPass = request.getParameter("newPassword");
        System.out.println("StuController-changePass : "+newPass);
        stuLoginRepository.changStuPassword(newPass, current_id);

        System.out.println("StuController-changePass : Change password finished");
        return "redirect:/student";
    }

    //退出登陆
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession httpSession) {
        httpSession.removeAttribute("id");

        return "redirect:/login";
    }
}
