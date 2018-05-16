package com.bugless.campus_online;

//import com.sun.org.apache.xpath.internal.operations.String;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
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
    private TcherLoginRepository tcherLoginRepository;
    //教师可看的预约属性
    private String tcherID;
    private String Time;
    private String passwd;
    public TcherController(ReserveRepository reserveRepo,TcherLoginRepository tloginRepo)
    {

        this.reserveRepository=reserveRepo;
        this.tcherLoginRepository=tloginRepo;

    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String add(HttpServletRequest request,Model model)
    {
        System.out.println("teacher:"+tcherID+"add campus");
        Reservation reservation=new Reservation();
        String year=request.getParameter("year");
        String month=request.getParameter("month");
        String date=request.getParameter("date");
        String classes=request.getParameter("classes");
        Time=year+"-"+month+"-"+date+"-"+classes;
        System.out.print(Time);
        reservation.setId(1000);
        reservation.setTcherID(tcherID);
        reservation.setResrvTime(Time);
        reservation.setResrvFlag(0);
        reservation.setStuID(null);
        reserveRepository.save(reservation);
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
    //修改密码
    @RequestMapping(value="/update" , method=RequestMethod.GET)
    public String update(HttpServletRequest request)
    {
        HttpSession session= request.getSession();
        tcherID=session.getAttribute("id").toString();
        passwd=request.getParameter("passwd");
        tcherLoginRepository.update(tcherID,passwd);
        return "redirect:/login" ;

    }
    //删除
    @RequestMapping(value="/delete/{id}" ,method = RequestMethod.GET)
    public String delete(HttpServletRequest request, Model model,@PathVariable(value = "id") String id)
    {
        HttpSession session=request.getSession();
        tcherID=session.getAttribute("id").toString();

        //Time=request.getParameter("resrvtime".toString());
        //int ID =Integer.parseInt(id);
        System.out.println(id);
        int ID=Integer.parseInt(id);
        System.out.println("delete message"+tcherID+"orderid:"+ID);
        reserveRepository.delete(ID);
        return "redirect:/teacher";
    }
    @RequestMapping(value = "/change",method = RequestMethod.POST)
    public String change(HttpServletRequest request,Model model)
    {
        HttpSession session=request.getSession();
        tcherID=session.getAttribute("id").toString();
        String newpasswd="new passwd";
        tcherLoginRepository.update(tcherID,newpasswd);
        return "redirect:/teacher";
    }

}
