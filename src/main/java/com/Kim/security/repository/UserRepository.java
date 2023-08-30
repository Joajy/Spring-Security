package com.Kim.security.repository;

import com.Kim.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    //SELECT * FROM user WHERE username=?
    public User findByUsername(String username);

    Optional<User> findByProviderAndProviderId(String provider, String providerId);
}
