package com.lunatic.examportalbackend.services;

import com.lunatic.examportalbackend.models.Subject;

import java.util.List;

public interface SubjectService {

    Subject addSubject(Subject subject);

    List<Subject> getSubjects();

    Subject getSubject(Long subjectId);

    Subject updateSubject(Subject subject);

    void deleteSubject(Long subjectId);
}
