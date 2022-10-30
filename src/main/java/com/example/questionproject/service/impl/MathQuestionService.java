package com.example.questionproject.service.impl;

import com.example.questionproject.repository.QuestionRepository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MathQuestionService extends AbstractQuestionService {


    public MathQuestionService(@Qualifier("mathQuestionRepository") QuestionRepository questionRepository) {
        super (questionRepository);
    }

}
