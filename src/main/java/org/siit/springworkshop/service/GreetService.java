package org.siit.springworkshop.service;

import org.siit.springworkshop.exception.AgeException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
//@Qualifier("serviceImplementation")
public class GreetService implements IService{
    @Override
    public String getCurrentTime() {
        return " Year - 2000.";
    }

    @Override
    public String greet(String firstName, String lastName, Integer age) {
        return String.format("Hello World, %s %s. You are %s", firstName, lastName, age);
    }
}
