package com.example.student.service;

import com.example.student.model.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherService {
    public Teacher saveTeacher(Teacher teacher);
    public List<Teacher> getAllTeacher();
//    boolean login(String username, String password);

    List<Teacher> getTeacherByClassId(int class_id);
    Optional<Teacher> getTeacherById(int id); //
}
