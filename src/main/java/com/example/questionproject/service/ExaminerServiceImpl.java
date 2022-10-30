package com.example.questionproject.service;

import com.example.questionproject.exeption.IncorectAmountOfQuestion;
import com.example.questionproject.model.Question;
import com.example.questionproject.service.impl.JavaQuestionService;
import com.example.questionproject.service.impl.MathQuestionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService{

    private final QuestionService javaQuestionService;
    private final QuestionService mathQuestionService;
    private final Random random;
    public ExaminerServiceImpl( JavaQuestionService javaQuestionService,
                               MathQuestionService mathQuestionService) {
        this.javaQuestionService = javaQuestionService;
        this.mathQuestionService = mathQuestionService;
        this.random= new Random();
    }


    @Override
    public Collection<Question> getQuestions(int amount){
        List<QuestionService> list = List.of(javaQuestionService, mathQuestionService);

        int totalQuestions = list.stream()
                .map(QuestionService::getAll)
                .mapToInt(Collection::size)
                .sum();


        if (amount <=0 || amount > totalQuestions){
            throw new IncorectAmountOfQuestion();
        }
        Set<Question> questions = new HashSet<>();
        while (questions.size() < amount) {
            questions.add(list.get(random.nextInt(list.size())).getRandomQestion());
            }
            return questions;
        }
}




