package com.lunatic.examportalbackend.services.implementation;

import com.lunatic.examportalbackend.models.Category;
import com.lunatic.examportalbackend.models.CategoryClass;
import com.lunatic.examportalbackend.models.Role;
import com.lunatic.examportalbackend.models.User;
import com.lunatic.examportalbackend.repository.CategoryClassRepository;
import com.lunatic.examportalbackend.repository.CategoryRepository;
import com.lunatic.examportalbackend.repository.UserRepository;
import com.lunatic.examportalbackend.services.CategoryClassService;
import com.lunatic.examportalbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CategoryClassServiceImpl implements CategoryClassService {

    @Autowired
    private CategoryClassRepository categoryClassRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @Override
    public CategoryClass addCategoryClass(CategoryClass categoryClass) {
        return categoryClassRepository.save(categoryClass);
    }

    @Override
    public List<CategoryClass> getCategoryClasses() {
        return categoryClassRepository.findAll();
    }

    @Override
    public List<CategoryClass> getCategoryClassByCatId(Category category) {
        return categoryClassRepository.findByCategory(category);
    }

    @Override
    public List<CategoryClass> getCategoryClassByUserId(User user) {
        return categoryClassRepository.findByUsers(user);
    }

    @Override
    public CategoryClass getCategoryClass(Long catClassId) {
        return categoryClassRepository.findById(catClassId).isPresent() ? categoryClassRepository.findById(catClassId).get() : null;
    }

    @Override
    public CategoryClass updateCategoryClass(CategoryClass categoryClass) {
        return categoryClassRepository.save(categoryClass);
    }

    @Override
    public Boolean joinCategoryClass(Long categoryClassId, Long userId) {
        CategoryClass categoryClass = getCategoryClass(categoryClassId);
        User user = userService.getUser(userId);
        Set<User> categoryUser = categoryClass.getUsers() != null ? categoryClass.getUsers() : new HashSet<>();
        categoryUser.add(user);
        categoryClass.setUsers(categoryUser);

        return (categoryClassRepository.save(categoryClass) != null) ? true : false;
    }

    @Override
    public Boolean detachCategoryClass(Long categoryClassId, Long userId) {
        CategoryClass categoryClass = getCategoryClass(categoryClassId);
        User user = userService.getUser(userId);

        if (categoryClass.getUsers() == null) {
            return false;
        }
        Set<User> categoryUser = categoryClass.getUsers();
        categoryUser.remove(user);
        categoryClass.setUsers(categoryUser);

        return (categoryClassRepository.save(categoryClass) != null) ? true : false;
    }

    @Override
    public void deleteCategoryClass(Long categoryClassId) {
        categoryClassRepository.delete(getCategoryClass(categoryClassId));
    }
}
