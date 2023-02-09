package org.siit.springworkshop.service;

import org.siit.springworkshop.converter.StudentConverter;
import org.siit.springworkshop.dto.StudentDto;
import org.siit.springworkshop.entity.StudentEntity;
import org.siit.springworkshop.exception.DataNotFound;
import org.siit.springworkshop.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Long saveStudent(StudentDto studentDto) {
        StudentEntity student = StudentConverter.fromDtoToEntity(studentDto);
        return studentRepository.save(student).getId();
    }

    public StudentDto getStudentById(Long id) throws DataNotFound {
        Optional<StudentEntity> student = studentRepository.findById(id);

        if(student.isEmpty())
        {
            throw new DataNotFound(String.format("The student with id %s could not be found in database.", id));
        }
        return StudentConverter.fromEntityToDto(student.get());
    }

    public StudentDto getStudentByFirstNameAndAge(String firstName, Integer age) throws DataNotFound {
//        List<StudentEntity> student = studentRepository.findStudent(firstName, age);
//        List<StudentEntity> student = studentRepository.findAllByFirstNameAndAge(firstName, age);
        List<StudentEntity> student = studentRepository.findAllByFirstNameAndAge(firstName, age);

        if(student.isEmpty())
        {
            throw new DataNotFound(String.format("The student with first name %s and age %s could not be found in database.", firstName, age));
        }
        return StudentConverter.fromEntityToDto(student.get(0));
    }

    public StudentDto getStudentByFirstNameContainingAndAgeGreaterThan(String firstName, Integer age) throws DataNotFound {
//        List<StudentEntity> student = studentRepository.findStudent(firstName, age);
//        List<StudentEntity> student = studentRepository.findAllByFirstNameAndAge(firstName, age);
//        Optional<StudentEntity> student = studentRepository.findFirstByFirstNameContainingAndAgeGreaterThan(firstName, age);
        Optional<StudentEntity> student = studentRepository.findFirstByFirstNameOrderByIdDesc(firstName);

        if(student.isEmpty())
        {
            throw new DataNotFound(String.format("The student with first name %s and age %s could not be found in database.", firstName, age));
        }
        return StudentConverter.fromEntityToDto(student.get());
    }
}
