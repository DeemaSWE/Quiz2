package com.example.quiz2.Controller;

import com.example.quiz2.Api.ApiResponse;
import com.example.quiz2.Model.Teacher;
import com.example.quiz2.Service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping("/get")
    public ResponseEntity getTeachers(){

        return ResponseEntity.status(200).body(teacherService.getTeachers());
    }

    @PostMapping("/add")
    public ResponseEntity addTeacher(@RequestBody @Valid Teacher teacher, Errors errors){

        if(errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        teacherService.addTeacher(teacher);
        return ResponseEntity.status(200).body(new ApiResponse("Teacher added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateTeacher(@PathVariable String id, @RequestBody Teacher teacher, Errors errors){

        if(errors.hasErrors())
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());

        boolean isUpdated = teacherService.updateTeacher(id, teacher);

        if(isUpdated)
            return ResponseEntity.status(200).body(new ApiResponse("Teacher updated"));

        return ResponseEntity.status(400).body(new ApiResponse("No teacher found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTeacher(@PathVariable String id){

        boolean isDeleted = teacherService.deleteTeacher(id);

        if(isDeleted)
            return ResponseEntity.status(200).body(new ApiResponse("Teacher deleted"));

        return ResponseEntity.status(400).body(new ApiResponse("No teacher found"));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getTeacherById(@PathVariable String id){

        Teacher teacher = teacherService.getTeacherById(id);

        if(teacher == null)
            return ResponseEntity.status(400).body(new ApiResponse("No teacher found"));

        return ResponseEntity.status(200).body(teacher);
    }

    @GetMapping("search/{salary}")
    public ResponseEntity searchBySalary(@PathVariable Integer salary){

        ArrayList<Teacher> teachers = teacherService.searchBySalary(salary);

        if(teachers.isEmpty())
            return ResponseEntity.status(400).body(new ApiResponse("No teacher found"));

        return ResponseEntity.status(200).body(teachers);
    }
}
