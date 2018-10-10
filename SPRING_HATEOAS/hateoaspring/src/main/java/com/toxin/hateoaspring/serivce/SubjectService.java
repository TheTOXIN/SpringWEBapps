package com.toxin.hateoaspring.serivce;

import com.toxin.hateoaspring.entity.Student;
import com.toxin.hateoaspring.entity.Subject;
import com.toxin.hateoaspring.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;


@Service
public class SubjectService {

    private final Random random = new Random();

    private final SubjectRepository subjectRepository;
    private final StudentService studentService;

    @Autowired
    public SubjectService(
        SubjectRepository subjectRepository,
        @Lazy StudentService studentService
    ) {
        this.subjectRepository = subjectRepository;
        this.studentService = studentService;
    }

    public void generateSubjects(Student student) {
        IntStream.range(0, random.nextInt(4)).forEach(i ->
            subjectRepository.save(
                new Subject(
                    "SUBJECT_" + random.nextInt(10),
                    LocalDateTime.now().plusMinutes(random.nextInt(24 * 60 * 60)),
                    student
                )
            )
        );
    }

    public List<Subject> readByUser(long id) {
        Student student = studentService.findById(id);

        return subjectRepository.findByStudent(student);
    }

}
