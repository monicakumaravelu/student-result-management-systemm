package com.srms.controller;

import com.srms.model.Marks;
import com.srms.model.Student;
import com.srms.repository.MarksRepository;
import com.srms.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/marks")
@CrossOrigin(origins = "http://localhost:3000")
public class MarksController {

    @Autowired
    private MarksRepository marksRepository;

    @Autowired
    private StudentRepository studentRepository;

    
    @GetMapping("/all")
    public List<Marks> getAllMarks() {
        return marksRepository.findAll();
    }

    
    @GetMapping("/student/{id}")
    public List<Marks> getMarksByStudentId(@PathVariable int id) {
        return marksRepository.findByStudentStudentId(id);
    }

    
    @PostMapping("/add")
    public Map<String, Object> addMarks(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();

        int studentId = (int) request.get("studentId");
        String subject = (String) request.get("subject");
        int marks = (int) request.get("marks");

        Optional<Student> studentOpt = studentRepository.findById(studentId);
        if (studentOpt.isEmpty()) {
            response.put("status", "error");
            response.put("message", "❌ Student not found!");
            return response;
        }

        Student student = studentOpt.get();

       
        Marks existing = marksRepository.findByStudentStudentIdAndSubject(studentId, subject);
        if (existing != null) {
            response.put("status", "error");
            response.put("message", "⚠️ Marks already exist for this subject!");
            return response;
        }

        Marks newMarks = new Marks();
        newMarks.setStudent(student);
        newMarks.setSubject(subject);
        newMarks.setMarks(marks);
        newMarks.setCreatedAt(java.time.LocalDateTime.now());
        newMarks.setUpdatedAt(java.time.LocalDateTime.now());

        marksRepository.save(newMarks);

        response.put("status", "success");
        response.put("message", "✅ Marks added successfully!");
        response.put("marks", newMarks);
        return response;
    }

    
    @PutMapping("/update/{id}")
    public Map<String, Object> updateMarks(@PathVariable int id, @RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();

        Optional<Marks> marksOpt = marksRepository.findById(id);
        if (marksOpt.isEmpty()) {
            response.put("status", "error");
            response.put("message", "❌ Marks record not found!");
            return response;
        }

        Marks existing = marksOpt.get();
        int newMarks = (int) request.get("marks");
        existing.setMarks(newMarks);
        existing.setUpdatedAt(java.time.LocalDateTime.now());

        marksRepository.save(existing);

        response.put("status", "success");
        response.put("message", "✅ Marks updated successfully!");
        response.put("marks", existing);
        return response;
    }

   
    @DeleteMapping("/delete/{id}")
    public Map<String, Object> deleteMarks(@PathVariable int id) {
        Map<String, Object> response = new HashMap<>();

        Optional<Marks> marksOpt = marksRepository.findById(id);
        if (marksOpt.isEmpty()) {
            response.put("status", "error");
            response.put("message", "❌ Marks record not found!");
            return response;
        }

        marksRepository.deleteById(id);
        response.put("status", "success");
        response.put("message", "🗑️ Marks deleted successfully!");
        return response;
    }
}
