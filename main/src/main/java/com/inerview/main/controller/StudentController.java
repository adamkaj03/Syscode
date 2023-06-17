package com.inerview.main.controller;

import com.inerview.main.model.Student;
import com.inerview.main.services.StudentService;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/profile")
public class StudentController {

    @Autowired
    private StudentService studentService;

    //az összes tanuló listázása
    @GetMapping("/students")
    public List<Student> getStudents(){
        List<Student> list = studentService.getAllStudent();
        return list;
    }

    //a megadott id-vel rendelkező tanuló módosítása
    @PutMapping("/students/{studentId}")
    public ResponseEntity<String> updateStudent(@PathVariable UUID studentId, @RequestBody Student student){
        Student modifiable = studentService.getStudentById(studentId);
        boolean isGoodEmail = EmailValidator.getInstance().isValid(student.getEmail());
        if(modifiable != null && isGoodEmail){
            modifiable.setName(student.getName());
            modifiable.setEmail(student.getEmail());
            modifiable.setId(studentId);
            studentService.deleteById(studentId);
            studentService.save(modifiable);
            return ResponseEntity.status(HttpStatus.OK).body("Modification was success.");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id or the email was incorrect.");
        }
    }

    //új tanuló beillesztése
    @PostMapping("/students")
    public ResponseEntity<String> addStudent(@RequestBody Student student){
        student.setId(UUID.randomUUID());
        Student returnValue = studentService.save(student);
        if(returnValue != null){
            return ResponseEntity.status(HttpStatus.OK).body("New student inserted.");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Can not insert student, because email is invalid.");
        }
    }

    //tanuló törlése
    @DeleteMapping("/students/{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable UUID studentId){
        Student deletedStudent = studentService.getStudentById(studentId);
        if(deletedStudent != null){
            studentService.deleteById(studentId);
            return ResponseEntity.status(HttpStatus.OK).body("Requested student deleted.");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Can not insert student, because email is invalid.");
        }
    }

}
