package com.inerview.main.controller;

import com.inerview.main.model.Student;
import com.inerview.main.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    @PutMapping("/update")
    public void updateStudent(){

    }

    @PostMapping("/add")
    public void addStudent(){

    }

    @DeleteMapping("/delete")
    public void deleteStudent(){

    }

}
