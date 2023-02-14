package org.siit.springworkshop.converter;

import org.modelmapper.ModelMapper;
import org.siit.springworkshop.dto.StudentDto;
import org.siit.springworkshop.entity.StudentEntity;
import org.springframework.stereotype.Component;

@Component
public class StudentConverter {

    private final ModelMapper mapper;

    public StudentConverter(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public StudentEntity fromDtoToEntity(StudentDto studentDto) {
//        StudentEntity student = new StudentEntity();
//
//        if (studentDto.getId() != null) {
//            student.setId(studentDto.getId());
//        }
//
//        student.setAge(studentDto.getAge());
//        student.setFirstName(studentDto.getFirstName());
//        student.setLastName(studentDto.getLastName());
//        student.setEmail(studentDto.getEmail());
//
//        return student;
        return mapper.map(studentDto, StudentEntity.class);
    }

    public StudentDto fromEntityToDto(StudentEntity studentEntity) {
//        StudentDto studentDto = new StudentDto();
//
//        studentDto.setId(studentEntity.getId());
//        studentDto.setFirstName(studentEntity.getFirstName());
//        studentDto.setLastName(studentEntity.getLastName());
//        studentDto.setAge(studentEntity.getAge());
//        studentDto.setEmail(studentEntity.getEmail());
//
//        return studentDto;

        return mapper.map(studentEntity, StudentDto.class);
    }
}
