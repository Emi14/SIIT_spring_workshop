package org.siit.springworkshop.service;

import jakarta.transaction.Transactional;
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

    private final StudentRepository studentRepository;
    private final AddressService addressService;
    private final StudentConverter studentConverter;

    public StudentService(StudentRepository studentRepository, AddressService addressService, StudentConverter studentConverter) {
        this.studentRepository = studentRepository;
        this.addressService = addressService;
        this.studentConverter = studentConverter;
    }

    @Transactional
    public Long saveStudent(StudentDto studentDto, boolean throwExceptionOnSave) {
        StudentEntity student = studentConverter.fromDtoToEntity(studentDto);

        StudentEntity savedStudent = studentRepository.save(student);

        if(throwExceptionOnSave)
        {
            throw new RuntimeException("Exception after student was saved");
        }

        addressService.saveAll(studentDto.getAddresses(), savedStudent);

        return savedStudent.getId();
    }

    public Long saveStudentWithCascade(StudentDto studentDto)
    {
        StudentEntity studentEntity = studentConverter.fromDtoToEntity(studentDto);
        studentEntity.getAddresses().forEach(address -> address.setStudent(studentEntity));
        return studentRepository.save(studentEntity).getId();
    }

    public StudentDto getStudentById(Long id) throws DataNotFound {
        Optional<StudentEntity> student = studentRepository.findById(id);

        if (student.isEmpty()) {
            throw new DataNotFound(String.format("The student with id %s could not be found in database.", id));
        }
        return studentConverter.fromEntityToDto(student.get());
    }

    public StudentDto getStudentByFirstNameAndAge(String firstName, Integer age) throws DataNotFound {
//        List<StudentEntity> student = studentRepository.findStudent(firstName, age);
//        List<StudentEntity> student = studentRepository.findAllByFirstNameAndAge(firstName, age);
        List<StudentEntity> student = studentRepository.findAllByFirstNameAndAge(firstName, age);

        if (student.isEmpty()) {
            throw new DataNotFound(String.format("The student with first name %s and age %s could not be found in database.", firstName, age));
        }
        return studentConverter.fromEntityToDto(student.get(0));
    }

    public StudentDto getStudentByFirstNameContainingAndAgeGreaterThan(String firstName, Integer age) throws DataNotFound {
//        List<StudentEntity> student = studentRepository.findStudent(firstName, age);
//        List<StudentEntity> student = studentRepository.findAllByFirstNameAndAge(firstName, age);
//        Optional<StudentEntity> student = studentRepository.findFirstByFirstNameContainingAndAgeGreaterThan(firstName, age);
        Optional<StudentEntity> student = studentRepository.findFirstByFirstNameOrderByIdDesc(firstName);

        if (student.isEmpty()) {
            throw new DataNotFound(String.format("The student with first name %s and age %s could not be found in database.", firstName, age));
        }
        return studentConverter.fromEntityToDto(student.get());
    }

    @Transactional
    public void deleteStudentById(Long studentId, boolean throwExceptionOnSave) throws DataNotFound {
        if(!studentRepository.existsById(studentId))
        {
            throw new DataNotFound(String.format("The student with id %s could not be found in database.", studentId));
        }
        addressService.deleteAddressesByStudentId(studentId);
        if(throwExceptionOnSave)
        {
            throw new RuntimeException("Exception after student was saved");
        }
        studentRepository.deleteById(studentId);
    }

    public void deleteStudentWithCascade(Long studentId)throws DataNotFound {
        if (!studentRepository.existsById(studentId)) {
            throw new DataNotFound(String.format("The student with id %s could not be found in database.", studentId));
        }
        studentRepository.deleteById(studentId);
    }
}
