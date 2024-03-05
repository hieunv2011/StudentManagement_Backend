package com.example.student.service;

import com.example.student.model.Class;

import java.util.List;

public interface ClassService {
    public Class saveClass(Class lophoc);
    public List<Class> getAllClass();
//    boolean login(String username, String password);
}
