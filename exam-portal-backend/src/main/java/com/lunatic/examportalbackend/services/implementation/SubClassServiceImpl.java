package com.lunatic.examportalbackend.services.implementation;

import com.lunatic.examportalbackend.models.Subject;
import com.lunatic.examportalbackend.models.SubClass;
import com.lunatic.examportalbackend.models.User;
import com.lunatic.examportalbackend.repository.SubClassRepository;
import com.lunatic.examportalbackend.repository.SubjectRepository;
import com.lunatic.examportalbackend.repository.UserRepository;
import com.lunatic.examportalbackend.services.SubClassService;
import com.lunatic.examportalbackend.services.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SubClassServiceImpl implements SubClassService {

    @Autowired
    private SubClassRepository subClassRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @Override
    public SubClass addSubClass(SubClass subClass) {
        return subClassRepository.save(subClass);
    }

    @Override
    public List<SubClass> getSubClasses() {
        return subClassRepository.findAll();
    }

    @Override
    public List<SubClass> getSubClassBySubId(Subject subject) {
        return subClassRepository.findBySubject(subject);
    }

    @Override
    public List<SubClass> getSubClassByUserId(User user) {
        return subClassRepository.findByUsers(user);
    }

//    @Override
//    public List<SubClass> getSubClassByManagerId(Long managerId) {
//        Session session = sessionFactory.openSession();
//        List<SubClass> results = new ArrayList<>();
//
//        try {
//            Query query = session.createNativeQuery("SELECT * FROM subject_class WHERE" +
//                    " create_by like :word", SubClass.class);
//            query.setParameter("word", "%" + managerId + "%");
//            results = query.getResultList();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        } finally {
//            session.close();
//        }
//        return results;
//    }

    @Override
    public SubClass getSubClass(Long subClassId) {
        return subClassRepository.findById(subClassId).isPresent() ? subClassRepository.findById(subClassId).get() : null;
    }

    @Override
    public SubClass updateCategoryClass(SubClass subClass) {
        return subClassRepository.save(subClass);
    }

    @Override
    public Boolean joinSubClass(Long subClassId, Long userId) {
        SubClass subClass = getSubClass(subClassId);
        User user = userService.getUser(userId);
        Set<User> users = subClass.getUsers() != null ? subClass.getUsers() : new HashSet<>();
        boolean result = users.add(user);
        subClass.setUsers(users);
        subClassRepository.save(subClass);

        return result;
    }

    @Override
    public Boolean detachSubClass(Long subClassId, Long userId) {
        SubClass subClass = getSubClass(subClassId);
        User user = userService.getUser(userId);

        if (subClass.getUsers() == null) {
            return false;
        }
        Set<User> subClassUsers = subClass.getUsers();
        boolean result = subClassUsers.remove(user);
        subClass.setUsers(subClassUsers);
        subClassRepository.save(subClass);
        return result;
    }

    @Override
    public void deleteSubClass(Long subClassId) {
        subClassRepository.delete(getSubClass(subClassId));
    }
}
