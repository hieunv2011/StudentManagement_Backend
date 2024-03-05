package com.example.student.service;

import com.example.student.model.Student;
import com.example.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }
//    private static final String VALID_USERNAME = "admin";
//    private static final String VALID_PASSWORD = "password";
//
//    @Override
//    public boolean login(String username, String password) {
//        return username.equals(VALID_USERNAME) && password.equals(VALID_PASSWORD);
//    }
}
