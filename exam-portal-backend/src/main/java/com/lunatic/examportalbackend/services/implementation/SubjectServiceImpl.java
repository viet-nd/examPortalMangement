package com.lunatic.examportalbackend.services.implementation;

import com.lunatic.examportalbackend.models.Subject;
import com.lunatic.examportalbackend.repository.SubjectRepository;
import com.lunatic.examportalbackend.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public Subject addSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public List<Subject> getSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject getSubject(Long subjectId) {
        return subjectRepository.findById(subjectId).isPresent() ? subjectRepository.findById(subjectId).get() : null;
    }

    @Override
    public Subject updateSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public void deleteSubject(Long subjectId) {
        subjectRepository.delete(getSubject(subjectId));
    }
}
