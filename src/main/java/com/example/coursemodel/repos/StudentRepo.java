package com.example.coursemodel.repos;

import com.example.coursemodel.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepo extends CrudRepository<Student, Integer> {

    Student getById(Integer studentId);
}
