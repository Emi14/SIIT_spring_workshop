package org.siit.springworkshop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
//POJO

@Data
public class StudentDto {
    private Long id;

    @NotEmpty(message = "First name cannot be null!")
    private String firstName;

    @NotEmpty(message = "Last name cannot be null!")
    private String lastName;

    @Min(value = 18, message = "Age must be greater than 18!")
    private int age;

    @NotEmpty(message = "Email cannot be null!")
    @Email(message = "Email is invalid")
    private String email;
}
