/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.juliuszpiskor.projekt_pai.services;

import com.juliuszpiskor.projekt_pai.entities.User;
import com.juliuszpiskor.projekt_pai.entities.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    public void addUser(User user){
        userRepository.save(user);
    }
    
    public User findUserById(int id){
      return userRepository.findUserById(id);
    }

    public int countByLogin(String login){
        return userRepository.countByLogin(login);
    }
    
    public User findUserByLogin(String login){
        return userRepository.findUserByLogin(login);
    }
    
    public void deleteByLogin(String login){
        userRepository.deleteByLogin(login);
    }    
}
