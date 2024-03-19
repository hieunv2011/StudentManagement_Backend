package com.example.student.repository;

import com.example.student.entities.Role;
import com.example.student.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {


    Optional<User> findByEmail(String email);
    User findByRole(Role role);

}
