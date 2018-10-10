package com.toxin.hateoaspring.resource;

import com.toxin.hateoaspring.controller.StudentController;
import com.toxin.hateoaspring.entity.Subject;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Service
public class SubjectResource {

    public Resources<Subject> readByUser(List<Subject> subjects, long id) {
        return new Resources<>(
            subjects,
            linkTo(
                methodOn(StudentController.class).retrieveStudent(id)
            ).withSelfRel()
        );
    }

}
