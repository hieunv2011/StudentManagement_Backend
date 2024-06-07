package com.example.student.service;

import com.example.student.model.Grade;

import java.util.List;

public interface GradeService {
    void copyFromClassStudentsToGrade();
    List<Grade> getGradesByClass(int clazz);
    List<Grade> getGradesByStudent(int student);
    Grade updateGiuaky(int clazz, int student, double giuaky);
    Grade updateCuoiky(int clazz, int student, double cuoiky);
}