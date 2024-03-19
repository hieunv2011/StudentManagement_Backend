package com.example.student.service;

import com.example.student.model.Teacher;
import com.example.student.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements  TeacherService{
    @Autowired
    private TeacherRepository teacherRepository;
    @Override
    public Teacher saveTeacher(Teacher teacher) {return  teacherRepository.save(teacher);}
    @Override
    public List<Teacher> getAllTeacher(){return teacherRepository.findAll();}
    @Override
    public List<Teacher> getTeacherByClassId(int class_id){
        return teacherRepository.findTeacherByClassId(class_id);
    }
    @Override
    public Optional<Teacher> getTeacherById(int id){return  teacherRepository.findById(id);}

}
