package org.siit.springworkshop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.siit.springworkshop.converter.AddressConverter;
import org.siit.springworkshop.converter.StudentConverter;
import org.siit.springworkshop.dto.StudentDto;
import org.siit.springworkshop.entity.StudentEntity;
import org.siit.springworkshop.exception.DataNotFound;
import org.siit.springworkshop.helper.StudentInfoContributor;
import org.siit.springworkshop.repository.StudentRepository;
import org.siit.springworkshop.util.StudentUtil;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

//@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    private static final long STUDENT_ID = 12;
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private AddressService addressService;
    @Mock
    private StudentConverter studentConverter;
    @Mock
    private AddressConverter addressConverter;
    @Mock
    private StudentInfoContributor studentInfoContributor;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    public void setup()
    {

    }

    @Test
    public void testGetStudentById_whenIdIsValid_thenStudentIsReturned() throws DataNotFound {
//        anyInt() any() any(StudentEntity.class)
        StudentEntity studentEntity = StudentUtil.getStudentEntity();
        when(studentRepository.findById(eq(STUDENT_ID)))
                .thenReturn(Optional.of(studentEntity));
        when(studentConverter.fromEntityToDto(eq(studentEntity)))
                .thenReturn(StudentUtil.getStudentDto());

        StudentDto result = studentService.getStudentById(STUDENT_ID);
        assertEquals(studentEntity.getFirstName(), result.getFirstName());
        assertEquals(studentEntity.getLastName(), result.getLastName());
        verify(studentConverter, times(1)).fromEntityToDto(eq(studentEntity));
        verifyNoInteractions(addressService);
        verifyNoInteractions(studentInfoContributor);
    }

}
