package com.lunatic.examportalbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "subjects")
public class Subject extends BaseModule implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

//    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
//    @JsonIgnore
//    private List<Quiz> quizzes = new ArrayList<>();

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<SubClass> subClasses = new ArrayList<>();
}
