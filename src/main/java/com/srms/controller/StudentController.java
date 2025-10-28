package com.srms.controller;

import com.srms.model.Student;
import com.srms.model.Marks;
import com.srms.repository.StudentRepository;
import com.srms.repository.MarksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/student")
@CrossOrigin(origins = "http://localhost:3000") 
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private MarksRepository marksRepository;

    // ✅ 1. Student Login
    @PostMapping("/login")
    public Map<String, Object> loginStudent(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");

        Map<String, Object> response = new HashMap<>();

        Student student = studentRepository.findByEmail(email);

        if (student == null) {
            response.put("status", "error");
            response.put("message", "❌ Student not found!");
            return response;
        }

        if (!student.getPassword().equals(password)) {
            response.put("status", "error");
            response.put("message", "❌ Incorrect password!");
            return response;
        }

        response.put("status", "success");
        response.put("message", "✅ Login successful!");
        response.put("student", student);
        return response;
    }

    
    @GetMapping("/all")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

   
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable int id) {
        return studentRepository.findById(id).orElse(null);
    }

    
    @GetMapping("/{id}/marks")
    public Map<String, Object> getStudentMarks(@PathVariable int id) {
        Map<String, Object> result = new HashMap<>();
        Optional<Student> studentOpt = studentRepository.findById(id);

        if (studentOpt.isEmpty()) {
            result.put("message", "❌ Student not found!");
            return result;
        }

        List<Marks> marksList = marksRepository.findByStudentStudentId(id);
        double total = 0;
        for (Marks m : marksList) {
            total += m.getMarks();
        }

        double average = marksList.isEmpty() ? 0 : total / marksList.size();
        double percentage = average; // assuming marks are out of 100

        result.put("student", studentOpt.get());
        result.put("marks", marksList);
        result.put("average", average);
        result.put("percentage", percentage);
        result.put("totalSubjects", marksList.size());

        return result;
    }

    
    @GetMapping("/average/{id}")
    public ResponseEntity<Double> getStudentAverage(@PathVariable int id) {
        List<Marks> marks = marksRepository.findByStudentStudentId(id);

        if (marks.isEmpty()) {
            return ResponseEntity.ok(0.0);
        }

        double average = marks.stream()
                .mapToInt(Marks::getMarks)
                .average()
                .orElse(0.0);

        return ResponseEntity.ok(average);
    }

    
    @PostMapping("/add")
    public Map<String, Object> addStudent(@RequestBody Student student) {
        Map<String, Object> response = new HashMap<>();
        try {
            // Prevent duplicate email entries
            if (studentRepository.findByEmail(student.getEmail()) != null) {
                response.put("status", "error");
                response.put("message", "⚠️ Email already exists!");
                return response;
            }

            studentRepository.save(student);
            response.put("status", "success");
            response.put("message", "✅ Student added successfully!");
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "⚠️ Error adding student: " + e.getMessage());
        }
        return response;
    }

   
    @DeleteMapping("/delete/{id}")
    public Map<String, Object> deleteStudent(@PathVariable int id) {
        Map<String, Object> response = new HashMap<>();

        if (!studentRepository.existsById(id)) {
            response.put("status", "error");
            response.put("message", "❌ Student not found!");
            return response;
        }

        studentRepository.deleteById(id);
        response.put("status", "success");
        response.put("message", "🗑️ Student deleted successfully!");
        return response;
    }
}

