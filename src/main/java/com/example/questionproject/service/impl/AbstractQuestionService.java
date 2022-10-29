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

public abstract class AbstractQuestionService implements QuestionService {

    private final QuestionRepository questionRepository;
    private final Random random = new Random(); // объявление рандома полем


    public AbstractQuestionService (QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question add(String question, String answer){

        return add(new Question(question, answer));
    }
    @Override
    public Question add(Question question) {
        if (!questionRepository.add(question)) {   //проверяет есть ли уже добовляемый вопрос
            throw new QestionAlredyExistsExeption();
        }
        return question;
    }

    @Override
    public Question remove(Question question){
        if (!questionRepository.remove(question)) {   //проверяет есть ли вопрос
            throw new QuestionNotFoundExeption(); // вопрос не найден
        }
        return question;
    }
    @Override
    public Collection<Question> getAll(){

        return questionRepository.getAll();
    }

    @Override
    public Question getRandomQestion(){
        Collection<Question> questions = questionRepository.getAll();
        if(questions.isEmpty()){
            return null;
        }

        return questions.stream().skip(random.nextInt(questions.size())).findFirst().orElse(null);

    }
}
