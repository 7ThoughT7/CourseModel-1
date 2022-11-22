package com.example.coursemodel;

import javax.persistence.*;

@Entity
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "passingCourseId")
    private PassingCourse passingCourse;

    private Integer grade;

    public Grade() {
    }

    public Grade(PassingCourse passingCourse, Integer grade) {
        this.passingCourse = passingCourse;
        this.grade = grade;
    }

    public Integer getId() {
        return id;
    }

    public PassingCourse getPassingCourse() {
        return passingCourse;
    }

    public void setPassingCourse(PassingCourse passingCourse) {
        this.passingCourse = passingCourse;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}
