package com.juliuszpiskor.projekt_pai.services;

import com.juliuszpiskor.projekt_pai.entities.Student;
import com.juliuszpiskor.projekt_pai.entities.StudentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getStudentList() {
        return (List<Student>) studentRepository.findAll();
    }

    public Student getStudent(Long id) {
        return studentRepository.findStudentById(id);
    }

    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public void addGrade(Student student, double mark) {
        if (studentRepository.existsById(student.getId())) {
             List<Double> marks = student.getGrades();
             marks.add(mark);
             student.setGrades(marks);
              studentRepository.save(student);
        }
    }

    public void editStudent(Student student) {
        if (studentRepository.existsById(student.getId())) {
            student.setName(student.getName());
            student.setSurname(student.getSurname());
            student.setGrades(student.getGrades());
            studentRepository.save(student);
            System.out.println(student.getGrades());
        }
    }
}
