package com.srms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.srms.model.Student;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    
    Student findByEmail(String email);

    
    List<Student> findByDepartment(String department);
}

