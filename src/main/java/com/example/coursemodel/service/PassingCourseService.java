package com.example.coursemodel.service;

import com.example.coursemodel.PassingCourse;
import com.example.coursemodel.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PassingCourseService {

    @Autowired
    private StudentService studentService;

    public PassingCourse getPassingCourse(Integer studentId, Integer courseId) {

        Student student = studentService.getById(studentId);
        Iterable<PassingCourse> passingCourses = student.getPassingCourses();
        for (PassingCourse p : passingCourses) {
            if (Objects.equals(p.getStudents().getId(), studentId)
                    && Objects.equals(p.getCourses().getId(), courseId)) {
                return p;
            }
        }
        return null;
    }
}
