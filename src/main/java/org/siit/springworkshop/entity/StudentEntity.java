package org.siit.springworkshop.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

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

//    @OneToMany
//    @JoinColumn(name = "student_id")
    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<AddressEntity> addresses;


}
