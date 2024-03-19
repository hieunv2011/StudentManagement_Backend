package com.example.student.service;

import com.example.student.model.Class;
import com.example.student.model.Student;
import com.example.student.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Class updateClass(int id, Class updatedClass) {
        Optional<Class> optionalClass = classRepository.findById(id);
        if (optionalClass.isPresent()) {
            Class existingClass = optionalClass.get();
            existingClass.setMahocphan(updatedClass.getMahocphan());
            existingClass.setMalop(updatedClass.getMalop());
            existingClass.setRoom(updatedClass.getRoom());
            existingClass.setStudentnumber(updatedClass.getStudentnumber());
//            existingClass.setTeachername(updatedClass.getTeachername());
            existingClass.setTime(updatedClass.getTime());

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
}
