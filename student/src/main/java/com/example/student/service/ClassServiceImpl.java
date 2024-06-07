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
            if (updatedClass.getRoom() != null  && !updatedClass.getRoom().isEmpty()){
                existingClass.setRoom(updatedClass.getRoom());
            }
            if (updatedClass.getStudentnumber() != null  && !updatedClass.getStudentnumber().isEmpty()) {
                existingClass.setStudentnumber(updatedClass.getStudentnumber());
            }
            if (updatedClass.getTime() != null  && !updatedClass.getTime().isEmpty()) {
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

    @Override
    public List<Class> searchClassByCodes(String mahocphan, String malop) {
        if (mahocphan != null && malop != null) {
            return classRepository.findClassesByMahocphanAndMalop(mahocphan, malop);
        } else if (mahocphan != null) {
            return classRepository.findClassesByMahocphan(mahocphan);
        } else if (malop != null) {
            return classRepository.findClassesByMalop(malop);
        } else {
            return classRepository.findAll();
        }
    }

    @Override
    public void addStudentToClass(int classId, int studentId) {
        // Tìm lớp học theo ID
        Optional<Class> optionalClass = classRepository.findById(classId);
        if (optionalClass.isPresent()) {
            Class classroom = optionalClass.get();

            // Tìm sinh viên theo ID
            Optional<Student> optionalStudent = studentRepository.findById(studentId);
            if (optionalStudent.isPresent()) {
                Student student = optionalStudent.get();

                // Lấy danh sách sinh viên hiện tại của lớp học
                List<Student> students = classroom.getStudents();

                // Kiểm tra xem sinh viên đã tồn tại trong lớp học chưa
                if (!students.contains(student)) {
                    // Thêm sinh viên vào danh sách sinh viên của lớp học
                    students.add(student);
                    classroom.setStudents(students);
                    classRepository.save(classroom);
                } else {
                    throw new IllegalArgumentException("Sinh viên đã tồn tại trong lớp học");
                }
            } else {
                throw new IllegalArgumentException("Sinh viên không tồn tại");
            }
        } else {
            throw new IllegalArgumentException("Lớp học không tồn tại");
        }
    }

}
