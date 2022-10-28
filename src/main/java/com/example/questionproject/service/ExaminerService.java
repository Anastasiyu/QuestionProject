package com.example.questionproject.service;

import com.example.questionproject.model.Question;

import java.util.Collection;

public interface ExaminerService {

    Collection<Question> getQuestions(int amount);
}
