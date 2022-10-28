package com.example.questionproject.service.impl;

import com.example.questionproject.service.QuestionService;
import com.example.questionproject.exeption.QestionAlredyExistsExeption;
import com.example.questionproject.exeption.QuestionNotFoundExeption;
import com.example.questionproject.model.Question;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQestionService implements QuestionService {

    private final Set<Question> questions= new HashSet<>();
    private final Random random = new Random(); // объявление рандома полем

    @Override
    public Question add(String question, String answer){
        return add(new Question(question, answer));
    }
    @Override
    public Question add(Question question) {
        if (!questions.add(question)) {   //проверяет есть ли уже добовляемый вопрос
            throw new QestionAlredyExistsExeption();
        }
        return question;
    }

    @Override
    public Question remove(Question question){
        if (!questions.remove(question)) {   //проверяет есть ли вопрос
            throw new QuestionNotFoundExeption(); // вопрос не найден
        }
        return question;
    }
    @Override
    public Collection<Question> getAll(){
        return new HashSet<>(questions);
    }

    @Override
    public Question getRandomQestion(){
        if(questions.isEmpty()){
            return null;
        }

        return questions.stream().skip(random.nextInt(questions.size())).findFirst().orElse(null);

    }
}
