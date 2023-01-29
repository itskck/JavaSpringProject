package com.juliuszpiskor.projekt_pai.entities;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {

  
    public Student findStudentById(Long id);

}