package com.telusko.quizapp.Service;

import com.telusko.quizapp.Dto.QuestionRequestDTO;
import com.telusko.quizapp.Dto.QuestionResponseDTO;
import com.telusko.quizapp.Model.Question;
import com.telusko.quizapp.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            List<Question> questions = questionRepository.findAll();
            if (questions.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            if (category == null || category.trim().isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            List<Question> questions = questionRepository.findByCategory(category);
            if (questions.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<QuestionResponseDTO> addQuestion(QuestionRequestDTO dto) {
        try {
            if (dto == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

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

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<QuestionResponseDTO> updateQuestion(Integer id, QuestionRequestDTO dto) {
        try {
            if (id == null || id <= 0) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (dto == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            Question question = questionRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Question not found with ID: " + id));

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

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> deleteQuestion(Integer id) {
        try {
            if (id == null || id <= 0) {
                return new ResponseEntity<>("Invalid question ID", HttpStatus.BAD_REQUEST);
            }
            if (!questionRepository.existsById(id)) {
                return new ResponseEntity<>("Question not found with ID: " + id, HttpStatus.NOT_FOUND);
            }

            questionRepository.deleteById(id);
            return new ResponseEntity<>("Question deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
