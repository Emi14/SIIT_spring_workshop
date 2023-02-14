package org.siit.springworkshop.controller;

import org.siit.springworkshop.exception.DataNotFound;
import org.siit.springworkshop.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<Object> searchAddressById(@PathVariable(name = "id") String addressId) throws DataNotFound {
        Long id;
        try {
            id = Long.valueOf(addressId);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>("The given id is not a number.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(addressService.getAddressById(id), HttpStatus.OK);
    }
}
