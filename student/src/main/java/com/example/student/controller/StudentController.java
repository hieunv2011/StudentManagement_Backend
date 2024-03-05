    package com.example.student.controller;

    import com.example.student.model.Student;
    import com.example.student.service.StudentService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/student")
    @CrossOrigin
    public class StudentController {
        @Autowired
        private StudentService studentService ;

        @PostMapping("/add")
        public String add(@RequestBody Student student){
            studentService.saveStudent(student);
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
        public List<Student> getAllStudents() {
            return studentService.getAllStudent();
        }

    }
