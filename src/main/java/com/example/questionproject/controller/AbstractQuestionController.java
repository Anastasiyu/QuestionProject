package com.example.questionproject.controller;

import com.example.questionproject.model.Question;
import com.example.questionproject.service.QuestionService;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.Collection;

public abstract class AbstractQuestionController {
    private final QuestionService questionService;

    public AbstractQuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/add")
    public Question addQuestion( String question,
                                 String answer){
        return questionService.add(question,answer);

    }

    @GetMapping("/remove")
    public Question removeQuestion( String question,
                                    String answer){
        return questionService.remove(new Question(question,answer));

    }

    @GetMapping
    public Collection<Question> getQuestions(){
        return questionService.getAll();

    }
}
