package com.example.demo.service.implementation;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository repository;

    // CREATE
    @Override
    public Student createStudent(Student student) {
        return repository.save(student);
    }

    // READ ALL
    @Override
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    // READ BY ID
    @Override
    public Student getStudentById(Long id) {
        Optional<Student> student = repository.findById(id);
        return student.orElse(null);
    }

    // UPDATE
    @Override
    public Student updateStudent(Long id, Student updatedStudent) {
        Student student = repository.findById(id).orElse(null);

        if (student != null) {
            student.setName(updatedStudent.getName());
            student.setAge(updatedStudent.getAge());
            return repository.save(student);
        }

        return null;
    }

    // DELETE
    @Override
    public void deleteStudent(Long id) {
        repository.deleteById(id);
    }
}