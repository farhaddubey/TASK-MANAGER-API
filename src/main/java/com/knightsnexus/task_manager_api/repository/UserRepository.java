package com.knightsnexus.task_manager_api.repository;

import com.knightsnexus.task_manager_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
//    findByEmail = SELECT * FROM users WHERE email = ?
}
