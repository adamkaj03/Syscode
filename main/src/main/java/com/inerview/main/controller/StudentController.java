package com.inerview.main.controller;

import com.inerview.main.model.Student;
import com.inerview.main.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/profile")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public List<Student> getStudents(){
        List<Student> lista = studentService.getAllStudent();
        return lista;
    }

    @PutMapping("/students")
    public void updateStudent(){

    }

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student){
        student.setId(UUID.randomUUID());
        studentService.save(student);
        return student;
    }

    @DeleteMapping("/students")
    public void deleteStudent(){

    }

}
