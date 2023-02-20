package com.lunatic.examportalbackend.services.implementation;

import com.lunatic.examportalbackend.models.Question;
import com.lunatic.examportalbackend.models.Quiz;
import com.lunatic.examportalbackend.repository.QuestionRepository;
import com.lunatic.examportalbackend.repository.QuizRepository;
import com.lunatic.examportalbackend.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionRepository questionRepository;
//    @Autowired
//    private QuizRepository quizRepository;
//    @Autowired
//    ServletContext context;

    @Override
    public Question addQuestion(Question question) {
        return questionRepository.save(question);
    }

//    @Override
//    public Question addQuestion(Question question, List<MultipartFile> imageList) {
//        Quiz quiz = question.getQuiz();
//        String imagesRootPath = context.getRealPath("images/quiz/" + quiz.getQuizId());
//        if (imageList.size() != 0) {
//            for (int i = 0; i < imageList.size(); i++) {
//                File imagesRootDir = new File(imagesRootPath);
//                if (!imagesRootDir.exists()) {
//                    imagesRootDir.mkdirs();
//                }
//                try {
//                    String imageName = i + "." + imageList.get(i).getOriginalFilename();
//                    File serverFile = new File(imagesRootDir.getAbsoluteFile() + File.separator + imageName);
//                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
//                    stream.write(imageList.get(i).getBytes());
//                    stream.close();
//                    question.setImage(serverFile.getPath());
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
//            }
//        }
//
//        return questionRepository.save(question);
//    }
    @Override
    public List<Question> getQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public Question getQuestion(Long quesId) {
        return questionRepository.findById(quesId).isPresent() ? questionRepository.findById(quesId).get() : null;
    }

    @Override
    public Question updateQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public void deleteQuestion(Long questionId) {
        questionRepository.delete(getQuestion(questionId));
    }

    @Override
    public List<Question> getQuestionsByQuiz(Quiz quiz) {
        return questionRepository.findByQuiz(quiz);
    }
}
