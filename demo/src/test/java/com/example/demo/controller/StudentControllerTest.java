package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService service;

    @Autowired
    private ObjectMapper objectMapper;

    // CREATE
    @Test
    void testCreateStudent() throws Exception {
        Student student = new Student();
        student.setName("John");
        student.setAge(20);

        when(service.createStudent(any(Student.class))).thenReturn(student);

        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John"))
                .andExpect(jsonPath("$.age").value(20));
    }

    // READ ALL
    @Test
    void testGetAllStudents() throws Exception {
        Student s1 = new Student();
        s1.setName("John");
        s1.setAge(20);

        Student s2 = new Student();
        s2.setName("Jane");
        s2.setAge(22);

        when(service.getAllStudents()).thenReturn(Arrays.asList(s1, s2));

        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    // READ BY ID
    @Test
    void testGetStudentById() throws Exception {
        Student student = new Student();
        student.setName("John");
        student.setAge(20);

        when(service.getStudentById(1L)).thenReturn(student);

        mockMvc.perform(get("/students/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John"))
                .andExpect(jsonPath("$.age").value(20));
    }

    // UPDATE
    @Test
    void testUpdateStudent() throws Exception {
        Student updated = new Student();
        updated.setName("Updated");
        updated.setAge(25);

        when(service.updateStudent(eq(1L), any(Student.class))).thenReturn(updated);

        mockMvc.perform(put("/students/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated"))
                .andExpect(jsonPath("$.age").value(25));
    }

    // DELETE
    @Test
    void testDeleteStudent() throws Exception {
        doNothing().when(service).deleteStudent(1L);

        mockMvc.perform(delete("/students/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Student deleted successfully"));
    }
}