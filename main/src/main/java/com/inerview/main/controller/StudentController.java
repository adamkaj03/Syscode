package com.inerview.main.controller;

import com.inerview.main.model.Student;
import com.inerview.main.services.StudentService;
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

    @GetMapping("/students")
    public List<Student> getStudents(){
        List<Student> lista = studentService.getAllStudent();
        return lista;
    }

    @PutMapping("/students/{studentId}")
    public Student updateStudent(@PathVariable UUID studentId, @RequestBody Student student){
        Student modifiable = studentService.getStudentById(studentId);
        if(modifiable != null){
            modifiable.setName(student.getName());
            modifiable.setEmail(student.getEmail());
            modifiable.setId(studentId);
            studentService.deleteById(studentId);
            studentService.save(modifiable);
        }
        else{
            //hiba
        }
        return modifiable;
    }

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student){
        student.setId(UUID.randomUUID());
        studentService.save(student);
        return student;
    }

    @DeleteMapping("/students/{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable UUID studentId){
        Student deletedStudent = studentService.getStudentById(studentId);
        if(deletedStudent != null){
            studentService.deleteById(studentId);
            return ResponseEntity.status(HttpStatus.OK).body("Requested student deleted.");
        }
        else{

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Requested id not found.");
        }
    }

}
