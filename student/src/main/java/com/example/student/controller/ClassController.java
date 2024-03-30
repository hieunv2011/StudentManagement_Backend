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
@RequestMapping("/api/v1/class")
@CrossOrigin
public class ClassController {
    @Autowired
    private ClassService classService ;

    //Thông tin tất cả các lớp
    //Tìm kiếm theo ....
    @GetMapping("")
    public List<Class> getAllClass() {
        return classService.getAllClass();
    }

    // Cập nhật thông tin lớp
    @PutMapping("/update/{id}")
    public String updateClass(@PathVariable int id, @RequestBody Class updatedClass) {
        classService.updateClass(id, updatedClass);
        return "Cập nhật lớp học thành công";
    }

    //Xoá lớp
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

    //Lấy thông tin về các lớp mà học sinh học
    @GetMapping("/detail/{student_id}")
    public ResponseEntity<List<Class>> getClassesByStudentId(@PathVariable int student_id) {
        List<Class> classes = classService.getClassesByStudentId(student_id);
        if (classes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(classes, HttpStatus.OK);
    }

    //Thông tin cụ thể về một lớp
    @GetMapping("/information/{id}")
    public ResponseEntity<Class> getClassInformationById(@PathVariable int id) {
        Optional<Class> optionalClass = classService.getClassById(id);
        if (optionalClass.isPresent()) {
            return ResponseEntity.ok(optionalClass.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Xoá student_id ra khỏi class_id
    @DeleteMapping("/{classId}/delete/{studentId}")
    public ResponseEntity<String> removeStudentFromClass(@PathVariable int classId, @PathVariable int studentId) {
        try {
            classService.removeStudentFromClass(classId, studentId);
            return ResponseEntity.ok("Sinh viên đã được xóa khỏi lớp học thành công");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Đã xảy ra lỗi khi xóa sinh viên khỏi lớp học");
        }
    }
}
