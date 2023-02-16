package org.siit.springworkshop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.siit.springworkshop.enums.Gender;

import java.util.List;
//POJO

@Data
@NoArgsConstructor
public class StudentDto {
    private Long id;

    @NotEmpty(message = "First name cannot be null!")
    private String firstName;

    @NotEmpty(message = "Last name cannot be null!")
    private String lastName;

    @Min(value = 18, message = "Age must be greater than 18!")
    private int age;

    private Gender gender;

    @NotEmpty(message = "Email cannot be null!")
    @Email(message = "Email is invalid")
    private String email;

    @NotEmpty(message = "At least one address should be sent")
    private List<AddressDto> addresses;
}
