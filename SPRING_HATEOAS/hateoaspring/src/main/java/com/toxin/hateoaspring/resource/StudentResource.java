package com.toxin.hateoaspring.resource;

import com.toxin.hateoaspring.controller.StudentController;
import com.toxin.hateoaspring.entity.Student;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Service
public class StudentResource {

    private Class<StudentController> clazz = StudentController.class;

    public Resource<Student> retrieveStudent(Student student) {
        Resource<Student> resource = new Resource<>(student);

        resource.add(
            linkTo(
                methodOn(clazz).retrieveAllStudents()
            ).withRel("all-student"),
            linkTo(
                methodOn(clazz).deleteStudent(student.getStudentId())
            ).withRel("delete-student")
        );

        return resource;
    }

    public Resources<Student> retrieveAllStudents(List<Student> students) {
        Link link = linkTo(StudentController.class).withSelfRel();

        Resources<Student> resources = new Resources<>(students, link);

        students.forEach(s -> s.add(linkTo(methodOn(clazz).retrieveStudent(s.getStudentId())).withSelfRel()));

        return resources;
    }

    public Resource<Student> actionStudent(Student student) {
        Resource<Student> resource = new Resource<>(student);

        resource.add(linkTo(methodOn(clazz).retrieveStudent(student.getStudentId())).withSelfRel());

        return resource;
    }

}
