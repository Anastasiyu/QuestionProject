package com.example.questionproject.repository.impl;

import com.example.questionproject.model.Question;
import com.example.questionproject.repository.QuestionRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Repository
public class MathQuestionRepository extends AbstractQuestionRepository {

    @Override
    public void init() {
        add(new Question("Math Вопрос1", "Math ответ1"));
        add(new Question("Math Вопрос2", "Math ответ2"));
        add(new Question("Math Вопрос3", "Math ответ3"));
        add(new Question("Math Вопрос4", "Math ответ4"));
        add(new Question("Math Вопрос5", "Math ответ5"));
    }
}
