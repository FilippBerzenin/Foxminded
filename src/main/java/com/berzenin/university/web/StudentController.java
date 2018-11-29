package com.berzenin.university.web;

import com.berzenin.university.dao.StudentRepository;
import com.berzenin.university.model.persons.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StudentController {

    private final StudentRepository studentRepository;

    @GetMapping(
            value = "/students",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    List<Student> getAll(){
        return studentRepository.findAll();
    }

    @PostMapping(
            value = "/students",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    Student addStudent(@RequestBody Student student){
        return studentRepository.save(student);
    }
}
