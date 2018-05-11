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
    //时间显示处理
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
            case 1: { date += " " + "08:00-09:45"; break; }
            case 2: { date += " " + "10:05-11:50"; break; }
            case 3: { date += " " + "14:00-15:45"; break; }
            case 4: { date += " " + "16:05-17:50"; break; }
            case 5: { date += " " + "19:00-21:00"; break; }
        }
        System.out.println("timeHandle : "+date);
        return date;
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
        //reservation.setTag(1);
        reserveRepository.save(reservation);
        return "redirect:/teacher";
    }

    //教师进入后直接显示预约情况
    @RequestMapping(method = RequestMethod.GET)
    public String select(Model model, HttpServletRequest request){
        HttpSession session= request.getSession();
        tcherID=session.getAttribute("id").toString();
        List<Reservation> reservations=reserveRepository.findByTcherID(tcherID);
        //处理时间
        for(Reservation reservation : reservations) {
            String timeTemp=reservation.getResrvTime();
            timeTemp=timeHandle(timeTemp);
            reservation.setResrvTime(timeTemp);
        }
        model.addAttribute("reserveList",reservations);

        return "teacher";
    }
    /*修改此项为不可预约
    @RequestMapping(value="/update" , method=RequestMethod.POST)
    public String update(TcherFetch tcherFetch)
    {
        tcherID=tcherFetch.getTcherID();
        Time=tcherFetch.getTime();

        reserveRepository.updateReservation(tcherID,Time);
        return "teacher" ;

    }*/

    @RequestMapping(value="/delete" ,method = RequestMethod.POST)
    public String delete(HttpServletRequest request, Model model)
    {
        HttpSession session=request.getSession();
        tcherID=session.getAttribute("id").toString();
        String tempTime=request.getParameter("resrvTime").toString();
        String[] splitTime=tempTime.split("//.");
        for(int i=0;i<4;i++)
        {
            System.out.println(splitTime[i]);
        }
        switch (splitTime[3])
        {
            case "08:00-09:45":{Time=splitTime[0]+"."+splitTime[1]+"."+splitTime[2]+"."+"1";break;}
            case "10:05-11:50":{Time=splitTime[0]+"."+splitTime[1]+"."+splitTime[2]+"."+"2";break;}
            case "14:00-15:45":{Time=splitTime[0]+"."+splitTime[1]+"."+splitTime[2]+"."+"3";break;}
            //case "08:00-09:45":{Time=splitTime[0]+"."+splitTime[1]+"."+splitTime[2]+"."+"1";break;}
            //case "08:00-09:45":{Time=splitTime[0]+"."+splitTime[1]+"."+splitTime[2]+"."+"1";break;}
        }

        reserveRepository.delete(tcherID,Time);

        return "teacher";
    }

}
