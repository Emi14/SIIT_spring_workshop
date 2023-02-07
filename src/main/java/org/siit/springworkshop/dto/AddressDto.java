package org.siit.springworkshop.dto;

import lombok.Data;

@Data
public class AddressDto {

    private Long id;

    private String street;

    private Integer streetNumber;

    private String zipCode;

    private String city;

    private String country;
}
