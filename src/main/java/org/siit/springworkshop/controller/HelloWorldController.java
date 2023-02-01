package org.siit.springworkshop.controller;

import org.siit.springworkshop.dto.StudentDto;
import org.siit.springworkshop.exception.AgeException;
import org.siit.springworkshop.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello-world")
public class HelloWorldController {

    private final HelloWorldService helloWorldService;

    public HelloWorldController(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    @GetMapping("/greet")
//    @RequestMapping(path = "/greet", method = {RequestMethod.GET})
    public String helloWorld(@RequestParam(required = false, defaultValue = "John", name = "firstName") String firstName,
                             @RequestParam(required = false, defaultValue = "Doe", name = "lastName") String lastName) {
        return String.format("Hello World, %s %s.", firstName, lastName);
    }

    @GetMapping("/greet/{firstName}/{lastName}")
    public String helloWorldPathParams(@PathVariable(name = "firstName") String firstName,
                                       @PathVariable(name = "lastName") String lastName,
                                       @RequestParam(required = false, defaultValue = "6", name = "age") Integer age) throws AgeException {
        return helloWorldService.greet(firstName, lastName, age);
    }

    @PostMapping("/greet")
    public String postMessage(@RequestBody StudentDto studentDto) {
        return String.format("Hello World, %s %s. You are %s", studentDto.getFirstName(), studentDto.getLastName(), studentDto.getAge());
    }

//    @Autowired
//    public void setHelloWorldService(HelloWorldService helloWorldService) {
//        this.helloWorldService = helloWorldService;
//    }
}
