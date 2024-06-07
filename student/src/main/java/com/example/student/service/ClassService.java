package com.example.student.service;

import com.example.student.model.Class;
import com.example.student.model.Student;

import java.util.List;
import java.util.Optional;

public interface ClassService {
    public Class saveClass(Class lophoc);
    public List<Class> getAllClass();
//    boolean login(String username, String password);
    public Class updateClass(int id, Class updatedClass); // Phương thức cập nhật dữ liệu
    public void deleteClass(int id); // Phương thức xoá dữ liệu
    Optional<Class> getClassById(int id); // Phương thức để lấy một lớp học dựa trên ID
    List<Class> getClassesByStudentId(int student_id);
    Optional<Class> getClassInformationById(int id);

    void removeStudentFromClass(int classId, int studentId);
    List<Class> searchClassByCodes(String mahocphan, String malop);

    void addStudentToClass(int classId, int studentId);

}
