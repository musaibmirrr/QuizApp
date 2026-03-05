package com.telusko.quizapp.Repository;

import com.telusko.quizapp.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findByCategory(String catergory);
}
