package org.siit.springworkshop.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "students")
@Data
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    private int age;

    private String email;

}
