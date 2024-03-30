package com.example.student.repository;

import com.example.student.model.Class;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<Class, Integer> {
    @Query("SELECT c FROM Class c JOIN c.students s WHERE s.id = :student_id")
    List<Class> findClassesByStudentId(int student_id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM class_students WHERE class_id = :classId AND student_id = :studentId", nativeQuery = true)
    void removeStudentFromClass(@Param("classId") int classId, @Param("studentId") int studentId);

}

