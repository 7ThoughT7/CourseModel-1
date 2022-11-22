package com.example.coursemodel;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String address;
    private String telephone;
    private Float payment;

    @ManyToMany(mappedBy = "professors")
    Set<Course> courses;

    public Professor() {

    }

    public Professor(String name, String address, String telephone, Float payment) {
        this.name = name;
        this.address = address;
        this.telephone = telephone;
        this.payment = payment;
    }

    public Iterable<Course> signUpCourse(Iterable<Course> allCourses) {
        Set<Course> signUpCourse = new LinkedHashSet<>();
        if (allCourses != null) {
            for (Course c : allCourses) {
                if (!courses.contains(c)) {
                    signUpCourse.add(c);
                }
            }
        }
        return signUpCourse;
    }

    public Set<Course> getListOfListedCourses() {
        return getCourses();
    }
    public Integer totalNumberOfStudents() {

        int result = 0;
        for (Course c : courses) {
            result += c.getStudents().size();
        }
        return result;
    }

    public float averagePerformance() {

        int amountSt = 0;
        float result = 0;
        for (Course c : courses) {
            result += c.averagePerformanceOnCourse();
            amountSt += c.amountStudents();
        }
        if (amountSt != 0 && result >= 0) {
            return (result / amountSt);
        } else {
            return 0.0f;
        }
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Course course) {
        courses.add(course);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Float getPayment() {
        return payment;
    }

    public void setPayment(Float payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return name;
    }
}
