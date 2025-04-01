package com.example.SpringSecurity.repo;

import com.example.SpringSecurity.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepo extends JpaRepository<Users, Integer> {

    Users getByUsername(String username);

}
