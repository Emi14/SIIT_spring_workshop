package org.siit.springworkshop.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressDto {

    private Long id;

    @NotEmpty(message = "Street cannot be null!")
    private String street;

    @NotEmpty(message = "Street number cannot be null!")
    private Integer streetNumber;

    private String zipCode;

    @NotEmpty(message = "City cannot be null!")
    private String city;

    @NotEmpty(message = "Country cannot be null!")
    private String country;
}
