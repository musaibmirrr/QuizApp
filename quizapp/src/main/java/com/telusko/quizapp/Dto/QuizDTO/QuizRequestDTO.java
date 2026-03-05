package com.telusko.quizapp.Dto.QuizDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizRequestDTO {
    private Integer id;
    private String response;
}
