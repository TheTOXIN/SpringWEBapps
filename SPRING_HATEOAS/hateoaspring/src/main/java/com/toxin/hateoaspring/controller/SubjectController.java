package com.toxin.hateoaspring.controller;

import com.toxin.hateoaspring.entity.Subject;
import com.toxin.hateoaspring.resource.SubjectResource;
import com.toxin.hateoaspring.serivce.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SubjectController {

    private final SubjectService subjectService;
    private final SubjectResource subjectResource;

    @Autowired
    public SubjectController(
        SubjectService subjectService,
        SubjectResource subjectResource
    ) {
        this.subjectService = subjectService;
        this.subjectResource = subjectResource;
    }

    @GetMapping("/subjects/read-by-user/{id}")
    public Resources<Subject> readByUser(@PathVariable long id) {
        List<Subject> list = subjectService.readByUser(id);

        return subjectResource.readByUser(list, id);
    }

}
