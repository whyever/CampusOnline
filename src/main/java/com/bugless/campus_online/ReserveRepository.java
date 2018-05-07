package com.bugless.campus_online;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReserveRepository extends JpaRepository<Reservation, String> {
    Reservation findByTcherIDAndResrvTime(String tcher_id,String resrv_time);
}
