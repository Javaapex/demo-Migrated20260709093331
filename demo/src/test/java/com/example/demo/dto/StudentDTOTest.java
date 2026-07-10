package com.example.demo.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentDTOTest {

    @Test
    void testGetterSetter() {
        StudentDTO dto = new StudentDTO();

        dto.setName("John");
        dto.setAge(20);

        assertEquals("John", dto.getName());
        assertEquals(20, dto.getAge());
    }

    @Test
    void testParameterizedConstructor() {
        StudentDTO dto = new StudentDTO("Jane", 25);

        assertEquals("Jane", dto.getName());
        assertEquals(25, dto.getAge());
    }

    @Test
    void testDefaultConstructor() {
        StudentDTO dto = new StudentDTO();

        assertNotNull(dto);
    }
}