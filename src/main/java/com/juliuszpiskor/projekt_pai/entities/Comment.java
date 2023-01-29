/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.juliuszpiskor.projekt_pai.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author juliu
 */
@Entity
@Table(name = "Comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @NotNull
    private String content;
    
    @NotNull
    private Integer studentId;
    
    
    public Comment() {
    }

    public Comment(String content, int studentId) {
        this.content = content;
        this.studentId = studentId;
    }
    //metody get i set

    public Integer getId() {
        return id;
    }

    public void setId(Integer userid) {
        this.id = userid;
    }
    
    public String getContent(){
        return content;
    }
     public void setContent(String content){
      this.content = content;
     
    }

    public int getStudentId() {
        return studentId;
    }

    public void setstudentId(int studentId) {
        this.studentId = studentId;
    }   

}
