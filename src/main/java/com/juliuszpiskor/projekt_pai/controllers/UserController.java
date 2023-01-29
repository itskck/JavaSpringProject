/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.juliuszpiskor.projekt_pai.controllers;
import com.juliuszpiskor.projekt_pai.entities.Student;
import com.juliuszpiskor.projekt_pai.entities.StudentRepository;
import com.juliuszpiskor.projekt_pai.entities.User;
import com.juliuszpiskor.projekt_pai.entities.UserRepository;
import com.juliuszpiskor.projekt_pai.services.StudentService;
import java.security.Principal;
import java.util.List;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 *
 * @author juliu
 */
@Controller
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private UserRepository userRepository;
    
     @Autowired
    private StudentService studentService;
   

    @GetMapping("/login")
    public String loginPage() {
        //zwrócenie nazwy widoku logowania - login.html
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model m) {
        //dodanie do modelu nowego użytkownika
        m.addAttribute("user", new User());
        //zwrócenie nazwy widoku rejestracji - register.html
        return "register";
    }

    @PostMapping("/register")
    public String registerPagePOST(@Valid User user, BindingResult binding) {
        if (binding.hasErrors()) {
            return "register"; //powrót do rejestracji
        }
        if(userRepository.countByLogin(user.getLogin())==1){
            binding.rejectValue("login", "error.login", "Użytkownik z takim ID już istnieje");
            return "register";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        //przekierowanie do adresu url: /login
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String profilePage(Model m, Principal principal) {
        //dodanie do modelu aktualnie zalogowanego użytkownika:
        System.out.println("profile");
        m.addAttribute("user", userRepository.findUserByLogin(principal.getName()));
        //zwrócenie nazwy widoku profilu użytkownika - profile.html
        return "profile";
    }
    
    @GetMapping("/delete")
    @Transactional
    public String deleteAccount(Model m, Principal principal) {
        //dodanie do modelu aktualnie zalogowanego użytkownika:
        userRepository.deleteByLogin(principal.getName());
        
        //zwrócenie nazwy widoku profilu użytkownika - profile.html
        return "redirect:/logout";
    }
    
    @GetMapping("/edit")
    public String editPage(Model m, Principal principal) {
        //dodanie do modelu aktualnie zalogowanego użytkownika:
        m.addAttribute("user", userRepository.findUserByLogin(principal.getName()));
        //zwrócenie nazwy widoku profilu użytkownika - profile.html
        return "edit";
    }
    
    @GetMapping("/student-page/{id}")
    public String studentPage(Model m, Principal principal,@PathVariable("id") int id){
        var user = userRepository.findUserByLogin(principal.getName());
        if(user.getIsStudent()) return "redirect:/profile";
         m.addAttribute("user",user );
         m.addAttribute("studentId",id);
         return "student_page";
    }
    
    @PostMapping("/edit")
    public String editPagePOST(Model m,@Valid User user, BindingResult binding, Principal principal) {
        boolean i=false;
        if (binding.hasErrors()) {
            return "edit"; //powrót do edycji
        }
        User user1 = userRepository.findUserByLogin(principal.getName());
        user1.setName(user.getName());
        user1.setSurname(user.getSurname());
        if(!user.getPassword().isEmpty())
        {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user1.setPassword(user.getPassword());
        }
        if(!user1.getLogin().equals(user.getLogin())&& (userRepository.countByLogin(user.getLogin())==1))
        {  
            binding.rejectValue("login", "error.login", "Użytkownik z takim ID już istnieje");
            return "edit";
        }
        else {
            if(!user1.getLogin().equals(user.getLogin()))
                i=true;
            user1.setLogin(user.getLogin());
        }
        userRepository.save(user1);
        if(i){
            System.out.println("logout");
            return "redirect:/logout";}
        else{
            System.out.println("profile");
            return "redirect:/profile";}
    }
    
    @GetMapping("/users")
    public String allUsersPage(Model m)
    {
        m.addAttribute("list", userRepository.findAll());
        return "users";
    }
    //definicja metody, która zwróci do widoku users.html listę
    //użytkowników z bd
    

}