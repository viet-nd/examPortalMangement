package com.lunatic.examportalbackend.repository;

import com.lunatic.examportalbackend.models.SubClass;
import com.lunatic.examportalbackend.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    List<Quiz> findBySubClass(SubClass subClass);
}
