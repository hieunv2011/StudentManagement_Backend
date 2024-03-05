package com.example.student.service;

import com.example.student.model.Student;

import java.util.List;

public interface StudentService {
    public Student saveStudent(Student student);
    public List<Student> getAllStudent();
//    boolean login(String username, String password);
}
