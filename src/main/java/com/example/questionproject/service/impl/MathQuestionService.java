package com.example.questionproject.service.impl;

import com.example.questionproject.exeption.QestionAlredyExistsExeption;
import com.example.questionproject.exeption.QuestionNotFoundExeption;
import com.example.questionproject.model.Question;
import com.example.questionproject.repository.QuestionRepository;
import com.example.questionproject.service.QuestionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Random;
@Service
public class MathQuestionService extends AbstractQuestionService {


    public MathQuestionService(@Qualifier("mathQuestionRepository") QuestionRepository questionRepository) {
        super (questionRepository);
    }

}
