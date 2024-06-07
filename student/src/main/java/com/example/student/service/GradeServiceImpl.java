package com.example.student.service;



import com.example.student.model.Grade;
import com.example.student.repository.GradeRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public void copyFromClassStudentsToGrade() {
        String nativeQuery = "INSERT INTO grade (subject, giuaky,cuoiky, student, clazz) " +
                "SELECT " +
                "    c.tenhocphan AS subject, " +
                "    0 AS giuaky, " +
                "    0 AS cuoiky, " +
                "    cs.student_id AS student, " +
                "    cs.class_id AS clazz " +
                "FROM " +
                "    class_students cs " +
                "    JOIN class c ON cs.class_id = c.id";
        entityManager.createNativeQuery(nativeQuery).executeUpdate();
    }

    @Autowired
    private GradeRepository gradeRepository;

    @Override
    public List<Grade> getGradesByClass(int clazz) {
        return gradeRepository.findByClazz(clazz);
    }
    @Override
    public List<Grade> getGradesByStudent(int student) {
        return gradeRepository.findByStudent(student);
    }
    @Override
    public Grade updateGiuaky(int clazz, int student, double giuaky) {
        Optional<Grade> optionalGrade = gradeRepository.findByClazzAndStudent(clazz, student);
        if (optionalGrade.isPresent()) {
            Grade grade = optionalGrade.get();
            grade.setGiuaky(giuaky);
            return gradeRepository.save(grade);
        } else {
            throw new RuntimeException("Grade not found");
        }
    }

    @Override
    public Grade updateCuoiky(int clazz, int student, double cuoiky) {
        Optional<Grade> optionalGrade = gradeRepository.findByClazzAndStudent(clazz, student);
        if (optionalGrade.isPresent()) {
            Grade grade = optionalGrade.get();
            grade.setCuoiky(cuoiky);
            return gradeRepository.save(grade);
        } else {
            throw new RuntimeException("Grade not found");
        }
    }
}