package com.lunatic.examportalbackend.services.implementation;

import com.lunatic.examportalbackend.models.SubClass;
import com.lunatic.examportalbackend.models.User;
import com.lunatic.examportalbackend.repository.SubClassRepository;
import com.lunatic.examportalbackend.repository.UserRepository;
import com.lunatic.examportalbackend.services.SubClassService;
import com.lunatic.examportalbackend.services.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Query;
import javax.servlet.ServletContext;
import javax.transaction.Transactional;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SubClassRepository subClassRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User getUser(Long userId) {
        return userRepository.findById(userId).isPresent() ? userRepository.findById(userId).get() : null;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getUserByKeyword(String keyword) {
        Session session = sessionFactory.openSession();
        List<User> results = new ArrayList<>();

        try {
            Query query = session.createNativeQuery("SELECT * FROM users WHERE" +
                    " full_name like :word" +
                    " or username like :word" +
                    " or phone_number like :word", User.class);
            query.setParameter("word", "%" + keyword + "%");
            results = query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }

    @Override
    public User updateUser(User newUser) {
        User oldUser = getUser(newUser.getUserId());
        newUser.setPassword(oldUser.getPassword());

        return userRepository.save(newUser);
    }

    @Override
    public User updatePassword(Long userId, String oldPassword, String newPassword) {
        User user = getUser(userId);

        if (oldPassword.equals("AdminChangePassword")) {
            user.setPassword(passwordEncoder.encode(newPassword));
            return userRepository.save(user);
        } else if (passwordEncoder.matches(oldPassword,user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            return userRepository.save(user);
        }

        return null;
    }

    @Override
    public void deleteUser(Long userId) {
        User user = getUser(userId);

        List<SubClass> subClasses = user.getSubClasses().stream().toList();

        if (!subClasses.isEmpty()) {
            for (int i = 0; i < subClasses.size(); i++) {
                Set<User> subClassUsers = subClasses.get(i).getUsers();
                subClassUsers.remove(user);
                subClasses.get(i).setUsers(subClassUsers);
                subClassRepository.save(subClasses.get(i));
            }
        }

        userRepository.delete(user);
    }
}
