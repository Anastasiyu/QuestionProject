package com.example.questionproject.repository.impl;

import com.example.questionproject.exeption.QestionAlredyExistsExeption;
import com.example.questionproject.model.Question;
import com.example.questionproject.repository.QuestionRepository;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class JavaQuestionRepositoryTest {

    @ParameterizedTest
    @MethodSource("question1")


    @ParameterizedTest
    @MethodSource("question2")
    public void add1Test(Question question) {
        questionRepository.add(question);@ParameterizedTest
        @MethodSource("question1")
        public void add1Test(Question question) {
            questionRepository.add(question);