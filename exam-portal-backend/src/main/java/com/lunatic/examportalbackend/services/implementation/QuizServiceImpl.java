package com.lunatic.examportalbackend.services.implementation;

import com.lunatic.examportalbackend.models.SubClass;
import com.lunatic.examportalbackend.models.Quiz;
import com.lunatic.examportalbackend.repository.QuizRepository;
import com.lunatic.examportalbackend.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    public QuizRepository quizRepository;

    @Override
    public Quiz addQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

//    @Override
//    public Quiz addComment(Long quizId, Long userId, String comment) {
//        Quiz quiz = quizRepository.findById(quizId).get();
//        String newComment = quiz.getComment() != null
//                ? userId.toString() + "||" + comment + "||" + quiz.getComment()
//                : userId.toString() + "||" + comment;
//        quiz.setComment(newComment);
//        return quizRepository.save(quiz);
//    }

    @Override
    public List<Quiz> getQuizzes() {
        return quizRepository.findAll();
    }

    @Override
    public Quiz getQuiz(Long quizId) {
        return quizRepository.findById(quizId).isPresent() ? quizRepository.findById(quizId).get() : null;
    }

    @Override
    public Quiz updateQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

//    @Override
//    public Quiz updateCommentByQuizId(Long quizId, Long userId, String comment) {
//        Quiz quiz = quizRepository.findById(quizId).get();
//        String currentComment = quiz.getComment();
//        String[] commentArray = currentComment.split("||");
//        String newComment = "";
//
//        for (int i = 0; i < commentArray.length; i++) {
//            if (commentArray[i].equals(userId.toString())) {
//                commentArray[i + 1] = comment;
//            }
//            newComment += commentArray[i];
//        }
//
//        quiz.setComment(newComment);
//        return quizRepository.save(quiz);
//    }

    @Override
    public void deleteQuiz(Long quizId) {
        quizRepository.deleteById(quizId);
    }

    @Override
    public List<Quiz> getQuizBySubClass(SubClass subClass) {
        return quizRepository.findBySubClass(subClass);
    }
}
