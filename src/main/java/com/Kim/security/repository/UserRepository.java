package com.Kim.security.repository;

import com.Kim.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    //SELECT * FROM user WHERE username=?
    public User findByUsername(String username);
}
