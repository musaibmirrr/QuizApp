package com.telusko.quizapp.Service;

import com.telusko.quizapp.Dto.QuestionDTO.QuestionResponseDTO;
import com.telusko.quizapp.Dto.QuizDTO.QuizRequestDTO;
import com.telusko.quizapp.Dto.QuizDTO.QuizResponseDTO;
import com.telusko.quizapp.Model.Question;
import com.telusko.quizapp.Model.Quiz;
import com.telusko.quizapp.Repository.QuestionRepository;
import com.telusko.quizapp.Repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepository questionRepository;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> questions = questionRepository.findRandomQuestionByCategory(category,numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizRepository.save(quiz);

        return new ResponseEntity<>("Quiz created successfully", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionResponseDTO>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizRepository.findById(id);
         List<Question> questions = quiz.get().getQuestions();
         List<QuestionResponseDTO> questionForUser = new ArrayList<>();
         for(Question q : questions){
             QuestionResponseDTO questionResponseDTO = new QuestionResponseDTO(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(), q.getOption4(), q.getDifficultylevel(), q.getCategory());
             questionForUser.add(questionResponseDTO);
         }
         return new ResponseEntity<>(questionForUser,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateScore(Integer id, List<QuizRequestDTO> responses) {
        Quiz quiz = quizRepository.findById(id).get();
        List<Question> questions = quiz.getQuestions();
        int score = 0;
        int i = 0;
        for(QuizRequestDTO q : responses){
            if(q.getResponse().equals(questions.get(i).getRightAnswer())){
                score++;
            }
            i++;
        }
        return new ResponseEntity<Integer>(score,HttpStatus.OK);
    }
}
