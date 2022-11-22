package com.example.coursemodel;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;
    private int number;
    private float price;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<PassingCourse> passingCourses;

    @ManyToMany
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(name = "courseId"),
            inverseJoinColumns = @JoinColumn(name = "studentId"))
    private Set<Student> students;

    @ManyToMany
    @JoinTable(
            name = "course_professor",
            joinColumns = @JoinColumn(name = "courseId"),
            inverseJoinColumns = @JoinColumn(name = "professorId"))
    private Set<Professor> professors;

    public Course() {
    }

    public Course(String title, int number, float price) {
        this.title = title;
        this.number = number;
        this.price = price;
    }

    public Iterable<Professor> signUpProfessor(Iterable<Professor> allProfessor) {
        Set<Professor> signUpProfessor = new LinkedHashSet<>();
        if (allProfessor != null) {
            for (Professor c : allProfessor) {
                if (!professors.contains(c)) {
                    signUpProfessor.add(c);
                }
            }
        }
        return signUpProfessor;
    }

    public Set<Professor> getListOfListedProfessors() {
        return getProfessors();
    }
    public Iterable<Student> signUpStudent(Iterable<Student> allStudent) {
        Set<Student> signUpStudent = new LinkedHashSet<>();
        if (allStudent != null) {
            for (Student c : allStudent) {
                if (!students.contains(c)) {
                    signUpStudent.add(c);
                }
            }
        }
        return signUpStudent;
    }

    public Set<Student> getListOfListedStudents() {
        return getStudents();
    }
    public void addStudent(Student student, Course course, PassingCourse passingCourse) {
        student.setPassingCourses(passingCourse);
        course.setPassingCourse(passingCourse);
        student.setCourses(course);
        course.setStudents(student);
    }

    public void deleteStudent(Student student, Course course, PassingCourse passingCourse) {
        student.getPassingCourses().remove(passingCourse);
        course.getPassingCourse().remove(passingCourse);
        student.getCourses().remove(course);
        course.getStudents().remove(student);
    }

    public float averagePerformanceOnCourse() {

        float result = 0;
        for (PassingCourse p : passingCourses) {
            result += p.getCurrentAverageScore();
        }
        return result;
    }

    public Integer amountStudents() {
        return students.size();
    }

    public Set<PassingCourse> getPassingCourse() {
        return passingCourses;
    }

    public void setPassingCourse(PassingCourse passingCourse) {
        passingCourses.add(passingCourse);
    }

    public Set<Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(Professor professor) {
        professors.add(professor);
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Student student) {
        students.add(student);
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return title;
    }
}
