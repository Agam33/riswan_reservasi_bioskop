package com.ra.nontonfilm.repository;

import com.ra.nontonfilm.model.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {
    Optional<Users> findUserByEmail(String email);
}
