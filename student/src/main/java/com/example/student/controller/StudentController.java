    package com.example.student.controller;

    import com.example.student.model.Class;
    import com.example.student.model.Student;
    import com.example.student.service.StudentService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;
    import java.util.Optional;

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

        @GetMapping("/detail/{class_id}")
        public ResponseEntity<List<Student>> getStudentsByClassId(@PathVariable int class_id) {
            List<Student> students = studentService.getStudentsByClassId(class_id);
            if (students.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(students, HttpStatus.OK);
        }
        @GetMapping("/{id}")
        public ResponseEntity<Student> getStudentById(@PathVariable int id) {
            Optional<Student> optionalStudent = studentService.getStudentById(id);
            if (optionalStudent.isPresent()) {
                return ResponseEntity.ok(optionalStudent.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        }

    }
