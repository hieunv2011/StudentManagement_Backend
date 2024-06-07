package com.example.student.service;

import com.example.student.model.Student;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface StudentService {
    public Student saveStudent(Student student);
    public List<Student> getAllStudent();
//    boolean login(String username, String password);

    List<Student> getStudentsByClassId(int class_id);
    Optional<Student> getStudentById(int id);
    List<Student> searchStudents(String firstName, String lastName, Integer id, String email);

    //Add student
    Student addStudent(Student student);

    Student saveStudentWithImage(int studentId, MultipartFile file) throws IOException;
    byte[] getStudentImage(int studentId);

}
