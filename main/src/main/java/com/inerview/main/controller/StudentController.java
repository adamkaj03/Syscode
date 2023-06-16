package com.inerview.main.controller;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/profile")
public class StudentController {

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
