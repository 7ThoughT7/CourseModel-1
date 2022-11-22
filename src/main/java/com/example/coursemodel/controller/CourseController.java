package com.example.coursemodel.controller;

import com.example.coursemodel.Course;
import com.example.coursemodel.Professor;
import com.example.coursemodel.repos.CourseRepo;
import com.example.coursemodel.repos.PassingCourseRepo;
import com.example.coursemodel.repos.ProfessorRepo;
import com.example.coursemodel.repos.StudentRepo;
import com.example.coursemodel.service.CourseService;
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
public class CourseController {

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private ProfessorRepo professorRepo;

    @Autowired
    private PassingCourseRepo passingCourseRepo;

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @GetMapping("/add/addCourses")
    public String courses(Map<String, Object> model) {

        Iterable<Course> courses = courseRepo.findAll();
        model.put("courses", courses);

        return "add/addCourses";
    }

    @PostMapping("/add/addCourses")
    public String addCourse(@RequestParam String title, @RequestParam int number,
                            @RequestParam float price) {

        Course course = new Course(title, number, price);
        courseRepo.save(course);

        return "redirect:/add/addCourses";
    }

    @GetMapping("/list/listCourses")
    public String listStudents(Model model) {
        model.addAttribute("courses", courseRepo.findAll());
        return "/list/listCourses";
    }

    @GetMapping("edit/courseEditStudent/{courseId}")
    public String courseEditStudent(Model model, @PathVariable Integer courseId) {
        model.addAttribute("course", courseService.getById(courseId));
        model.addAttribute("students", studentRepo.findAll());
        return "edit/courseEditStudent";
    }

    @PostMapping("/edit/courseEditStudent/{courseId}")
    public String courseSaveStudent(@PathVariable Integer courseId,
                              @RequestParam Integer studentId
    ) {
        courseService.courseAddStudent(studentId, courseId);

        return "redirect:/edit/courseEditStudent/{courseId}";
    }

    @GetMapping("/delete/courseDeleteStudent/{courseId}")
    public String courseDeleteFormSt(Model model, @PathVariable Integer courseId) {
        model.addAttribute("course", courseService.getById(courseId));
        model.addAttribute("students", studentRepo.findAll());
        return "delete/courseDeleteStudent";
    }

    @PostMapping("/delete/courseDeleteStudent/{courseId}")
    public String courseDeleteStudent(@PathVariable Integer courseId,
                                @RequestParam Integer studentId
    ) {
        courseService.courseDeleteStudent(studentId, courseId);

        return "redirect:/delete/courseDeleteStudent/{courseId}";
    }

    @GetMapping("edit/courseEditProfessor/{courseId}")
    public String courseEditForm(Model model, @PathVariable Integer courseId) {
        model.addAttribute("course", courseService.getById(courseId));
        model.addAttribute("professors", professorRepo.findAll());
        return "edit/courseEditProfessor";
    }

    @PostMapping("/edit/courseEditProfessor/{courseId}")
    public String courseSaveProfessor(@PathVariable Integer courseId,
                             @RequestParam Integer professorId
    ) {
        Course course = courseService.getById(courseId);
        Professor professor = professorRepo.getById(professorId);
        course.setProfessors(professor);
        professor.setCourses(course);
        courseRepo.save(course);
        professorRepo.save(professor);

        return "redirect:/edit/courseEditProfessor/{courseId}";
    }

    @GetMapping("/delete/courseDeleteProfessor/{courseId}")
    public String courseDeleteFormPr(Model model, @PathVariable Integer courseId) {
        model.addAttribute("course", courseService.getById(courseId));
        model.addAttribute("professors", professorRepo.findAll());
        return "delete/courseDeleteProfessor";
    }

    @PostMapping("/delete/courseDeleteProfessor/{courseId}")
    public String courseDeleteProfessor(@PathVariable Integer courseId,
                                  @RequestParam Integer professorId
    ) {
        Professor professor = professorRepo.getById(professorId);
        Course course = courseService.getById(courseId);
        professor.getCourses().remove(course);
        course.getProfessors().remove(professor);
        professorRepo.save(professor);
        courseRepo.save(course);

        return "redirect:/delete/courseDeleteProfessor/{courseId}";
    }
}
