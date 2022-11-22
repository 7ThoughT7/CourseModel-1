package com.example.coursemodel.service;

import com.example.coursemodel.Student;
import com.example.coursemodel.repos.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
@Service
public class StudentService{

    @Autowired
    private StudentRepo studentRepo;

    public Student getById(Integer studentId) {

        Iterable<Student> students = studentRepo.findAll();
        for (Student s : students) {
            if (Objects.equals(s.getId(), studentId)) {
                return s;
            }
        }
        return null;
    }

}
