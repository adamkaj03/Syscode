package com.inerview.main.services;

import com.inerview.main.model.Student;
import com.inerview.main.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
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

    public Student getStudentById(UUID id){
        Optional<Student> studentOptional = studentRepository.findById(id);

        //ha van ilyen elem, akkor azt a student-et adja vissza
        //különben null-t
        return studentOptional.orElse(null);
    }

    public Student save(Student student){
        if(isValidEmail(student.getEmail())){
            studentRepository.save(student);
            return student;
        }
        else{
            return null;
        }
    }


    public void deleteById(UUID id){
        studentRepository.deleteById(id);
    }

    public boolean isValidEmail(String email){
        return EmailValidator.getInstance().isValid(email);
    }
}
