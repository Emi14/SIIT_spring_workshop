package org.siit.springworkshop.dto;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.siit.springworkshop.entity.AddressEntity;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;
//POJO

@Data
public class StudentDto {
    private Long id;

    //not working
    @NotEmpty(message = "First name cannot be null!")
    private String firstName;

    @NotEmpty(message = "Last name cannot be null!")
    private String lastName;

    @Min(value = 18, message = "Age must be greater than 18!")
    private int age;

    @NotEmpty(message = "Email cannot be null!")
    @Email(message = "Email is invalid")
    private String email;

    private List<AddressDto> addresses;
}
