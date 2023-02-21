package org.siit.springworkshop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.siit.springworkshop.dto.StudentDto;
import org.siit.springworkshop.exception.DataNotFound;
import org.siit.springworkshop.service.StudentService;
import org.siit.springworkshop.util.StudentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

//@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    private long studentId;
    private StudentDto studentDto;

    @BeforeEach
    public void setup()
    {
        studentDto = StudentUtil.getStudentDto();
        studentId = studentService.saveStudent(studentDto, false);
    }

    @AfterEach
    public void destroy() throws DataNotFound {
        studentService.deleteStudentById(studentId, false);
    }

    @Test
    public void testSearchStudentById_whenStudentIsPresentInDB_thenOk() throws Exception {

        MvcResult mvcResult = mockMvc.perform(get("/student/search/"+studentId).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        StudentDto result = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), StudentDto.class);

        assertEquals(studentDto.getFirstName(), result.getFirstName());
        assertEquals(studentDto.getLastName(), result.getLastName());
    }

    @Test
    public void testSearchStudentById_whenStudentIsNotPresentInDB_thenBadRequest() throws Exception {
        mockMvc.perform(get("/student/search/"+studentId+12).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}
