package com.srms.controller;

import com.srms.model.Admin;
import com.srms.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:3000") 
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    
    @GetMapping("/all")
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

   
    @PostMapping("/login")
    public String loginAdmin(@RequestBody Admin loginRequest) {
        Admin existingAdmin = adminRepository.findByUsername(loginRequest.getUsername());

        if (existingAdmin == null) {
            return "❌ Invalid username!";
        } else if (!existingAdmin.getPassword().equals(loginRequest.getPassword())) {
            return "❌ Incorrect password!";
        } else {
            return "✅ Login successful!";
        }
    }
}
