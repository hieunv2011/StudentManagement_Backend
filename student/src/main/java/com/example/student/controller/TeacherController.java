package com.example.student.controller;

import com.example.student.model.Teacher;
import com.example.student.service.ClassServiceImpl;
import com.example.student.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teacher")
@CrossOrigin
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @PostMapping("/add")
    public String add(@RequestBody Teacher teacher){
        teacherService.saveTeacher(teacher);
        return "Thêm giáo viên thành công";
    }
    @GetMapping("/getAll")
    public List<Teacher> getAllTeacher() {
        return teacherService.getAllTeacher();
    }

    @GetMapping("/detail/{class_id}")
    public ResponseEntity<List<Teacher>> getTeacherByClassId(@PathVariable int class_id) {
        List<Teacher> teachers = teacherService.getTeacherByClassId(class_id);
        if (teachers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable int id) {
        Optional<Teacher> optionalTeacher = teacherService.getTeacherById(id);
        if (optionalTeacher.isPresent()) {
            return ResponseEntity.ok(optionalTeacher.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
