/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.juliuszpiskor.projekt_pai.services;

import com.juliuszpiskor.projekt_pai.entities.Comment;
import com.juliuszpiskor.projekt_pai.entities.CommentRepository;
import com.juliuszpiskor.projekt_pai.entities.Student;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getStudentComments(int id) {
        return (List<Comment>) commentRepository.findAllByStudentId(id);
    }

    public void addComment(Comment student) {
        commentRepository.save(student);
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    public void editComment(Comment comment) {
        if (commentRepository.existsById(Long.valueOf(comment.getId()))) {
            comment.setContent(comment.getContent());
            commentRepository.save(comment);
        }
    }
}
