package com.lunatic.examportalbackend.controllers;

import com.lunatic.examportalbackend.models.SubClass;
import com.lunatic.examportalbackend.models.Quiz;
import com.lunatic.examportalbackend.services.SubClassService;
import com.lunatic.examportalbackend.services.SubjectService;
import com.lunatic.examportalbackend.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @Autowired
    private SubjectService subjectService;
    @Autowired
    private SubClassService subClassService;

    @PostMapping("/")
    public ResponseEntity<?> addQuiz(@RequestBody Quiz quiz) {
        return ResponseEntity.ok(quizService.addQuiz(quiz));
    }

    @GetMapping("/")
    public ResponseEntity<?> getQuizzes() {
        return ResponseEntity.ok(quizService.getQuizzes());
    }

    @GetMapping("/{quizId}")
    public ResponseEntity<?> getQuiz(@PathVariable Long quizId) {
        return ResponseEntity.ok(quizService.getQuiz(quizId));
    }

//    @PostMapping("/comment/{quizId}")
//    public ResponseEntity<?> addCommentByQuizId(
//            @PathVariable Long quizId,
//            @RequestParam("comment") String comment,
//            @RequestParam("userid") Long userId) {
//        return ResponseEntity.ok(quizService.addComment(quizId, userId, comment));
//    }

    @GetMapping(value = "/", params = "subClassId")
    public ResponseEntity<?> getQuizBySubClass(@RequestParam Long subClassId) {
        SubClass subClass = subClassService.getSubClass(subClassId);
        return ResponseEntity.ok(quizService.getQuizBySubClass(subClass));
    }

    @PutMapping("/{quizId}")
    public ResponseEntity<?> updateQuiz(@PathVariable Long quizId, @RequestBody Quiz quiz) {
        if (quizService.getQuiz(quizId) != null) {
            return ResponseEntity.ok(quizService.updateQuiz(quiz));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Quiz with id : " + String.valueOf(quizId) + ", doesn't exists");
    }

//    @PutMapping("/comment/{quizId}")
//    public ResponseEntity<?> updateCommentByQuizId(
//            @PathVariable Long quizId,
//            @RequestParam("comment") String comment,
//            @RequestParam("userid") Long userId) {
//        if (quizService.getQuiz(quizId) != null) {
//            return ResponseEntity.ok(quizService.updateCommentByQuizId(quizId, userId, comment));
//        }
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Quiz with id : " + String.valueOf(quizId) + ", doesn't exists");
//    }

    @DeleteMapping("/{quizId}")
    public ResponseEntity<?> deleteQuiz(@PathVariable Long quizId) {
        quizService.deleteQuiz(quizId);
        return ResponseEntity.ok(true);
    }
}
