package com.example.student.controller;

import com.example.student.model.Class;
import com.example.student.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

}
