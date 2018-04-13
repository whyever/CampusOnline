package com.bugless.campus_online;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StuLoginRepository extends JpaRepository<StuLogin, String> {
    StuLogin findByStuID(String stuID);
}
