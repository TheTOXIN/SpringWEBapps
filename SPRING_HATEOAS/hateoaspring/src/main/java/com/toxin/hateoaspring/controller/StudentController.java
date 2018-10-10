package com.toxin.hateoaspring.controller;

import com.toxin.hateoaspring.repository.StudentRepository;
import com.toxin.hateoaspring.resource.StudentResource;
import com.toxin.hateoaspring.entity.Student;
import com.toxin.hateoaspring.serivce.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class StudentController {

    private final StudentRepository studentRepository;
    private final StudentResource studentResource;
    private final StudentService studentService;

    @Autowired
    public StudentController(
        StudentRepository studentRepository,
        StudentResource studentResource,
        StudentService studentService
    ) {
        this.studentRepository = studentRepository;
        this.studentResource = studentResource;
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public Resources<Student> retrieveAllStudents() {
        List<Student> students = studentRepository.findAll();

        return studentResource.retrieveAllStudents(students);
    }

    @GetMapping("/students/{id}")
    public Resource<Student> retrieveStudent(@PathVariable long id) {
        Student student = studentService.findById(id);

        return studentResource.retrieveStudent(student);
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
        studentService.findById(id);

        student.setStudentId(id);

        studentRepository.save(student);

        return studentResource.actionStudent(student);
    }

    @GetMapping("/students/{id}/generate-subjects")
    public Resource<Long> generateSubjects(@PathVariable long id) {
        studentService.generateSubjects(id);

        return studentResource.generateSubjects(id);
    }

}
