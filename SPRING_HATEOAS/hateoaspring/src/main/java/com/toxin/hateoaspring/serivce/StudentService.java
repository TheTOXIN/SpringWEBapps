package com.toxin.hateoaspring.serivce;

import com.toxin.hateoaspring.entity.Student;
import com.toxin.hateoaspring.exception.StudentNotFoundException;
import com.toxin.hateoaspring.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final SubjectService subjectService;

    @Autowired
    public StudentService(
        StudentRepository studentRepository,
        @Lazy SubjectService subjectService
    ) {
        this.studentRepository = studentRepository;
        this.subjectService = subjectService;
    }

    public void generateSubjects(long id) {
        subjectService.generateSubjects(findById(id));
    }

    public Student findById(long id) {
        Optional<Student> student = studentRepository.findById(id);

        if (!student.isPresent())
            throw new StudentNotFoundException(id);

        return student.get();
    }

}
