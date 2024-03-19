package com.example.student.repository;

import com.example.student.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    @Query("SELECT s FROM Teacher s JOIN s.classes c WHERE c.id = :class_id")
    List<Teacher> findTeacherByClassId(int class_id);
}