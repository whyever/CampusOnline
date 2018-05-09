package com.bugless.campus_online;

//import com.sun.org.apache.xpath.internal.operations.String;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
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
        /*数据库查询重写
        Reservation reservation=reserveRepository.findByTcherIDAndAndResrvTime(tcherID,Time);
        reservation.setResrvFlag(1);
        reserveRepository.save(reservation);
        */
        reserveRepository.updateReservation(tcherID,Time);
        return("update");

    }

    //首先测试这个吧
    @RequestMapping(value="/delete" ,method = RequestMethod.POST)
    public String delete(TcherFetch tcherFetch)
    {
        tcherID=tcherFetch.getTcherID();
        Time=tcherFetch.getTime();
        /*JPQL
        EntityManagerFactory emfactory = Persistence.
                createEntityManagerFactory( "link_JPA" );
        EntityManager entitymanager = emfactory.
                createEntityManager();
        Query query=entitymanager.
                createQuery(
                "select r.id "+
                "from  reservation r "+
                "where r.tcher_id=?1 and r.resrv_time=?2");
        query.setParameter(1,tcherID);
        query.setParameter(2,Time);
        Integer n= (Integer) query.getSingleResult();
        */
        Reservation reservation = reserveRepository.findByTcherIDAndAndResrvTime(tcherID,Time);
        reserveRepository.delete(reservation.getId());
        return("delete");
    }

}
