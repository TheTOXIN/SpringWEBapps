package com.toxin.hateoaspring.repository;

import com.toxin.hateoaspring.entity.Student;
import com.toxin.hateoaspring.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    List<Subject> findByStudent(Student student);

}
