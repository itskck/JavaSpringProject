package com.juliuszpiskor.projekt_pai.controllers;

import com.juliuszpiskor.projekt_pai.NotFoundException;
import com.juliuszpiskor.projekt_pai.entities.Comment;
import com.juliuszpiskor.projekt_pai.entities.CommentRepository;
import com.juliuszpiskor.projekt_pai.entities.Student;
import com.juliuszpiskor.projekt_pai.entities.StudentRepository;
import com.juliuszpiskor.projekt_pai.services.CommentService;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author juliu
 */
@RestController
public class CommentController {
      
    @Autowired
    private CommentService commentService;
    
    @Autowired
    private CommentRepository commentRepository;
    
    @GetMapping("/comments/{id}")
    public List<Comment> getStudentComments(@PathVariable("id") int id) {
        return commentService.getStudentComments(id);
    }
    
    @PostMapping("/comment/add/{id}")
    public void addStudent(@RequestBody Comment comment, @PathVariable("id") int id) {
        commentService.addComment(comment);
    }
    
    @GetMapping("/comment/delete/{id}")
    @Transactional
    public void deleteComment(@PathVariable("id") int id) {
        //dodanie do modelu aktualnie zalogowanego użytkownika:
        commentService.deleteComment(Long.valueOf(id));     
    }
    
    @PostMapping("/comment/{id}")
    public List<Comment> editComment(@PathVariable("id") Long id, @RequestBody Comment comment) throws NotFoundException {
         if(commentRepository.existsById(id)){
            commentService.editComment(comment);
            return commentService.getStudentComments(comment.getStudentId());
        } else {
            throw new NotFoundException();
        }  
    }
    
}
