package com.bugless.campus_online;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface StuLoginRepository extends JpaRepository<StuLogin, String> {
    StuLogin findByStuID(String stuID);

    @Query(value = "update stu_login s set s.stu_passwd = ?1 where s.stu_id = ?2", nativeQuery = true)
    @Modifying
    @Transactional
    public void changStuPassword(String newPassword, String stuId);
}
