package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.implementation.StudentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @Mock
    private StudentRepository repository;

    @InjectMocks
    private StudentServiceImpl service;

    // CREATE
    @Test
    void testCreateStudent() {
        Student student = new Student();
        student.setName("John");
        student.setAge(20);

        when(repository.save(student)).thenReturn(student);

        Student result = service.createStudent(student);

        assertEquals("John", result.getName());
        verify(repository).save(student);
    }

    // READ ALL
    @Test
    void testGetAllStudents() {
        Student s1 = new Student();
        s1.setName("John");
        s1.setAge(20);

        Student s2 = new Student();
        s2.setName("Jane");
        s2.setAge(22);

        when(repository.findAll()).thenReturn(Arrays.asList(s1, s2));

        List<Student> result = service.getAllStudents();

        assertEquals(2, result.size());
        verify(repository).findAll();
    }

    // READ BY ID - FOUND
    @Test
    void testGetStudentById() {
        Student student = new Student();
        student.setName("John");
        student.setAge(20);

        when(repository.findById(1L)).thenReturn(Optional.of(student));

        Student result = service.getStudentById(1L);

        assertNotNull(result);
        assertEquals("John", result.getName());
    }

    // READ BY ID - NOT FOUND (🔥 important)
    @Test
    void testGetStudentById_NotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        Student result = service.getStudentById(1L);

        assertNull(result);
    }

    // UPDATE - SUCCESS
    @Test
    void testUpdateStudent() {
        Student existing = new Student();
        existing.setName("Old");
        existing.setAge(20);

        Student updated = new Student();
        updated.setName("New");
        updated.setAge(25);

        when(repository.findById(1L)).thenReturn(Optional.of(existing));
        when(repository.save(existing)).thenReturn(existing);

        Student result = service.updateStudent(1L, updated);

        assertEquals("New", result.getName());
        assertEquals(25, result.getAge());
    }

    // UPDATE - NOT FOUND (🔥 important)
    @Test
    void testUpdateStudent_NotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        Student updated = new Student();
        updated.setName("Test");
        updated.setAge(25);

        Student result = service.updateStudent(1L, updated);

        assertNull(result);
    }

    // DELETE
    @Test
    void testDeleteStudent() {
        doNothing().when(repository).deleteById(1L);

        service.deleteStudent(1L);

        verify(repository).deleteById(1L);
    }
}