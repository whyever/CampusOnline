package com.bugless.campus_online;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TcherLoginRepository extends JpaRepository<TcherLogin, String> {
    TcherLogin findByTcherID(String tcherID);
}
