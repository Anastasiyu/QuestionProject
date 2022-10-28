package com.example.questionproject;

import com.example.questionproject.exeption.IncorectAmountOfQuestion;
import com.example.questionproject.model.Question;
import com.example.questionproject.service.ExaminerServiceImpl;
import com.example.questionproject.service.QuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    private static final List<Question> QUESTIONS = List.of(
            new Question("Вопрос1", "Ответ1"),
            new Question("Вопрос2", "Ответ2"),
            new Question("Вопрос3", "Ответ3"),
            new Question("Вопрос4", "Ответ4"),
            new Question("Вопрос5", "Ответ5")
    );
    @BeforeEach
    public void beforeEach(){
        when(questionService.getAll()).thenReturn(QUESTIONS);
    }


    @ParameterizedTest
    @MethodSource("getQuestionsNegativeParams")
    public void getQuestionsNegativeTest(int incorrectAmount){
        assertThatExceptionOfType(IncorectAmountOfQuestion.class)
                .isThrownBy(()-> examinerService.getQuestions(incorrectAmount));

    }

    @Test
    public void getQuestionsPositiveTest(){
        when(questionService.getRandomQestion()).thenReturn(
                QUESTIONS.get(1),
                QUESTIONS.get(4),
                QUESTIONS.get(3),
                QUESTIONS.get(4),
                QUESTIONS.get(2)
        );
        assertThat(examinerService.getQuestions(4)).containsExactly(
                QUESTIONS.get(1),
                QUESTIONS.get(4),
                QUESTIONS.get(3),
                QUESTIONS.get(2)
        );
    }


    public static Stream<Arguments> getQuestionsNegativeParams(){
        return Stream.of(
                Arguments.of( -1),
                Arguments.of(QUESTIONS.size() +1),
                Arguments.of(QUESTIONS.size() +100)
        );
    }
}
