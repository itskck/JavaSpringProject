/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.juliuszpiskor.projekt_pai.entities;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author juliu
 */
public interface CommentRepository  extends CrudRepository<Comment, Long>{
    public List<Comment> findAllByStudentId(int id);
}
