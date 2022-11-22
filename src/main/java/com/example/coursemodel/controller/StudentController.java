package com.example.coursemodel.controller;

import com.example.coursemodel.Grade;
import com.example.coursemodel.PassingCourse;
import com.example.coursemodel.Student;
import com.example.coursemodel.repos.CourseRepo;
import com.example.coursemodel.repos.GradeRepo;
import com.example.coursemodel.repos.PassingCourseRepo;
import com.example.coursemodel.repos.StudentRepo;
import com.example.coursemodel.service.CourseService;
import com.example.coursemodel.service.PassingCourseService;
import com.example.coursemodel.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class StudentController {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private PassingCourseRepo passingCourseRepo;

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private PassingCourseService passingCourseService;

    @Autowired
    private GradeRepo gradeRepo;

    @GetMapping("/add/addStudents")
    public String students(Map<String, Object> model) {

        Iterable<Student> students = studentRepo.findAll();
        model.put("students", students);

        return "add/addStudents";
    }

    @PostMapping("/add/addStudents")
    public String addStudent(@RequestParam String name, @RequestParam String address,
                             @RequestParam String telephone, @RequestParam String email,
                             @RequestParam Integer countingNum) {

        Student student = new Student(name, address, telephone, email, countingNum);
        studentRepo.save(student);

        return "redirect:/add/addStudents";
    }

    @GetMapping("/list/listStudents")
    public String listStudents(Model model) {
        model.addAttribute("students", studentRepo.findAll());
        return "/list/listStudents";
    }

    @GetMapping("edit/studentEdit/{studentId}")
    public String userEditForm(Model model, @PathVariable Integer studentId) {
        model.addAttribute("student", studentService.getById(studentId));
        model.addAttribute("courses", courseRepo.findAll());
        return "edit/studentEdit";
    }

    @PostMapping("/edit/studentEdit/{studentId}")
    public String studentSave(@PathVariable Integer studentId,
                              @RequestParam Integer courseId
    ) {
        courseService.courseAddStudent(studentId, courseId);

        return "redirect:/edit/studentEdit/{studentId}";
    }

    @GetMapping("/delete/studentDelete/{studentId}")
    public String userDeleteForm(Model model, @PathVariable Integer studentId) {
        model.addAttribute("student", studentService.getById(studentId));
        model.addAttribute("courses", courseRepo.findAll());
        return "delete/studentDelete";
    }

    @PostMapping("/delete/studentDelete/{studentId}")
    public String studentDelete(@PathVariable Integer studentId,
                                @RequestParam Integer courseId
    ) {
        courseService.courseDeleteStudent(studentId, courseId);

        return "redirect:/delete/studentDelete/{studentId}";
    }

    @GetMapping("/studentGrades/{courseId}")
    public String studentGrades(Model model,
                                @PathVariable Integer courseId
    ) {
        model.addAttribute("course", courseService.getById(courseId));
        model.addAttribute("students", studentRepo.findAll());

        return "/studentGrades";
    }

    @PostMapping("/studentGrades/{courseId}")
    public String studentGradesPost(@PathVariable Integer courseId,
                                    @RequestParam Integer studentId,
                                    @RequestParam Integer grade
    ) {
        PassingCourse passingCourse = passingCourseService.getPassingCourse(studentId, courseId);
        if (passingCourse != null) {
            Grade grades = new Grade(passingCourse, grade);
            passingCourse.setGrades(grades);
            gradeRepo.save(grades);
            passingCourseRepo.save(passingCourse);
        }

        return "redirect:/studentGrades/{courseId}";
    }

    @GetMapping("/studyProgress/{studentId}")
    public String studyProgress(Model model,
                                @PathVariable Integer studentId
    ) {
        model.addAttribute("student", studentService.getById(studentId));

        return "/studyProgress";
    }

    @GetMapping("/grades/{studentId}/{courseId}")
    public String grades(Model model,
                         @PathVariable Integer studentId,
                         @PathVariable Integer courseId
                         ) {
        model.addAttribute("student", studentService.getById(studentId));
        model.addAttribute("course", courseService.getById(courseId));
        model.addAttribute("passingCourse", passingCourseService.getPassingCourse(studentId, courseId));

        return "/grades";
    }
}
