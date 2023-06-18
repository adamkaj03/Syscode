package com.inerview.main.controller;

import com.inerview.main.model.Student;
import com.inerview.main.services.StudentService;
import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/profile")
public class StudentController {

    @Autowired
    private StudentService studentService;

    private Logger logger = LoggerFactory.getLogger(StudentController.class);

    //az összes tanuló listázása
    @GetMapping("/students")
    public List<Student> getStudents(){
        //loggolom kérés url-t és a method típusát
        logger.info("Request method: GET");
        logger.info("Request URL: localhost:8080/profile/students");

        //lekéri a service az összes tanulót az adatbázisból
        List<Student> list = studentService.getAllStudent();

        //válasz loggolása
        logger.info("Response Status code: OK 200");
        logger.info("Response Body: ");
        for(Student s : list){
            logger.info(s.toString());
        }
        return list;
    }

    //a megadott id-vel rendelkező tanuló módosítása
    @PutMapping("/students/{studentId}")
    public ResponseEntity<String> updateStudent(@PathVariable UUID studentId, @RequestBody Student student){
        //loggolás
        logger.info("Request method: PUT");
        logger.info("Request body: " + student.toString());
        logger.info("Request URL: localhost:8080/profile/students/" + studentId);

        Student modifiable = studentService.getStudentById(studentId);
        boolean isGoodEmail = EmailValidator.getInstance().isValid(student.getEmail());
        //megnézem, hogy van-e a rendszerben a PathVariable-ben megadott id-vel Student és,
        // hogy annak jó-e az email-je
        if(modifiable != null && isGoodEmail){
            //modósítom a régit  requestbodynak megfelelően
            modifiable.setName(student.getName());
            modifiable.setEmail(student.getEmail());
            modifiable.setId(studentId);
            //törlöm a régit és beillesztem az újat
            studentService.deleteById(studentId);
            studentService.save(modifiable);
            //loggolás
            logger.info("Response Status code: 200 OK");
            logger.info("Response Body: Modification was success.");
            return ResponseEntity.status(HttpStatus.OK).body("Modification was success.");
        }
        else{
            //loggolás
            logger.info("Response Status code: " + HttpStatus.NOT_FOUND);
            logger.info("Response Body: Id or the email was incorrect.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id or the email was incorrect.");
        }
    }

    //új tanuló beillesztése
    @PostMapping("/students")
    public ResponseEntity<String> addStudent(@RequestBody Student student){
        //loggolás
        logger.info("Request method: POST");
        logger.info("Request body: " + student.toString());
        logger.info("Request URL: localhost:8080/profile/students");

        //az adatbázisba illesztés előtt generálok egy id-t a student-nek
        student.setId(UUID.randomUUID());
        Student returnValue = studentService.save(student);
        if(returnValue != null){
            //loggolás
            logger.info("Response Status code: " + HttpStatus.OK);
            logger.info("Response Body: New student inserted.");
            return ResponseEntity.status(HttpStatus.OK).body("New student inserted.");
        }
        else{
            //loggolás
            logger.info("Response Status code: " + HttpStatus.NOT_FOUND);
            logger.info("Response Body: Can not insert student, because email is invalid.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Can not insert student, because email is invalid.");
        }
    }

    //tanuló törlése
    @DeleteMapping("/students/{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable UUID studentId){
        //loggolás
        logger.info("Request method: DELETE");
        logger.info("Request URL: localhost:8080/profile/students/" + studentId);
        //student megkeresése adatb-ből
        Student deletedStudent = studentService.getStudentById(studentId);
        if(deletedStudent != null){
            //elem törlése
            studentService.deleteById(studentId);
            //loggolás
            logger.info("Response Status code: " + HttpStatus.OK);
            logger.info("Requested student deleted.");
            return ResponseEntity.status(HttpStatus.OK).body("Requested student deleted.");
        }
        else{
            logger.info("Response Status code: " + HttpStatus.NOT_FOUND);
            logger.info("Response Body: Can not delete student, because there was not in db.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Can not delete student, because there was not in db.");
        }
    }

}
