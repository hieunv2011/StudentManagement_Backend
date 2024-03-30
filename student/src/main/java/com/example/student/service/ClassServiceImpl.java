package com.example.student.service;

import com.example.student.model.Class;
import com.example.student.model.Student;
import com.example.student.repository.ClassRepository;
import com.example.student.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private StudentRepository studentRepository;
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

    @Override
    public Class updateClass(int id, Class updatedClass) {
        Optional<Class> optionalClass = classRepository.findById(id);
        if (optionalClass.isPresent()) {
            Class existingClass = optionalClass.get();
            if (updatedClass.getMahocphan() != null && !updatedClass.getMahocphan().isEmpty()) {
                existingClass.setMahocphan(updatedClass.getMahocphan());
            }
            if (updatedClass.getMalop() != null && !updatedClass.getMalop().isEmpty()){
                existingClass.setMalop(updatedClass.getMalop());
            }
            if (updatedClass.getRoom() != null) {
                existingClass.setRoom(updatedClass.getRoom());
            }
            if (updatedClass.getStudentnumber() != null) {
                existingClass.setStudentnumber(updatedClass.getStudentnumber());
            }
            if (updatedClass.getTime() != null) {
                existingClass.setTime(updatedClass.getTime());
            }
            if (updatedClass.getRoom() != null) {
                existingClass.setRoom(updatedClass.getRoom());
            }
            if (updatedClass.getStudentnumber() != null) {
                existingClass.setStudentnumber(updatedClass.getStudentnumber());
            }
            if (updatedClass.getTime() != null) {
                existingClass.setTime(updatedClass.getTime());
            }
            return classRepository.save(existingClass);
        } else {
            throw new IllegalArgumentException("Class with id " + id + " not found.");
        }
    }

    @Override
    public void deleteClass(int id) {
        classRepository.deleteById(id);
    }
    @Override
    public Optional<Class> getClassById(int id) {
        return classRepository.findById(id);
    }

    @Override
    public List<Class> getClassesByStudentId(int student_id) {
        return classRepository.findClassesByStudentId(student_id);
    }

    @Override
    public Optional<Class> getClassInformationById(int id) {
        return classRepository.findById(id);
    }

    @Override
    @Transactional
    public void removeStudentFromClass(int classId, int studentId) {
        classRepository.removeStudentFromClass(classId, studentId);
    }

}
