package org.siit.springworkshop.util;

import org.siit.springworkshop.dto.StudentDto;
import org.siit.springworkshop.entity.StudentEntity;
import org.siit.springworkshop.enums.Gender;

import java.util.ArrayList;

public class StudentUtil {

    public static StudentDto getStudentDto()
    {
        StudentDto studentDto = new StudentDto();

        studentDto.setFirstName("Dorel");
        studentDto.setLastName("Gigel");
        studentDto.setAge(20);
        studentDto.setEmail("dorel@gmail.com");
        studentDto.setGender(Gender.MALE);
        studentDto.setAddresses(new ArrayList<>());

        return studentDto;
    }
    public static StudentEntity getStudentEntity()
    {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setFirstName("Dorel");
        studentEntity.setLastName("Gigel");
        studentEntity.setAge(20);
        studentEntity.setEmail("dorel@gmail.com");
        studentEntity.setGender(Gender.MALE.getDbValue());
        studentEntity.setAddresses(new ArrayList<>());

        return studentEntity;
    }
}
