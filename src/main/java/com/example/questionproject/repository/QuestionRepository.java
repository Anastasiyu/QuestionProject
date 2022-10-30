package com.example.questionproject.repository;

import com.example.questionproject.model.Question;

import java.util.Collection;

public interface QuestionRepository {

    boolean add(Question question);

    boolean remove(Question question);

    Collection<Question> getAll();
}
