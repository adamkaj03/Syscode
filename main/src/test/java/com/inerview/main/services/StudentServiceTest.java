package com.inerview.main.services;

import com.inerview.main.model.Student;
import com.inerview.main.repository.StudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@SpringBootTest
class StudentServiceTest {
    @Autowired
    private StudentRepository studentRepository;
    private StudentService underTest;


    @BeforeEach
    void setUp(){
        underTest = new StudentService(studentRepository);
    }


    @Test
    void getAllStudentTest() {
        //alapból van már két elem az adatbázisba, hogy könnyebb legyen tesztelni
        List<Student> list = underTest.getAllStudent();
        //2 elemnek kell lennie a listában
        assertEquals(2, list.size());
        //első student neve Balogh Anna
        assertEquals("Balogh Anna", list.get(0).getName());
    }

    @Test
    void getStudentByIdNotFoundTest() {
        //egy random id-t adtam meg
        Student notFoundStudent = underTest.getStudentById(UUID.fromString("550e8400-e29b-41d4-a716-446655440000"));
        //nullnak kell lennie, mert nincs ilyen id-vel student
        assertNull(notFoundStudent);
    }

    @Test
    void getStudentByIdFoundTest() {
        //azt az id-t adtam meg, amit a teszt adatként hozzáadott student-nek adtam
        Student foundStudent = underTest.getStudentById(UUID.fromString("fd16a7a5-3e56-4e96-bc14-8f2c1e54e76d"));
        //Balogh Anna-nak kell lennie
        assertEquals("Balogh Anna", foundStudent.getName());
    }

    @Test
    void saveSuccessTest() {
        underTest.save(new Student(UUID.randomUUID(), "Buzás Ádám", "adam2000buzas@gmail.com"));
        List<Student> list = underTest.getAllStudent();
        assertEquals("Buzás Ádám", list.get(2).getName());
    }

    @Test
    void saveUnsuccessTest() {
        Student nullStudent = underTest.save(new Student(UUID.randomUUID(), "Buzás Ádám", "adam2000buzasgmail.com"));
        //invalid email, ezért nem illeszt be semmit
        assertNull(nullStudent);
        //adatbázisba továbbra is kettő elem van
        assertEquals(2, underTest.getAllStudent().size());
    }

    @Test
    void deleteByIdTest() {
        //Balogh Anna kitörlődött
        underTest.deleteById(UUID.fromString("fd16a7a5-3e56-4e96-bc14-8f2c1e54e76d"));
        assertEquals(1, underTest.getAllStudent().size());
        assertEquals("Magyar Balazs", underTest.getAllStudent().get(0).getName());
    }

    @Test
    void isValidEmailTest() {
        //nincsen kukac jel false-t kell visszaadjon
        assertFalse(underTest.isValidEmail("adam2000buzasgmail.com"));
        //elfogadja a magyar domain-el rendelkező emailt is
        assertTrue(underTest.isValidEmail("adam2000buzas@citromail.hu"));
        //értelmetlen domain-t nem fogadja el
        assertFalse(underTest.isValidEmail("adam2000buzas@citromail.askj"));
        //nincsen meg az email eleje
        assertFalse(underTest.isValidEmail("@gmail.com"));
        //normális email elkell fogadja
        assertTrue(underTest.isValidEmail("adam2000buzas@gmail.com"));
        //üres email
        assertFalse(underTest.isValidEmail(""));
        //null az email
        assertFalse(underTest.isValidEmail(null));
        // speciális karakter
        assertFalse(underTest.isValidEmail("adam-2000bu zas@gmail.com"));
    }
}