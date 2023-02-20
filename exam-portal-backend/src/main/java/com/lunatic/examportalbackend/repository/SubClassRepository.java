package com.lunatic.examportalbackend.repository;

import com.lunatic.examportalbackend.models.Category;
import com.lunatic.examportalbackend.models.CategoryClass;
import com.lunatic.examportalbackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryClassRepository extends JpaRepository<CategoryClass, Long> {
    List<CategoryClass> findByCategory(Category category);
    List<CategoryClass> findByUsers(User user);
}
