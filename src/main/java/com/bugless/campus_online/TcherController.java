package com.bugless.campus_online;

//import com.sun.org.apache.xpath.internal.operations.String;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.validation.Valid;
import java.lang.*;
import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TcherController {

    @Autowired
    private ReserveRepository reserveRepository;
    //教师可看的预约属性
    private String tcherID;
    private String Time;
    public TcherController(ReserveRepository reserveRepo)
    {
        this.reserveRepository=reserveRepo;
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String add(TcherFetch tcherFetch)
    {
        Reservation reservation=new Reservation();
        //获取前端数据,每个页面增加fetch
        tcherID=tcherFetch.getTcherID();
        Time=tcherFetch.getTime();
        reservation.setTcherID(tcherID);
        reservation.setResrvTime(Time);
        reservation.setResrvFlag(0);
        reservation.setStuID(null);
        reserveRepository.save(reservation);
        return("add");
    }
    @RequestMapping(value = "/select" , method = RequestMethod.POST)
    public String select(TcherFetch tcherFetch){
        tcherID=tcherFetch.getTcherID();
        List<Reservation> reservations=reserveRepository.findByTcherID(tcherID);
        return("select");
    }
    //修改此项为不可预约
    @RequestMapping(value="/update" , method=RequestMethod.POST)
    public String update(TcherFetch tcherFetch)
    {
        tcherID=tcherFetch.getTcherID();
        Time=tcherFetch.getTime();
        reserveRepository.updateReservation(tcherID,Time);
        return("update");

    }

    //首先测试这个吧
    @RequestMapping(value="/delete" ,method = RequestMethod.POST)
    public String delete(@Valid TcherFetch tcherFetch, Model model, BindingResult result)
    {
        System.out.println(tcherID=tcherFetch.getTcherID());
        System.out.println(Time=tcherFetch.getTime());
        if(result.hasErrors())
        {
            model.addAttribute("MSG","error");
        }
        else
        {
            model.addAttribute("MSG","right");
        }

        Reservation reservation = reserveRepository.findByTcherIDAndAndResrvTime(tcherID,Time);
        reserveRepository.delete(reservation.getId());
        return "teacher";
    }

}
