package com.example.questionproject.service.impl;

import com.example.questionproject.exeption.QestionAlredyExistsExeption;
import com.example.questionproject.exeption.QuestionNotFoundExeption;
import com.example.questionproject.model.Question;
import com.example.questionproject.repository.impl.JavaQuestionRepository;
import com.example.questionproject.service.QuestionService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JavaQestionServiceTest {

    @Mock
    private JavaQuestionRepository javaQuestionRepository;

    @InjectMocks
    private JavaQuestionService javaQuestionService;

    @ParameterizedTest
    @MethodSource("question1")
    public void add1Test(Question question) {
        when(javaQuestionRepository.add(question)).thenReturn(true);
        javaQuestionService.add(question);

        when(javaQuestionRepository.add(question)).thenThrow(new QestionAlredyExistsExeption());
        assertThatExceptionOfType(QestionAlredyExistsExeption.class)
                .isThrownBy(() -> javaQuestionService.add(question));
        when(javaQuestionRepository.getAll()).thenReturn(Collections.singleton(question));
        assertThat(javaQuestionService.getAll()).containsExactlyInAnyOrder(question);
    }


    @ParameterizedTest
    @MethodSource("question2")
    public void add2Test(String question, String answer) {
        Question q = new Question(question, answer);
        when(javaQuestionRepository.add(new Question(question, answer))).thenReturn(true);
        javaQuestionService.add(question, answer);

        when(javaQuestionRepository.add(new Question(question, answer))).thenThrow(new QestionAlredyExistsExeption());
        assertThatExceptionOfType(QestionAlredyExistsExeption.class)
                .isThrownBy(() -> javaQuestionService.add(question, answer));
        when(javaQuestionRepository.getAll()).thenReturn(Collections.singleton(q));
        assertThat(javaQuestionService.getAll()).containsExactlyInAnyOrder(q);

    }


    @ParameterizedTest
    @MethodSource("questions1")
    public void removeTest(Question question) {
        when(javaQuestionRepository.add(question)).thenReturn(true);
        javaQuestionService.add(question);

        when(javaQuestionRepository.remove(question)).thenReturn(true);
        javaQuestionService.remove(question);

        when(javaQuestionRepository.remove(question)).thenThrow(new QuestionNotFoundExeption());
        assertThatExceptionOfType(QuestionNotFoundExeption.class).isThrownBy(() -> javaQuestionService.remove(question));


    }

    @ParameterizedTest
    @MethodSource("questions")
    public void getRandomQuestionTest(Set<Question> questions) {
        when(javaQuestionRepository.getAll()).thenReturn(questions);

        assertThat(javaQuestionService.getRandomQestion()).isIn(javaQuestionService.getAll());
    }

    public static Stream<Arguments> question1() {
        return Stream.of(
                Arguments.of("Question", "Answer")
        );
    }


    public static Stream<Arguments> question2() {
        return Stream.of(
                Arguments.of("Question", "Answer")
        );
    }

    public static Stream<Arguments> questions() {
        return Stream.of(
                Arguments.of(
                        Set.of(new Question("Вопрос по Java 1", "Ответ по Java 1"),
                                new Question("Вопрос по Java 2", "Ответ по Java 2"),
                                new Question("Вопрос по Java 1", "Ответ по Java 1"),
                                new Question("Вопрос по Java 1", "Ответ по Java 1"),
                                new Question("Вопрос по Java 3", "Ответ по Java 3")

                        )
                ));
    }


}
