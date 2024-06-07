package com.example.student.repository;

import com.example.student.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Integer> {
    List<Grade> findByClazz(int clazz);
    List<Grade> findByStudent(int student);
    Optional<Grade> findByClazzAndStudent(int clazz, int student);
}