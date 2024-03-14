package com.example.quiz2.Controller;

import com.example.quiz2.Api.ApiResponse;
import com.example.quiz2.Model.Student;
import com.example.quiz2.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/get")
    public ResponseEntity getStudents(){
        return ResponseEntity.status(200).body(studentService.getStudents());
    }

    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody @Valid Student student, Errors errors){

        if(errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        studentService.addStudent(student);
        return ResponseEntity.status(200).body(new ApiResponse("Student added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateStudent(@PathVariable String id, @RequestBody @Valid Student student, Errors errors){

        if(errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        boolean isUpdated = studentService.updateStudent(id, student);

        if(isUpdated)
            return ResponseEntity.status(200).body(new ApiResponse("Student updated"));

        return ResponseEntity.status(400).body(new ApiResponse("No student found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudent(@PathVariable String id){

        boolean isDeleted = studentService.deleteStudent(id);

        if(isDeleted)
            return ResponseEntity.status(200).body(new ApiResponse("Student Deleted"));

        return ResponseEntity.status(400).body(new ApiResponse("No student found"));
    }

    @GetMapping("/get/{name}")
    public ResponseEntity getStudentByName(@PathVariable String name){

        Student student = studentService.getStudentByName(name);

        if(student == null)
            return ResponseEntity.status(400).body(new ApiResponse("No student found"));

        return ResponseEntity.status(200).body(student);
    }

    @GetMapping("/search/{major}")
    public ResponseEntity searchByMajor(@PathVariable String major){

        ArrayList<Student> students = studentService.searchByMajor(major);

        if(students.isEmpty())
            return ResponseEntity.status(400).body(new ApiResponse("No student found"));

        return ResponseEntity.status(200).body(students);
    }
}
