package com.example.student.controller;

import com.example.student.dto.GradeUpdateRequest;
import com.example.student.model.Grade;
import com.example.student.repository.GradeRepository;
import com.example.student.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/grade")
@CrossOrigin
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @Autowired
    private GradeRepository gradeRepository;

    @PostMapping("/copyData")
    public String copyData() {
        try {
            gradeService.copyFromClassStudentsToGrade();
            return "Data copied successfully";
        } catch (Exception e) {
            return "Failed to copy data: " + e.getMessage();
        }
    }
    @GetMapping("/class/{clazz}")
    public List<Grade> getGradesByClass(@PathVariable int clazz) {
        return gradeService.getGradesByClass(clazz);
    }
    @GetMapping("/student/{student}")
    public List<Grade> getGradesByStudent(@PathVariable int student) {
        return gradeService.getGradesByStudent(student);
    }

    @PutMapping("/{clazz}/{student}/giuaky")
    public Grade updateGiuaky(@PathVariable int clazz, @PathVariable int student, @RequestBody GradeUpdateRequest request) {
        return gradeService.updateGiuaky(clazz, student, request.getGiuaky());
    }

    @PutMapping("/{clazz}/{student}/cuoiky")
    public Grade updateCuoiky(@PathVariable int clazz, @PathVariable int student, @RequestBody GradeUpdateRequest request) {
        return gradeService.updateCuoiky(clazz, student, request.getCuoiky());
    }

}