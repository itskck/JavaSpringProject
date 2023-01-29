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
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull
    @Pattern(regexp = "[a-zA-ZŁłĆćŻżŹźŃńĄąĘę]{2,30}",message="Wpisz conajmniej 2 litery")
    private String name;
    @NotNull
    @Pattern(regexp = "[a-zA-ZŁłĆćŻżŹźŃńĄąĘę]{2,30}",message="Wpisz conajmniej 2 litery")
    private String surname;
    @NotNull
    @Column(unique = true)
    @Pattern(regexp = "[a-zA-Z0-9]{2,10}",message="Użytkownik o takim ID już istnieje")
    @Size(min = 2, message= "Wpisz conajmniej 2 znaki")
    private String login;
    //@NotNull
    //@Pattern(regexp = "[a-zA-Z0-9]{3,15}",message="Podaj inne hasło")
    @Size(min = 4, message= "Wpisz conajmniej 4 znaki")
    private String password;
    
    @NotNull
    private boolean isStudent;

    public User() {
    }

    public User(String name, String surname, String login,
            String password, boolean isStudent) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.isStudent = isStudent;
    }
    //metody get i set

    public Integer getId() {
        return id;
    }

    public void setId(Integer userid) {
        this.id = userid;
    }
    
    public boolean getIsStudent(){
        return isStudent;
    }
     public void setIsStudent(boolean isStudent){
      this.isStudent = isStudent;
     
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
