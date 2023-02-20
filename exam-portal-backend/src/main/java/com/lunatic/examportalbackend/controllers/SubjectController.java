package com.lunatic.examportalbackend.controllers;

import com.lunatic.examportalbackend.models.Subject;
import com.lunatic.examportalbackend.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/subject")

public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @PostMapping("/")
    public ResponseEntity<?> addSubject(@RequestBody Subject subject) {
        return ResponseEntity.ok(subjectService.addSubject(subject));
    }

    @GetMapping("/")
    public ResponseEntity<?> getSubjects() {
        return ResponseEntity.ok(subjectService.getSubjects());
    }

    @GetMapping("/{subjectId}")
    public ResponseEntity<?> getSubject(@PathVariable Long subjectId) {
        return ResponseEntity.ok(subjectService.getSubject(subjectId));
    }

    @PutMapping("/{subjectId}")
    public ResponseEntity<?> updateSubject(@PathVariable Long subjectId, @RequestBody Subject subject) {
        if (subjectService.getSubject(subjectId) != null) {
            return ResponseEntity.ok(subjectService.updateSubject(subject));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Subject with id : " + String.valueOf(subjectId) + ", doesn't exists");
    }

    @DeleteMapping("/{subjectId}")
    public ResponseEntity<?> deleteSubject(@PathVariable Long subjectId) {
        subjectService.deleteSubject(subjectId);
        return ResponseEntity.ok(true);
    }
}
