package com.lunatic.examportalbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "subject_class")
public class SubClass extends BaseModule implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subClassId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "key_active")
    private String keyActive;

    @Column(name = "create_by")
    private Long createBy;

    @ManyToOne(fetch = FetchType.EAGER)
    private Subject subject;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "sub_class_user",
            joinColumns = {
                    @JoinColumn(name = "sub_class_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "user_id")
            }
    )
    private Set<User> users;

    @OneToMany(mappedBy = "subClass", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Quiz> quizzes = new ArrayList<>();
}
