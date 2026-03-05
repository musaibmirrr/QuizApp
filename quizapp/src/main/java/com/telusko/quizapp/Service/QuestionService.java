package com.telusko.quizapp.Service;

import com.telusko.quizapp.Dto.QuestionRequestDTO;
import com.telusko.quizapp.Dto.QuestionResponseDTO;
import com.telusko.quizapp.Model.Question;
import com.telusko.quizapp.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public List<Question> getQuestionsByCategory(String category) {
        return questionRepository.findByCategory(category);
    }

    public QuestionResponseDTO addQuestion(QuestionRequestDTO dto) {

        Question question = new Question();

        question.setQuestionTitle(dto.getQuestionTitle());
        question.setOption1(dto.getOption1());
        question.setOption2(dto.getOption2());
        question.setOption3(dto.getOption3());
        question.setOption4(dto.getOption4());
        question.setRightAnswer(dto.getRightAnswer());
        question.setDifficultylevel(dto.getDifficultylevel());
        question.setCategory(dto.getCategory());

        Question saved = questionRepository.save(question);

        QuestionResponseDTO response = new QuestionResponseDTO();

        response.setId(saved.getId());
        response.setQuestionTitle(saved.getQuestionTitle());
        response.setOption1(saved.getOption1());
        response.setOption2(saved.getOption2());
        response.setOption3(saved.getOption3());
        response.setOption4(saved.getOption4());
        response.setDifficultylevel(saved.getDifficultylevel());
        response.setCategory(saved.getCategory());

        return response;
    }
}
