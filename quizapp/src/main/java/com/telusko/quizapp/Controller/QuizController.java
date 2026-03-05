package com.telusko.quizapp.Controller;

import com.telusko.quizapp.Dto.QuestionDTO.QuestionResponseDTO;
import com.telusko.quizapp.Dto.QuizDTO.QuizRequestDTO;
import com.telusko.quizapp.Dto.QuizDTO.QuizResponseDTO;
import com.telusko.quizapp.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category,@RequestParam int numQ, @RequestParam String title) {
        return quizService.createQuiz(category,numQ,title);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionResponseDTO>> getQuizQuestions(@PathVariable Integer id) {
          return quizService.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<QuizRequestDTO> responses){
        return quizService.calculateScore(id,responses);
    }

}
