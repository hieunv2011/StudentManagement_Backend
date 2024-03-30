package com.example.student.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
public class Class {
    public Class(){}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String mahocphan;
    private String malop;
    private String tenhocphan;
    private  String room;
    private String studentnumber;
//    private String teachername;
    private String time;
    private String doclink;
    private String vidlink;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "class_students",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "class_teachers",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    private List<Teacher> teachers;
}
