package com.telusko.quizapp.Controller;

import com.telusko.quizapp.Dto.QuestionDTO.QuestionRequestDTO;
import com.telusko.quizapp.Dto.QuestionDTO.QuestionResponseDTO;
import com.telusko.quizapp.Model.Question;
import com.telusko.quizapp.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("allquestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getAllQuestionsByCategory(@PathVariable String category) {
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("addquestion")
    public ResponseEntity<QuestionResponseDTO> addQuestion(@RequestBody QuestionRequestDTO question) {
        return questionService.addQuestion(question);
    }

    @PutMapping("updatequestion/{id}")
    public ResponseEntity<QuestionResponseDTO> updateQuestion(@PathVariable Integer id, @RequestBody QuestionRequestDTO question) {
        return questionService.updateQuestion(id, question);
    }

    @DeleteMapping("deletequestion/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Integer id) {
        return questionService.deleteQuestion(id);
    }
}
