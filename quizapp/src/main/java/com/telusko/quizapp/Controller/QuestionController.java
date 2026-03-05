package com.telusko.quizapp.Controller;

import com.telusko.quizapp.Dto.QuestionRequestDTO;
import com.telusko.quizapp.Dto.QuestionResponseDTO;
import com.telusko.quizapp.Model.Question;
import com.telusko.quizapp.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("allquestions")
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public List<Question> getAllQuestionsByCategory(@PathVariable String category) {
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("addquestion")
    public ResponseEntity<QuestionResponseDTO> addQuestion(@RequestBody QuestionRequestDTO question) {
    QuestionResponseDTO savedQuestion = questionService.addQuestion(question);
    return  ResponseEntity.status(HttpStatus.CREATED).body(savedQuestion);
    }
}
