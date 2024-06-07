package com.example.student.service;

import com.example.student.model.Student;
import com.example.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
    @Override
    public List<Student> getStudentsByClassId(int class_id) {
    return studentRepository.findStudentsByClassId(class_id);
    }
    @Override
    public Optional<Student> getStudentById(int id) {
        return studentRepository.findById(id);
    }

    @Override
    public List<Student> searchStudents(String firstName, String lastName, Integer id, String email) {
        if (id != null) {
            Optional<Student> studentById = studentRepository.findById(id);
            if (studentById.isPresent()) {
                return Collections.singletonList(studentById.get());
            } else {
                return Collections.emptyList();
            }
//        } else if (firstName != null && lastName != null) {
//            return studentRepository.findStudentsByFirstNameAndLastName(firstName, lastName);
        } else if (email != null) {
            return studentRepository.findStudentsByEmail(email);
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public Student saveStudentWithImage(int studentId, MultipartFile file) throws IOException {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            student.setProfileImage(file.getBytes());
            return studentRepository.save(student);
        }
        return null;
    }

    @Override
    public byte[] getStudentImage(int studentId) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        return optionalStudent.map(Student::getProfileImage).orElse(null);
    }


    //Add student
    @Override
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

}
