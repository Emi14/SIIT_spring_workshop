package org.siit.springworkshop.controller;

import jakarta.validation.Valid;
import org.siit.springworkshop.dto.StudentDto;
import org.siit.springworkshop.exception.DataNotFound;
import org.siit.springworkshop.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/save")
    public Long postMessage(@Valid @RequestBody StudentDto studentDto,
                            @RequestHeader(name = "debugHeaderThrowExceptionOnSave", defaultValue = "false") boolean throwExceptionOnSave) {
//        return studentService.saveStudent(studentDto, throwExceptionOnSave);
        return studentService.saveStudentWithCascade(studentDto);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<Object> searchStudentById(@PathVariable(name = "id") String studentId) throws DataNotFound {
        Long id;
        try {
            id = Long.valueOf(studentId);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>("The given id is not a number.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(studentService.getStudentById(id), HttpStatus.OK);
    }

    @GetMapping("/search")
    public StudentDto searchStudentByFirstNameAndAge(@RequestParam(name = "firstName") String firstName,
                                                     @RequestParam(name = "age") Integer age) throws DataNotFound {
        return studentService.getStudentByFirstNameAndAge(firstName, age);
//        return studentService.getStudentByFirstNameContainingAndAgeGreaterThan(firstName, age);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteStudentById(@PathVariable(name = "id") String studentId,
                                                    @RequestHeader(name = "debugHeaderThrowExceptionOnSave", defaultValue = "false") boolean throwExceptionOnSave) throws DataNotFound {
        Long id;
        try {
            id = Long.valueOf(studentId);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>("The given id is not a number.", HttpStatus.BAD_REQUEST);
        }
//        studentService.deleteStudentById(id, throwExceptionOnSave);
        studentService.deleteStudentWithCascade(id);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

}
