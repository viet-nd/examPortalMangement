package com.lunatic.examportalbackend.services;

import com.lunatic.examportalbackend.models.SubClass;
import com.lunatic.examportalbackend.models.Quiz;

import java.util.List;


public interface QuizService {

    Quiz addQuiz(Quiz quiz);

//    Quiz addComment(Long quizId, Long userId, String comment);

    List<Quiz> getQuizzes();

    Quiz getQuiz(Long quizId);

    Quiz updateQuiz(Quiz quiz);

//    Quiz updateCommentByQuizId(Long quizId, Long userId, String comment);

    void deleteQuiz(Long quizId);

    // Extra methods
    List<Quiz> getQuizBySubClass(SubClass subClass);
}
