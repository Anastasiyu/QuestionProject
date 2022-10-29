package com.example.questionproject.service.impl;

import com.example.questionproject.exeption.QestionAlredyExistsExeption;
import com.example.questionproject.exeption.QuestionNotFoundExeption;
import com.example.questionproject.model.Question;
import com.example.questionproject.service.QuestionService;
import com.example.questionproject.service.impl.JavaQuestionService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class JavaQestionServiceTest {
    public final QuestionService questionService = new JavaQuestionService();

    @AfterEach
    public void  cleanUp(){
        questionService.getAll().forEach(questionService::remove);
    }

    @Test
    public void addPositiveTest(){
        assertThat(questionService.getAll()).isEmpty(); // проверяет что пусто
        Question question1 = new Question("Вопрос1", "Ответ1");
        Question question2 = new Question("Вопрос2", "Ответ2");
        questionService.add(question1.getQuestion(),question1.getAnswer()); //добавляем
        assertThat(questionService.getAll())
                .hasSize(1)
                .containsOnly(question1);

        questionService.add(question2);
        assertThat(questionService.getAll())
                .hasSize(2)
                .containsOnly(question1,question2);
    }
    @Test
    public void addNegativeTest() {
        Question question = new Question("Вопрос1", "Ответ1");
       add(question);
        assertThatExceptionOfType(QestionAlredyExistsExeption.class)      //проверяем что вопрос уже есть
                .isThrownBy(() -> questionService.add(question));
    }

    @Test
    public void removePositiveTest(){
        Question question = new Question("Вопрос1", "Ответ1");
        add(question);

        questionService.remove(question);
        assertThat(questionService.getAll()).isEmpty();
    }

    @Test
    public void removeNegativeTest(){
        Question question = new Question("Вопрос1", "Ответ1");
        assertThatExceptionOfType(QuestionNotFoundExeption.class)
                .isThrownBy(()->questionService.remove(question));
        add(question);
        assertThatExceptionOfType(QuestionNotFoundExeption.class)
                .isThrownBy(()->questionService.remove(new Question("Вопрос2", "Ответ2")));
    }
    @Test
    public void getRandomQestionPositiveTest(){
        for (int i = 1; i <=5 ; i++) {
            add(new Question("Вопрос2" +i, "Ответ2" +i));
        }
        assertThat(questionService.getRandomQestion()).isIn(questionService.getAll());
    }

    @Test
    public void getRandomQestionNegativeTest(){
        assertThat(questionService.getAll()).isEmpty();

        assertThat(questionService.getRandomQestion()).isNull();
    }
     private void add(Question question){
        int sizeBefore = questionService.getAll().size();
         questionService.add(question.getQuestion(),question.getAnswer()); //добавляем
         assertThat(questionService.getAll())
                 .hasSize(sizeBefore+1)
                 .contains(question);

     }


}
