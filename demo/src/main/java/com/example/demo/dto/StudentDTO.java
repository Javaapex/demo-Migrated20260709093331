package com.example.demo.dto;

public class StudentDTO {

    private String name;
    private int age;

    // ✅ Correct default constructor
    public StudentDTO() {
    }

    // ✅ Correct parameterized constructor
    public StudentDTO(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // ✅ Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}