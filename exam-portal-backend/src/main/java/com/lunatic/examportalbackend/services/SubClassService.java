package com.lunatic.examportalbackend.services;

import com.lunatic.examportalbackend.models.Category;
import com.lunatic.examportalbackend.models.CategoryClass;
import com.lunatic.examportalbackend.models.User;

import java.util.List;

public interface CategoryClassService {

    CategoryClass addCategoryClass (CategoryClass categoryClass);

    List<CategoryClass> getCategoryClasses();

    List<CategoryClass> getCategoryClassByCatId(Category category);

    List<CategoryClass> getCategoryClassByUserId(User user);

    CategoryClass getCategoryClass(Long catClassId);

    CategoryClass updateCategoryClass(CategoryClass categoryClass);

    Boolean joinCategoryClass(Long categoryClassId, Long userId);

    Boolean detachCategoryClass(Long categoryClassId, Long userId);

    void deleteCategoryClass(Long categoryClassId);
}
