package org.siit.springworkshop.service.hello;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("test")
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
