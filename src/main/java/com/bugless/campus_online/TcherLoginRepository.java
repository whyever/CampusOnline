package com.bugless.campus_online;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface TcherLoginRepository extends JpaRepository<TcherLogin, String> {
    TcherLogin findByTcherID(String tcherID);

    @Query(value = "update tcher_login t set t.tcher_passwd=?2 where t.tcher_id=?1 ", nativeQuery = true)
    @Modifying
    @Transactional
    public void update(String tcherID,String newpasswd);
}
