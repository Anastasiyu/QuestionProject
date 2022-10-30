package com.example.questionproject.service.impl;

import com.example.questionproject.exeption.QestionAlredyExistsExeption;
import com.example.questionproject.exeption.QuestionNotFoundExeption;
import com.example.questionproject.model.Question;
import com.example.questionproject.repository.QuestionRepository;
import com.example.questionproject.repository.impl.JavaQuestionRepository;
import com.example.questionproject.repository.impl.MathQuestionRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

public class MathQuestionServiceTest {

    @ExtendWith(MockitoExtension.class)
    public class MathQestionServiceTest {

        @Mock
        private MathQuestionRepository mathQuestionRepository;

        @InjectMocks
        private MathQuestionService mathQuestionService;

        @ParameterizedTest
        @MethodSource("question1")
        public void add1Test(Question question) {
            when(mathQuestionRepository.add(question)).thenReturn(true);
            mathQuestionService.add(question);

            when(mathQuestionRepository.add(question)).thenThrow(new QestionAlredyExistsExeption());
            assertThatExceptionOfType(QestionAlredyExistsExeption.class)
                    .isThrownBy(() -> mathQuestionService.add(question));
            when(mathQuestionRepository.getAll()).thenReturn(Collections.singleton(question));
            assertThat(mathQuestionService.getAll()).containsExactlyInAnyOrder(question);
        }


        @ParameterizedTest
        @MethodSource("question2")
        public void add2Test(String question, String answer) {
            Question q = new Question(question, answer);
            when(mathQuestionRepository.add(new Question(question, answer))).thenReturn(true);
            mathQuestionService.add(question, answer);

            when(mathQuestionRepository.add(new Question(question, answer))).thenThrow(new QestionAlredyExistsExeption());
            assertThatExceptionOfType(QestionAlredyExistsExeption.class)
                    .isThrownBy(() -> mathQuestionService.add(question, answer));
            when(mathQuestionRepository.getAll()).thenReturn(Collections.singleton(q));
            assertThat(mathQuestionService.getAll()).containsExactlyInAnyOrder(q);

        }


        @ParameterizedTest
        @MethodSource("questions1")
        public void removeTest(Question question) {
            when(mathQuestionRepository.add(question)).thenReturn(true);
            mathQuestionService.add(question);

            when(mathQuestionRepository.remove(question)).thenReturn(true);
            mathQuestionService.remove(question);

            when(mathQuestionRepository.remove(question)).thenThrow(new QuestionNotFoundExeption());
            assertThatExceptionOfType(QuestionNotFoundExeption.class).isThrownBy(() -> mathQuestionService.remove(question));


        }

        @ParameterizedTest
        @MethodSource("questions")
        public void getRandomQuestionTest(Set<Question> questions) {
            when(mathQuestionRepository.getAll()).thenReturn(questions);

            assertThat(mathQuestionService.getRandomQestion()).isIn(mathQuestionService.getAll());
        }

        public Stream<Arguments> question1() {
            return Stream.of(
                    Arguments.of("Question", "Answer")
            );
        }


        public  Stream<Arguments> question2() {
            return Stream.of(
                    Arguments.of("Question", "Answer")
            );
        }

        public  Stream<Arguments> questions() {
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
    }
