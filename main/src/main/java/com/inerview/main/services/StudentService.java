package com.inerview.main.services;

import com.inerview.main.model.Student;
import com.inerview.main.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudent(){
        List<Student> list = new ArrayList<>();
        for(Student s : studentRepository.findAll()){
            list.add(s);
        }
        return list;
    }
}
