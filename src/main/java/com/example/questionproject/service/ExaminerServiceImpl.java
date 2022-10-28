package com.example.questionproject.service;

import com.example.questionproject.exeption.IncorectAmountOfQuestion;
import com.example.questionproject.model.Question;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService{

    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount){

        if (amount <=0 || amount > questionService.getAll().size()){
            throw new IncorectAmountOfQuestion();
        }
        Set<Question> questions = new HashSet<>();
        while (questions.size() < amount) {
            questions.add(questionService.getRandomQestion());
            }
            return questions;
        }
}




