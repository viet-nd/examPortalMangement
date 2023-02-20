package com.lunatic.examportalbackend.controllers;

import com.lunatic.examportalbackend.models.Subject;
import com.lunatic.examportalbackend.models.SubClass;
import com.lunatic.examportalbackend.models.User;
import com.lunatic.examportalbackend.repository.SubjectRepository;
import com.lunatic.examportalbackend.services.SubClassService;
import com.lunatic.examportalbackend.services.SubjectService;
import com.lunatic.examportalbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/subClass")
public class SubClassController {

    @Autowired
    private SubClassService subClassService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private UserService userService;

    @Autowired
    private SubjectRepository subjectRepository;

    @PostMapping("/")
    public ResponseEntity<?> addSubClass(@RequestBody SubClass subClass) {
        return ResponseEntity.ok(subClassService.addSubClass(subClass));
    }

    @GetMapping("/")
    public ResponseEntity<?> getSubClasses() {
        return ResponseEntity.ok(subClassService.getSubClasses());
    }

    @GetMapping("/{subClassId}")
    public ResponseEntity<?> getSubClassById(@PathVariable Long subClassId) {
        return ResponseEntity.ok(subClassService.getSubClass(subClassId));
    }

    @GetMapping(value = "/", params = "subjectId")
    public ResponseEntity<?> getSubClassBySubId(@RequestParam Long subjectId) {
        Subject subject = subjectService.getSubject(subjectId);
        return ResponseEntity.ok(subClassService.getSubClassBySubId(subject));
    }

    @GetMapping(value = "/", params = "userId")
    public ResponseEntity<?> getSubClassByUserId(@RequestParam Long userId) {
        User user = userService.getUser(userId);
        if (user != null) {
            return ResponseEntity.ok(subClassService.getSubClassByUserId(user));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User with id : " + String.valueOf(userId) + ", doesn't exists");
    }
//
//    @GetMapping(value = "/manager", params = "userId")
//    public ResponseEntity<?> getSubClassByManagerId(@RequestParam Long managerId) {
//        User user = userService.getUser(managerId);
//        if (user != null) {
//            return ResponseEntity.ok(subClassService.getSubClassByManagerId(managerId));
//        }
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Manager with id : " + String.valueOf(managerId) + ", doesn't exists");
//    }

    @PutMapping("/{subClassId}")
    public ResponseEntity<?> updateSubClass(@PathVariable Long subClassId, @RequestBody SubClass subClass) {
        if (subClassService.getSubClass(subClassId) != null) {
            return ResponseEntity.ok(subClassService.updateCategoryClass(subClass));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Subject class with id : " + String.valueOf(subClassId) + ", doesn't exists");
    }

    @PutMapping("/join/{subClassId}")
    public ResponseEntity<?> joinSubClass(@PathVariable Long subClassId, @RequestParam("userId") Long userId) {
        if (subClassService.getSubClass(subClassId) != null) {
            return ResponseEntity.ok(subClassService.joinSubClass(subClassId, userId));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Subject class with id : " + String.valueOf(subClassId) + ", doesn't exists");
    }

    @PutMapping("/detach/{subClassId}")
    public ResponseEntity<?> detachCategoryClass(@PathVariable Long subClassId, @RequestParam("userId") Long userId) {
        if (subClassService.getSubClass(subClassId) != null) {
            return ResponseEntity.ok(subClassService.detachSubClass(subClassId, userId));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Category class with id : " + String.valueOf(subClassId) + ", doesn't exists");
    }

    @DeleteMapping("/{subClassId}")
    public ResponseEntity<?> deleteCategoryClass(@PathVariable Long subClassId) {
        subClassService.deleteSubClass(subClassId);
        return ResponseEntity.ok(true);
    }
}
