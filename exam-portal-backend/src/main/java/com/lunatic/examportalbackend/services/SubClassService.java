package com.lunatic.examportalbackend.services;

import com.lunatic.examportalbackend.models.Subject;
import com.lunatic.examportalbackend.models.SubClass;
import com.lunatic.examportalbackend.models.User;

import java.util.List;

public interface SubClassService {

    SubClass addSubClass (SubClass subClass);

    List<SubClass> getSubClasses();

    List<SubClass> getSubClassBySubId(Subject subject);

    List<SubClass> getSubClassByUserId(User user);

//    List<SubClass> getSubClassByManagerId(Long managerId);

    SubClass getSubClass(Long subClassId);

    SubClass updateCategoryClass(SubClass subClass);

    Boolean joinSubClass(Long categoryClassId, Long userId);

    Boolean detachSubClass(Long categoryClassId, Long userId);

    void deleteSubClass(Long categoryClassId);
}
