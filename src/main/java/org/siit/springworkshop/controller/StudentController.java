package org.siit.springworkshop.controller;

import jakarta.validation.Valid;
import org.siit.springworkshop.dto.StudentDto;
import org.siit.springworkshop.exception.DataNotFound;
import org.siit.springworkshop.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/save")
    public Long saveStudent(@Valid @RequestBody StudentDto studentDto,
                            @RequestHeader(name = "debugHeaderThrowExceptionOnSave", defaultValue = "false") boolean throwExceptionOnSave) {
//        return studentService.saveStudent(studentDto, throwExceptionOnSave);
        return studentService.saveStudentWithCascade(studentDto);
    }

    @PostMapping("/updateOrCreate")
    public Long upsertStudent(@Valid @RequestBody StudentDto studentDto) throws DataNotFound {
        return studentService.upsertStudent(studentDto);
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

    @GetMapping("/all")
    public List<StudentDto> getAll(@RequestParam(name = "pageNumber") int pageNumber,
                                   @RequestParam(name = "pageSize") int pageSize,
                                   @RequestParam(name = "sortBy") String sortBy,
                                   @RequestParam(name = "order") String order) {
//        return studentService.findAll();
        return studentService.findAllPaginated(pageNumber, pageSize, sortBy, order);
    }

    @PostMapping("/send/email/{id}")
    public ResponseEntity<String> sendEmail(@PathVariable(name = "id") String studentId,
                                            @RequestHeader(name = "debugHeaderThrowExceptionOnSendingEmail", defaultValue= "false") boolean throwExceptionOnSend) {
        try {
            studentService.sendEmail(studentId, throwExceptionOnSend);
        } catch (Exception e) {
            return new ResponseEntity<>("There was an exception. Try again later.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        /*call other logic in main thread*/
        return new ResponseEntity<>("Email is going to be sent.", HttpStatus.OK);
    }

}
