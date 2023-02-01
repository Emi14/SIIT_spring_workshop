package org.siit.springworkshop.service;

import org.siit.springworkshop.exception.AgeException;
import org.springframework.stereotype.Service;

@Service
public class HelloWorldService {

    public String greet(String firstName, String lastName, Integer age) throws AgeException {
        if(age>99)
        {
            throw new AgeException("Invalid age");
        }
        return String.format("Hello World, %s %s. You are %s", firstName, lastName, age);
    }

}
