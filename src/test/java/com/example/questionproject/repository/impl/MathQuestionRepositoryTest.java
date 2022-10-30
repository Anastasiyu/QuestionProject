package com.example.questionproject.repository.impl;

import com.example.questionproject.exeption.QestionAlredyExistsExeption;
import com.example.questionproject.exeption.QuestionNotFoundExeption;
import com.example.questionproject.model.Question;
import com.example.questionproject.repository.QuestionRepository;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class MathQuestionRepositoryTest {
    public final QuestionRepository questionRepository = new MathQuestionRepository();

    @ParameterizedTest
    @MethodSource("question1")
    public void add1Test(Question question) {
        questionRepository.add(question);
        //assertThatExceptionOfType(QestionAlredyExistsExeption.class)
       //         .isThrownBy(() -> questionRepository.add(question));
        assertThat(questionRepository.getAll()).containsExactlyInAnyOrder(question);
    }


    @ParameterizedTest
    @MethodSource("question2")
    public void add2Test(String question, String answer) {
        questionRepository.add(new Question(question, answer));
       /* assertThatExceptionOfType(QestionAlredyExistsExeption.class)
                .isThrownBy(() -> questionRepository.add(new Question(question, answer)));*/
        assertThat(questionRepository.getAll()).containsExactlyInAnyOrder(new Question(question, answer));

    }


    @ParameterizedTest
    @MethodSource("question1")
    public void removeTest(Question question) {
        questionRepository.add(question);
        questionRepository.remove(question);
        assertThat(questionRepository.getAll()).isEmpty();
        assertThatExceptionOfType(QuestionNotFoundExeption.class).isThrownBy(() -> questionRepository.remove(question));

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
}
