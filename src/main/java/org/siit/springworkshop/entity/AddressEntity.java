package org.siit.springworkshop.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "addresses")
@Data
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String street;

    private Integer streetNumber;

    private String zipCode;

    private String city;

    private String country;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", nullable = false)
    private StudentEntity student;
}
