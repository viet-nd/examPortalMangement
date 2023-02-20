package com.lunatic.examportalbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "quiz_results")
public class QuizResult implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quizResId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "total_obtained_marks")
    private float totalObtainedMarks;

    @Column(name = "attempt_datetime")
    private String attemptDatetime;

    @ManyToOne(fetch = FetchType.EAGER)
    private Quiz quiz;


}
