package com.example.student.controller;

import com.example.student.model.Class;
import com.example.student.model.Student;
import com.example.student.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/class")
@CrossOrigin
public class ClassController {
    @Autowired
    private ClassService classService ;

    @PostMapping("/add")
    public String add(@RequestBody Class lophoc){
        classService.saveClass(lophoc);
        return "Đăng ký tài khoản thành công";
    }

//        @PostMapping("/login")
//        public String login(@RequestBody Student student) {
//            boolean isAuthenticated = studentService.login(student.getUsername(), student.getPassword());
//            if (isAuthenticated) {
//                return "Đăng nhập thành công!";
//            } else {
//                return "Sai tài khoản hoặc mật khẩu";
//            }
//        }

    @GetMapping("/getAll")
    public List<Class> getAllClass() {
        return classService.getAllClass();
    }


    @PutMapping("/update/{id}")
    public String updateClass(@PathVariable int id, @RequestBody Class updatedClass) {
        classService.updateClass(id, updatedClass);
        return "Cập nhật lớp học thành công";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteClass(@PathVariable int id) {
        classService.deleteClass(id);
        return "Xoá lớp học thành công";
    }
    //Get class theo id
    @GetMapping("/{id}")
    public ResponseEntity<Class> getClassById(@PathVariable int id) {
        Optional<Class> optionalClass = classService.getClassById(id);
        if (optionalClass.isPresent()) {
            return ResponseEntity.ok(optionalClass.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/detail/{student_id}")
    public ResponseEntity<List<Class>> getClassesByStudentId(@PathVariable int student_id) {
        List<Class> classes = classService.getClassesByStudentId(student_id);
        if (classes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(classes, HttpStatus.OK);
    }

    @GetMapping("/information/{id}")
    public ResponseEntity<Class> getClassInformationById(@PathVariable int id) {
        Optional<Class> optionalClass = classService.getClassById(id);
        if (optionalClass.isPresent()) {
            return ResponseEntity.ok(optionalClass.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
