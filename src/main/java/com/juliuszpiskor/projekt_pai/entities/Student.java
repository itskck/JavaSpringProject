package com.juliuszpiskor.projekt_pai.entities;

import com.fasterxml.jackson.annotation.JsonView;
import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "student")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @JsonView
    @Column
    @Getter @Setter
    String name;

    @JsonView
    @Column
    @Getter @Setter
    String surname;

    @JsonView
    @Column
    @ElementCollection(targetClass=Double.class)
    @Getter @Setter
    List<Double> grades;
}