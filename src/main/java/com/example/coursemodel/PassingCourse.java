package com.example.coursemodel;

import javax.persistence.*;
import java.util.Set;

import static java.lang.Math.round;

@Entity
public class PassingCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "studentId")
    private Student students;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "courseId")
    private Course courses;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Grade> grades;

    public PassingCourse() {
    }

    public PassingCourse(Student student, Course course) {
        this.students = student;
        this.courses = course;
    }

    public float getCurrentAverageScore() {

        float result = 0;
        for (Grade g : grades) {
            if (g.getGrade() != null) {
                result += g.getGrade();
            }
        }
        return result / grades.size();
    }

    public Integer getFinalGrade() {

        return round(getCurrentAverageScore());
    }
    public Set<Grade> getGrades() {
        return grades;
    }

    public void setGrades(Grade grade) {
        grades.add(grade);
    }

    public Integer getPassingCourseId() {
        return Id;
    }

    public Student getStudents() {
        return students;
    }

    public void setStudents(Student students) {
        this.students = students;
    }

    public Course getCourses() {
        return courses;
    }

    public void setCourses(Course course) {
        this.courses = course;
    }
}
