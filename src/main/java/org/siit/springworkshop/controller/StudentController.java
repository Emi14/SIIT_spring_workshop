package org.siit.springworkshop.controller;

import org.siit.springworkshop.dto.StudentDto;
import org.siit.springworkshop.service.StudentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/student")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/save")
    public Long postMessage(@Valid @RequestBody StudentDto studentDto) {
        return studentService.saveStudent(studentDto);
    }

}
