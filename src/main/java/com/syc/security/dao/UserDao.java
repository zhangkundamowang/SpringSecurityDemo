package com.syc.security.dao;

import com.syc.security.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {

    //User findByUsernameAndPassword();
    //User findByPassword(String password);

    User findByUsername(String username);

}
