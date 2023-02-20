package com.lunatic.examportalbackend.controllers;

import com.lunatic.examportalbackend.models.Category;
import com.lunatic.examportalbackend.models.CategoryClass;
import com.lunatic.examportalbackend.models.User;
import com.lunatic.examportalbackend.repository.CategoryRepository;
import com.lunatic.examportalbackend.services.CategoryClassService;
import com.lunatic.examportalbackend.services.CategoryService;
import com.lunatic.examportalbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/categoryClass")
public class CategoryClassController {

    @Autowired
    private CategoryClassService categoryClassService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping("/")
    public ResponseEntity<?> addCategoryClass(@RequestBody CategoryClass categoryClass) {
        return ResponseEntity.ok(categoryClassService.addCategoryClass(categoryClass));
    }

    @GetMapping("/")
    public ResponseEntity<?> getCategoryClasses() {
        return ResponseEntity.ok(categoryClassService.getCategoryClasses());
    }

    @GetMapping("/{categoryClassId}")
    public ResponseEntity<?> getCategoryClassById(@PathVariable Long categoryClassId) {
        return ResponseEntity.ok(categoryClassService.getCategoryClass(categoryClassId));
    }

    @GetMapping(value = "/", params = "categoryId")
    public ResponseEntity<?> getCategoryClassByCatId(@RequestParam Long categoryId) {
        Category category = categoryService.getCategory(categoryId);
        return ResponseEntity.ok(categoryClassService.getCategoryClassByCatId(category));
    }

    @GetMapping(value = "/", params = "userId")
    public ResponseEntity<?> getCategoryClass(@RequestParam Long userId) {
        User user = userService.getUser(userId);
        if (user != null) {
            return ResponseEntity.ok(categoryClassService.getCategoryClassByUserId(user));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User with id : " + String.valueOf(userId) + ", doesn't exists");
    }

    @PutMapping("/{categoryClassId}")
    public ResponseEntity<?> updateCategoryClass(@PathVariable Long categoryClassId, @RequestBody CategoryClass categoryClass) {
        if (categoryClassService.getCategoryClass(categoryClassId) != null) {
            return ResponseEntity.ok(categoryClassService.updateCategoryClass(categoryClass));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Category class with id : " + String.valueOf(categoryClassId) + ", doesn't exists");
    }

    @PutMapping("/join/{categoryClassId}")
    public ResponseEntity<?> joinCategoryClass(@PathVariable Long categoryClassId, @RequestParam("userId") Long userId) {
        if (categoryClassService.getCategoryClass(categoryClassId) != null) {
            return ResponseEntity.ok(categoryClassService.joinCategoryClass(categoryClassId, userId));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Category class with id : " + String.valueOf(categoryClassId) + ", doesn't exists");
    }

    @PutMapping("/detach/{categoryClassId}")
    public ResponseEntity<?> detachCategoryClass(@PathVariable Long categoryClassId, @RequestParam("userId") Long userId) {
        if (categoryClassService.getCategoryClass(categoryClassId) != null) {
            return ResponseEntity.ok(categoryClassService.detachCategoryClass(categoryClassId, userId));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Category class with id : " + String.valueOf(categoryClassId) + ", doesn't exists");
    }

    @DeleteMapping("/{categoryClassId}")
    public ResponseEntity<?> deleteCategoryClass(@PathVariable Long categoryClassId) {
        categoryClassService.deleteCategoryClass(categoryClassId);
        return ResponseEntity.ok(true);
    }
}
