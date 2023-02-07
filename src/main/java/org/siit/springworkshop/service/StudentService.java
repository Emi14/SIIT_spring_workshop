package org.siit.springworkshop.service;

import org.siit.springworkshop.converter.StudentConverter;
import org.siit.springworkshop.dto.StudentDto;
import org.siit.springworkshop.entity.StudentEntity;
import org.siit.springworkshop.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Long saveStudent(StudentDto studentDto)
    {
        StudentEntity student = StudentConverter.fromDtoToEntity(studentDto);
        return studentRepository.save(student).getId();
    }
}
