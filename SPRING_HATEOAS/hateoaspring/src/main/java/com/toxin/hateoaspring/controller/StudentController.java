package com.toxin.hateoaspring.controller;

import com.toxin.hateoaspring.exception.StudentNotFoundException;
import com.toxin.hateoaspring.repository.StudentRepository;
import com.toxin.hateoaspring.resource.StudentResource;
import com.toxin.hateoaspring.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class StudentController {

    private final StudentRepository studentRepository;

    private final StudentResource studentResource;

    @Autowired
    public StudentController(
        StudentRepository studentRepository,
        StudentResource studentResource
    ) {
        this.studentRepository = studentRepository;
        this.studentResource = studentResource;
    }

    @GetMapping("/students")
    public Resources<Student> retrieveAllStudents() {
        return studentResource.retrieveAllStudents(studentRepository.findAll());
    }

    @GetMapping("/students/{id}")
    public Resource<Student> retrieveStudent(@PathVariable long id) {
        Optional<Student> student = studentRepository.findById(id);

        if (!student.isPresent())
            throw new StudentNotFoundException("NOT FOUND by studentId-" + id);

        return studentResource.retrieveStudent(student.get());
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity deleteStudent(@PathVariable long id) {
        studentRepository.deleteById(id);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/students")
    public Resource<Student> createStudent(@RequestBody Student student) {
        Student savedStudent = studentRepository.save(student);

        return studentResource.actionStudent(savedStudent);
    }

    @PutMapping("/students/{id}")
    public Resource<Student> updateStudent(@RequestBody Student student, @PathVariable long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);

        if (!studentOptional.isPresent())
            throw new StudentNotFoundException("NOT FOUND by studentId-" + id);

        student.setStudentId(id);

        studentRepository.save(student);

        return studentResource.actionStudent(student);
    }

}
