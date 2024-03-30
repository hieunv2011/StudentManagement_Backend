package com.example.student.repository;

import com.example.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query("SELECT s FROM Student s JOIN s.classes c WHERE c.id = :class_id")
    List<Student> findStudentsByClassId(int class_id);


//    @Query("SELECT s FROM Student s WHERE s.firstName = ?1 AND s.lastName = ?2")
//    List<Student> findStudentsByFirstNameAndLastName(String firstName, String lastName);

    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    List<Student> findStudentsByEmail(String email);
}