package com.inerview.main;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inerview.main.controller.StudentController;
import com.inerview.main.model.Student;
import com.inerview.main.repository.StudentRepository;
import com.inerview.main.services.StudentService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentRepository studentRepository;
    private static HttpHeaders headers;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeAll
    public static void init() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }
    private String createURLWithPort() {
        return "http://localhost:" + port + "/profile/students";
    }

    @Test
    public void testOrdersList() {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<List<Student>> response = restTemplate.exchange(
                createURLWithPort(), HttpMethod.GET, entity, new ParameterizedTypeReference<List<Student>>(){});
        List<Student> studentList = response.getBody();
        assert studentList != null;
        assertEquals(response.getStatusCodeValue(), 200);
        assertEquals(studentList.size(), studentService.getAllStudent().size());
        assertEquals(studentList.size(), ((Collection<?>) studentRepository.findAll()).size());
    }
}
