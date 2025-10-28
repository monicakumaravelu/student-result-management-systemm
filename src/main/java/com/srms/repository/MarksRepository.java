package com.srms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.srms.model.Marks;
import java.util.List;

public interface MarksRepository extends JpaRepository<Marks, Integer> {
    
    List<Marks> findByStudentStudentId(int studentId);

   
    Marks findByStudentStudentIdAndSubject(int studentId, String subject);
}
