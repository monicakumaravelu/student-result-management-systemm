package com.srms.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "marks", uniqueConstraints = @UniqueConstraint(columnNames = {"student_id", "subject"}))
public class Marks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(nullable = false, length = 50)
    private String subject;

    @Column(nullable = false)
    private int marks;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Constructors
    public Marks() {}

    public Marks(Student student, String subject, int marks, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.student = student;
        this.subject = subject;
        this.marks = marks;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
