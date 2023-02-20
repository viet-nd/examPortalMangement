package com.lunatic.examportalbackend.repository;

import com.lunatic.examportalbackend.models.Subject;
import com.lunatic.examportalbackend.models.SubClass;
import com.lunatic.examportalbackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubClassRepository extends JpaRepository<SubClass, Long> {
    List<SubClass> findBySubject(Subject subject);
    List<SubClass> findByUsers(User user);
}
