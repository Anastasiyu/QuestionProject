package com.example.questionproject.controller;

import com.example.questionproject.model.Question;
import com.example.questionproject.service.QuestionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
@RestController
@RequestMapping("/math")
public class MathQuestionController extends AbstractQuestionController{

    public MathQuestionController(@Qualifier("mathQuestionService") QuestionService questionService) {
        super(questionService);
    }

    @GetMapping("/add")
    public Question addQuestion(@RequestParam String question,
                                @RequestParam String answer){
        return super.addQuestion(question,answer);

    }

    @GetMapping("/remove")
    public Question removeQuestion(@RequestParam String question,
                                   @RequestParam String answer){
        return super.removeQuestion(question,answer);

    }

    @GetMapping
    public Collection<Question> getQuestions(){
        return super.getQuestions();

    }
}
