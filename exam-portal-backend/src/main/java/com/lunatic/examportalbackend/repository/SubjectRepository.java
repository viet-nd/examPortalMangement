package com.lunatic.examportalbackend.repository;


import com.lunatic.examportalbackend.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
