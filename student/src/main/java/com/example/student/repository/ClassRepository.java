package com.example.student.repository;

import com.example.student.model.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<Class, Integer> {
    @Query("SELECT c FROM Class c JOIN c.students s WHERE s.id = :student_id")
    List<Class> findClassesByStudentId(int student_id);

}

