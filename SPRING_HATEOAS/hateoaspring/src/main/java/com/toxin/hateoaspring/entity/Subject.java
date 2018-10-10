package com.toxin.hateoaspring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Subject {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private LocalDateTime start;

    @JsonIgnore
    @ManyToOne
    private Student student;

    public Subject(
        String title,
        LocalDateTime start,
        Student student
    ) {
        this.title = title;
        this.start = start;
        this.student = student;
    }

}
