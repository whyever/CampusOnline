package com.bugless.campus_online;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login, String> {
    Login findByIDAndFlag(String ID, int flag);
}
