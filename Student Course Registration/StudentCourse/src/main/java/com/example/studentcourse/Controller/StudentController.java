package com.example.studentcourse.Controller;

import com.example.studentcourse.model.Student;
import com.example.studentcourse.Dto.StudentDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/student")
public class StudentController {
    ArrayList<Student> students = new ArrayList<>();

    @PostMapping
    public ResponseEntity<StudentDto> registerStudent(@RequestBody Student student) {
        StudentDto sd = new StudentDto();
        if (student.getName() == null || student.getName().isEmpty() || student.getCourse() == null || student.getCourse().isEmpty()) {
            sd.setStatus(false);
            sd.setStatusCode(400);
            sd.setMessage("Name or Course not valid.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sd);
        }
        for (Student value : students) {
            if (value.getId() == student.getId()) {
                sd.setStatus(false);
                sd.setStatusCode(409);
                sd.setMessage("Student Already Exist.");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(sd);
            }
        }
        students.add(student);
        sd.setStatus(true);
        sd.setStatusCode(201);
        sd.setMessage("Student Added Successfully.");
        sd.setStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(sd);
    }

    @GetMapping
    public ResponseEntity<ArrayList<Student>> getAllStudents() {
        StudentDto sd = new StudentDto();
        sd.setStatus(true);
        sd.setStatusCode(201);
        sd.setMessage("Student Added Successfully.");
        return ResponseEntity.status(HttpStatus.OK).body(students);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<StudentDto> getById(@PathVariable int id) {
        StudentDto sd = new StudentDto();
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                sd.setStatus(true);
                sd.setStatusCode(200);
                sd.setMessage("Student Found Successfully.");
                sd.setStudent(students.get(i));
                return ResponseEntity.status(HttpStatus.OK).body(sd);
            }
        }
        sd.setStatus(false);
        sd.setStatusCode(404);
        sd.setMessage("Student Not Found.");
        sd.setStudent(null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(sd);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<StudentDto> deleteStudent(@PathVariable int id) {
        StudentDto sd = new StudentDto();
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                sd.setStatus(true);
                sd.setStatusCode(200);
                sd.setMessage("Student Deleted Successfully");
                sd.setStudent(students.get(i));
                students.remove(i);
                return ResponseEntity.status(HttpStatus.OK).body(sd);
            }
        }
        sd.setStatus(false);
        sd.setStatusCode(404);
        sd.setMessage("Student Not Found.");
        sd.setStudent(null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(sd);
    }

}
