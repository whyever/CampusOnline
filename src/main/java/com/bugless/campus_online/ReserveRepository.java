package com.bugless.campus_online;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReserveRepository extends JpaRepository<Reservation, Integer> {

    List<Reservation> findByTcherID(String tcherID);

    @Query(value="select r from reservation r where r.tcher_id=?1 and r.resrv_time=?2")
    Reservation findByTcherIDAndAndResrvTime(String tcherID,String resrvTime);

    @Query(value = "delete from reservation where id=?1 ", nativeQuery = true)
    @Modifying
    public void delete(int id);

}