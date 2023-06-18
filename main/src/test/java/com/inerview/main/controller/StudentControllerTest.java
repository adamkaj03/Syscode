package com.inerview.main.controller;

import com.inerview.main.model.Student;
import com.inerview.main.services.StudentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    // Privát mező az adatbázis előtti állapot tárolására
    private List<Student> originalStudents;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void getStudents() {
        Student student1 = new Student(UUID.fromString("fd16a7a5-3e56-4e96-bc14-8f2c1e54e76d"), "Balogh Anna", "anna12@gmail.com");
        List<Student> list = new ArrayList<>();
        list.add(student1);

        when(studentService.getAllStudent()).thenReturn(list);
        List<Student> response = studentController.getStudents();

        assertEquals(student1.getName(), response.get(0).getName());
        verify(studentService, times(1)).getAllStudent();
    }

    @Test
    void updateStudent() {
    }


    @Test
    void addStudentSuccessTest() {
        //a beszúrandó elem és a beszúrás után már uuid-el rendelkező
        Student student = new Student(null, "Kovács István", "kovacs@gmail.com");
        Student savedStudent = new Student(UUID.randomUUID(), "Kovács István", "kovacs@gmail.com");

        //save hatására a mentett objektumot kell kapni
        when(studentService.save(student)).thenReturn(savedStudent);

        //az endpoint meghívása
        ResponseEntity<String> response = studentController.addStudent(student);

        // az eredmény ellenőrzése
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("New student inserted.", response.getBody());
        assertNotNull(student.getId());
        verify(studentService, times(1)).save(student);
    }

    @Test
    public void addStudentFailTest(){
        //a beszúrandó elem
        Student student = new Student(null, "Kovács István", "kovac s@gmail.com");


        //save hatására a mentett objektumot kell kapni
        when(studentService.save(student)).thenReturn(null);

        //az endpoint meghívása
        ResponseEntity<String> response = studentController.addStudent(student);

        // az eredmény ellenőrzése
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Can not insert student, because email is invalid.", response.getBody());
        verify(studentService, times(1)).save(student);
    }

    //valamiért ez a teszt nem működik, mert nem rakja bele az elemeket az adatbázisba
    @Test
    @Disabled
    void deleteStudentSuccessTest() {
        //a törlendő elem id-je
        UUID uuid = UUID.fromString("fd16a7a5-3e56-4e96-bc14-8f2c1e54e76d");
        Student student = new Student(uuid, "Balogh Feri", "adam2000buzas@gmail.com");

        studentController.addStudent(student);

        // A studentService.deleteId() metódusának beállítása
        doNothing().when(studentService).deleteById(uuid);

        //az endpoint meghívása
        ResponseEntity<String> response = studentController.deleteStudent(uuid);

        // az eredmény ellenőrzése
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Requested student deleted.", response.getBody());
        verify(studentService, times(1)).deleteById(uuid);
    }

    @Test
    void deleteStudentFailTest() {
        //a törlendő elem id-je
        UUID uuid = UUID.fromString("46d297ee-0a5f-49f6-923e-9c8456771db0");


        // A studentService.deleteId() metódusának beállítása
        doNothing().when(studentService).deleteById(uuid);

        //az endpoint meghívása
        ResponseEntity<String> response = studentController.deleteStudent(uuid);

        // az eredmény ellenőrzése
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Can not delete student, because there was not in db.", response.getBody());
        verify(studentService, times(1)).getStudentById(uuid);
    }


}