package com.inerview.main.controller;

import com.inerview.main.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/profile")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public String getStudents(){
        return "Hello";
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
