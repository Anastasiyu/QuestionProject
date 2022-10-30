package com.example.questionproject.repository.impl;

import com.example.questionproject.model.Question;

import org.springframework.stereotype.Repository;



@Repository
public class JavaQuestionRepository extends AbstractQuestionRepository {

    @Override
    public void init() {
        add(new Question("Java Вопрос1", "Java ответ1"));
        add(new Question("Java Вопрос2", "Java ответ2"));
        add(new Question("Java Вопрос3", "Java ответ3"));
        add(new Question("Java Вопрос4", "Java ответ4"));
        add(new Question("Java Вопрос5", "Java ответ5"));
    }
}
