package com.bugless.campus_online;

//import com.sun.org.apache.xpath.internal.operations.String;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.lang.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TcherController extends HttpServlet {

    @Autowired
    private ReserveRepository reserveRepository;
    //教师可看的预约属性
    private String tcherID;
    private String Time;
    private String passwd;
    public TcherController(ReserveRepository reserveRepo)
    {
        this.reserveRepository=reserveRepo;
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String add(HttpServletRequest request,Model model)
    {
        System.out.println("add method "+tcherID);
        Reservation reservation=new Reservation();
        String year=request.getParameter("year");
        String month=request.getParameter("month");
        String date=request.getParameter("date");
        String classes=request.getParameter("classes");
        Time=year+"."+month+"."+date+"."+classes;
        System.out.print(Time);
        reservation.setId(1000);
        reservation.setTcherID(tcherID);
        reservation.setResrvTime(Time);
        reservation.setResrvFlag(0);
        reservation.setStuID(null);
        reservation.setTag(1);
        reserveRepository.save(reservation);
        /*HttpSession session= request.getSession();
        tcherID=session.getAttribute("id").toString();
        List<Reservation> reservations=reserveRepository.findByTcherID(tcherID);
        model.addAttribute("reserveList",reservations);
        */
        return "redirect:/teacher";
    }

    //教师进入后直接显示预约情况
    @RequestMapping(method = RequestMethod.GET)
    public String select(Model model, HttpServletRequest request){
        HttpSession session= request.getSession();
        tcherID=session.getAttribute("id").toString();
        List<Reservation> reservations=reserveRepository.findByTcherID(tcherID);
        model.addAttribute("reserveList",reservations);

        return "teacher";
    }
    //修改此项为不可预约
    @RequestMapping(value="/update" , method=RequestMethod.POST)
    public String update(TcherFetch tcherFetch)
    {
        tcherID=tcherFetch.getTcherID();
        Time=tcherFetch.getTime();

        reserveRepository.updateReservation(tcherID,Time);
        return "teacher" ;

    }

    //首先测试这个吧
    @RequestMapping(value="/delete" ,method = RequestMethod.POST)
    public String delete(HttpServletRequest request, Model model)
    {
        HttpSession session=request.getSession();
        tcherID=session.getAttribute("id").toString();


        reserveRepository.delete(tcherID,Time);

        return "teacher";
    }

}
