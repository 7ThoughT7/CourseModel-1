package com.example.coursemodel.service;

import com.example.coursemodel.Course;
import com.example.coursemodel.PassingCourse;
import com.example.coursemodel.Student;
import com.example.coursemodel.repos.CourseRepo;
import com.example.coursemodel.repos.PassingCourseRepo;
import com.example.coursemodel.repos.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CourseService {

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private PassingCourseRepo passingCourseRepo;

    @Autowired
    private StudentService studentService;

    @Autowired
    private PassingCourseService passingCourseService;

    public Course getById(Integer courseId) {

        Iterable<Course> courses = courseRepo.findAll();
        for (Course s : courses) {
            if (Objects.equals(s.getId(), courseId)) {
                return s;
            }
        }
        return null;
    }

    public void courseDeleteStudent(Integer studentId, Integer courseId) {
        Student student = studentService.getById(studentId);
        Course course = getById(courseId);
        PassingCourse passingCourse = passingCourseService.getPassingCourse(studentId, courseId);
        course.deleteStudent(student, course, passingCourse);
        if (passingCourse != null) {
            passingCourseRepo.delete(passingCourse);
        }
        studentRepo.save(student);
        courseRepo.save(course);
    }

    public void courseAddStudent(Integer studentId, Integer courseId) {
        Course course = getById(courseId);
        Student student = studentService.getById(studentId);
        PassingCourse passingCourse = new PassingCourse(student, course);
        course.addStudent(student, course, passingCourse);
        passingCourseRepo.save(passingCourse);
        courseRepo.save(course);
        studentRepo.save(student);
    }
}
