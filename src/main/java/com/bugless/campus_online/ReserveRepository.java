package com.bugless.campus_online;

import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ReserveRepository extends JpaRepository<Reservation, String> {
    List<Reservation> findByTcherIDAndResrvTime(String tcher_id,String resrv_time);
    List<Reservation> findDistinctByTag(int tag);

}
