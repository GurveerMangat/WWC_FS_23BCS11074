package com.example.studentcourse.Dto;

import com.example.studentcourse.model.Student;
public class StudentDto {
    private int statusCode;
    private boolean status;
    private String message;
    private Student student;

    public StudentDto(){};
    public StudentDto(int statusCode, boolean status, String message, Student student){
        this.statusCode=statusCode;
        this.status=status;
        this.message=message;
        this.student=student;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Student getStu() {
        return student;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}

