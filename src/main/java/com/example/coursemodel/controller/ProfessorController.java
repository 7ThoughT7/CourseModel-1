package com.example.coursemodel.controller;

import com.example.coursemodel.Course;
import com.example.coursemodel.Professor;
import com.example.coursemodel.repos.CourseRepo;
import com.example.coursemodel.repos.PassingCourseRepo;
import com.example.coursemodel.repos.ProfessorRepo;
import com.example.coursemodel.repos.StudentRepo;
import com.example.coursemodel.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class ProfessorController {

    @Autowired
    private ProfessorRepo professorRepo;

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private PassingCourseRepo passingCourseRepo;

    @Autowired
    private CourseService courseService;

    @GetMapping("/add/addProfessors")
    public String professors(Map<String, Object> model) {

        Iterable<Professor> professors = professorRepo.findAll();
        model.put("professors", professors);

        return "add/addProfessors";
    }

    @PostMapping("/add/addProfessors")
    public String addProfessor(@RequestParam String name, @RequestParam String address,
                               @RequestParam String telephone, @RequestParam float payment) {

        Professor professor = new Professor(name, address, telephone, payment);
        professorRepo.save(professor);

        return "redirect:/add/addProfessors";
    }

    @GetMapping("/list/listProfessors")
    public String listProfessors(Model model) {
        model.addAttribute("professors", professorRepo.findAll());
        return "/list/listProfessors";
    }

    @GetMapping("edit/professorEdit/{professorId}")
    public String professorEditCourse(Model model, @PathVariable Integer professorId) {
        model.addAttribute("professor", professorRepo.getById(professorId));
        model.addAttribute("courses", courseRepo.findAll());
        return "edit/professorEdit";
    }

    @PostMapping("/edit/professorEdit/{professorId}")
    public String professorSave(@PathVariable Integer professorId,
                              @RequestParam Integer courseId
    ) {
        Professor professor = professorRepo.getById(professorId);
        Course course = courseService.getById(courseId);
        professor.setCourses(course);
        course.setProfessors(professor);
        professorRepo.save(professor);
        courseRepo.save(course);

        return "redirect:/edit/professorEdit/{professorId}";
    }

    @GetMapping("/delete/professorDelete/{professorId}")
    public String professorDeleteForm(Model model, @PathVariable Integer professorId) {
        model.addAttribute("professor", professorRepo.getById(professorId));
        model.addAttribute("courses", courseRepo.findAll());
        return "delete/professorDelete";
    }

    @PostMapping("/delete/professorDelete/{professorId}")
    public String professorDelete(@PathVariable Integer professorId,
                               @RequestParam Integer courseId
    ) {
        Professor professor = professorRepo.getById(professorId);
        Course course = courseService.getById(courseId);
        professor.getCourses().remove(course);
        course.getProfessors().remove(professor);
        professorRepo.save(professor);
        courseRepo.save(course);

        return "redirect:/delete/professorDelete/{professorId}";
    }

    @GetMapping("/evaluateWork/{professorId}")
    public String evaluateWork(Model model,
                               @PathVariable Integer professorId
    ) {
        model.addAttribute("professor", professorRepo.getById(professorId));
        model.addAttribute("courses", courseRepo.findAll());

        return "/evaluateWork";
    }

}
