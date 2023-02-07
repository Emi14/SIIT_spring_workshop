package org.siit.springworkshop.converter;

import org.siit.springworkshop.dto.StudentDto;
import org.siit.springworkshop.entity.StudentEntity;

public class StudentConverter {

    public static StudentEntity fromDtoToEntity(StudentDto studentDto)
    {
        StudentEntity student = new StudentEntity();

        if(studentDto.getId() != null)
        {
            student.setId(studentDto.getId());
        }

        student.setAge(studentDto.getAge());
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setEmail(studentDto.getEmail());

        return student;
    }
}
