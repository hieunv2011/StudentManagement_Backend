    package com.example.student.controller;

    import com.example.student.model.Class;
    import com.example.student.model.Student;
    import com.example.student.service.StudentService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.multipart.MultipartFile;

    import java.io.IOException;
    import java.util.List;
    import java.util.Optional;

    @RestController
    @RequestMapping("api/v1/student")
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

//        @GetMapping("/getAll")
//        public List<Student> getAllStudents() {
//            return studentService.getAllStudent();
//        }

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

        @GetMapping("")
        public List<Student> searchStudents(
                @RequestParam(required = false) Integer id,
                @RequestParam(required = false) String email
        ) {
            if (id == null && (email == null || email.isEmpty())) {
                // Nếu không có id hoặc email được cung cấp, trả về tất cả sinh viên
                return studentService.getAllStudent();
            } else {
                // Nếu có id hoặc email được cung cấp, sử dụng phương thức searchStudents để tìm kiếm
                return studentService.searchStudents(null, null, id, email);
            }
        }

        @PostMapping("/{id}/uploadImage")
        public ResponseEntity<Student> uploadStudentImage(@PathVariable int id, @RequestParam("file") MultipartFile file) {
            try {
                Student student = studentService.saveStudentWithImage(id, file);
                return new ResponseEntity<>(student, HttpStatus.CREATED);
            } catch (IOException e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        @GetMapping("/{id}/image")
        public ResponseEntity<byte[]> getStudentImage(@PathVariable int id) {
            byte[] image = studentService.getStudentImage(id);
            if (image != null) {
                return ResponseEntity.ok().contentType(org.springframework.http.MediaType.IMAGE_JPEG).body(image);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        //Add
//        @PostMapping("/add")
//        public Student addStudent(@RequestBody Student student) {
//            return studentService.addStudent(student);
//        }

    }
