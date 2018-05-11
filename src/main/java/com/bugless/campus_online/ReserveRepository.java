package com.bugless.campus_online;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.transaction.Transactional;
import java.util.List;

public interface ReserveRepository extends JpaRepository<Reservation, Integer> {

    /*------------------------------------张博凯------------------------------------*/
    List<Reservation> findByTcherID(String tcherID);

    @Query(value = "select * from reservation r where r.tcher_id=?1 and r.resrv_time=?2",nativeQuery = true)
    Reservation findByTcherIDAndAndResrvTime(String tcherID,String resrvTime);

    @Query(value = "update reservation r set r.resrv_flag=1 where r.tcher_id=?1 and r.resrv_time=?2 ", nativeQuery = true)
    @Modifying
    @Transactional
    public void updateReservation(String tcherID,String resrvTime);

    @Query(value = "delete from reservation  where tcher_id=?1 and resrv_time=?2 ", nativeQuery = true)
    @Modifying
    @Transactional
    public void delete(String tcherID,String resrvTime);
    //http://www.cnblogs.com/hawell/p/SpringDataJpa.html
    /*------------------------------------张博凯------------------------------------*/


    /*------------------------------------定理------------------------------------*/
    List<Reservation> findByResrvFlag(int reserv_flag);
    List<Reservation> findByStuID(String stuID);
    //学生提交预约1
    @Query(value = "update reservation r set r.stu_id = ?1 where r.tcher_id = ?2 and r.resrv_time = ?3", nativeQuery = true)
    @Modifying
    @Transactional
    public void submitReserve1(String stuID, String tcher_id, String resrv_time);

    //学生提交预约2
    @Query(value = "update reservation r set r.resrv_flag=1 where r.tcher_id = ?1 and r.resrv_time = ?2", nativeQuery = true)
    @Modifying
    @Transactional
    public void submitReserve2(String tcher_id, String trsrv_time);

    //学生编辑预约
    @Query(value = "update reservation r set r.tcher_id = ?1 , r.resrv_time = ?2 where r.stu_id = ?3", nativeQuery = true)
    @Modifying
    @Transactional
    public void editReserve(String tcher_id,String resrv_time,String stu_id);

    //学生删除预约
    @Query(value = "delete from reservation where stu_id = ?1", nativeQuery = true)
    @Modifying
    @Transactional
    public void deleteReserve(String stu_id);
    /*------------------------------------定理------------------------------------*/
}