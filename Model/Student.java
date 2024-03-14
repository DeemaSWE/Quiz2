package com.example.quiz2.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {

    @NotEmpty(message = "Id cannot be empty")
    private String id;

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotNull(message = "Age cannot be empty")
    private Integer age;

    @NotEmpty(message = "Major cannot be empty")
    private String major;
}
