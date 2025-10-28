package com.srms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.srms.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
   
    Admin findByUsername(String username);
}
