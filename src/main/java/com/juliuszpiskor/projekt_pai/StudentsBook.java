package com.juliuszpiskor.projekt_pai;



import com.juliuszpiskor.projekt_pai.entities.Student;
import com.juliuszpiskor.projekt_pai.entities.StudentRepository;
import com.juliuszpiskor.projekt_pai.entities.User;
import com.juliuszpiskor.projekt_pai.entities.UserRepository;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableAutoConfiguration
@SpringBootApplication
public class StudentsBook {
  
      @Autowired
    private StudentRepository studentRepository;
      
      @Autowired
      private UserRepository userRepository;
      
      @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(StudentsBook.class, args);
        
    }

    
    @PostConstruct
    public void init() {
        Student s1 = new Student();
        s1.setName("Imioniusz");
        s1.setSurname("Nazwiskowski");
        s1.setGrades(List.of(4.0,5.0,3.0,5.0));
        studentRepository.save(s1);
        
         Student s2 = new Student();
        s2.setName("Student");
        s2.setSurname("Studentowski");
        s2.setGrades(List.of(4.0,1.0,2.0,5.0));
        studentRepository.save(s2);
        
        User u1 = new User();
        u1.setIsStudent(true);
        u1.setLogin("student");
        u1.setPassword(passwordEncoder.encode("student"));
        u1.setName("Student");
        u1.setSurname("Studentowski");
        userRepository.save(u1);
        
          User u2 = new User();
        u2.setIsStudent(false);
        u2.setLogin("nauczyciel");
        u2.setPassword(passwordEncoder.encode("nauczyciel"));
        u2.setName("Nauczyciel");
        u2.setSurname("Nauczycielowski");
        userRepository.save(u2);
    }
}
