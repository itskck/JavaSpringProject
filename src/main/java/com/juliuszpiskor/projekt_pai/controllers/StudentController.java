package com.juliuszpiskor.projekt_pai.controllers;

import com.juliuszpiskor.projekt_pai.NotFoundException;
import com.juliuszpiskor.projekt_pai.entities.Student;
import com.juliuszpiskor.projekt_pai.entities.StudentRepository;
import com.juliuszpiskor.projekt_pai.services.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    @ResponseBody
    public List<Student> getAll() {
        return studentService.getStudentList();
    }

    @GetMapping("/student/{id}")
    public Student getStudent(@PathVariable("id") Long id) {
        return studentService.getStudent(id);
    }

    @PostMapping("/add")
    public void addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
    }

    @PostMapping("student/{id}/{grade}")
    public void addGrade(@PathVariable("id") Long id, @PathVariable("grade") Double grade) throws NotFoundException {
        if (studentRepository.existsById(id)) {
            studentService.addGrade(studentService.getStudent(id), grade);
        } else {
            throw new NotFoundException();
        }

    }

    @DeleteMapping("/student/{id}")
    public void deleteStudent(@PathVariable("id") Long id) throws NotFoundException {
        if (studentRepository.existsById(id)) {
            studentService.deleteStudent(id);
        } else {
            throw new NotFoundException();
        }

    }

    @PutMapping("/student/{id}")
    public void editStudent(@PathVariable("id") Long id, @RequestBody Student student) throws NotFoundException {
        if (studentRepository.existsById(id)) {
            System.out.println(student.getGrades());
            studentService.editStudent(student);
          
        } else {
            throw new NotFoundException();
        }
    }

}
