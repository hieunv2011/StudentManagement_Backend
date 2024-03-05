package com.example.student.service;

import com.example.student.model.Class;
import com.example.student.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassRepository classRepository;
    @Override
    public Class saveClass(Class lophoc) {
        return classRepository.save(lophoc);
    }

    @Override
    public List<Class> getAllClass() {
        return classRepository.findAll();
    }
//    private static final String VALID_USERNAME = "admin";
//    private static final String VALID_PASSWORD = "password";
//
//    @Override
//    public boolean login(String username, String password) {
//        return username.equals(VALID_USERNAME) && password.equals(VALID_PASSWORD);
//    }
}
