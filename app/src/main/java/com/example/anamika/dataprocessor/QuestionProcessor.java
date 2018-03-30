package com.example.anamika.dataprocessor;

import java.util.ArrayList;
import java.util.List;

import com.example.anamika.model.Question;
import com.example.anamika.resource.AllQuestions;


/**
 * Created by tahmid on 30-Sep-17.
 */

public class QuestionProcessor {

    private List<Question> questions;

    public QuestionProcessor() {
        questions = new ArrayList<>();

        for (int i = 0; i < AllQuestions.ALL_QUESTION.length; i++) {

            if (AllQuestions.ALL_QUESTION[i].contains("Question:")) {

                String ques = AllQuestions.ALL_QUESTION[i].substring(10);
                String[] options = new String[]{};

                if (i + 1 < AllQuestions.ALL_QUESTION.length && !AllQuestions.ALL_QUESTION[i + 1].contains("Question:")) {
                    options = new String[]{
                            AllQuestions.ALL_QUESTION[i + 1]
                    };
                    i += 1;
                } else {
                    addQuestion(ques, options);

                    continue;
                }
                if (i + 1 < AllQuestions.ALL_QUESTION.length && !AllQuestions.ALL_QUESTION[i + 1].contains("Question:")) {
                    options = new String[]{
                            AllQuestions.ALL_QUESTION[i],
                            AllQuestions.ALL_QUESTION[i + 1]
                    };
                    i += 1;
                } else {
                    addQuestion(ques, options);

                    continue;
                }
                if (i + 1 < AllQuestions.ALL_QUESTION.length && !AllQuestions.ALL_QUESTION[i + 1].contains("Question:")) {
                    options = new String[]{
                            AllQuestions.ALL_QUESTION[i - 1],
                            AllQuestions.ALL_QUESTION[i],
                            AllQuestions.ALL_QUESTION[i + 1]
                    };
                    i += 1;
                } else {
                    addQuestion(ques, options);

                    continue;
                }
                if (i + 1 < AllQuestions.ALL_QUESTION.length && !AllQuestions.ALL_QUESTION[i + 1].contains("Question:")) {
                    options = new String[]{
                            AllQuestions.ALL_QUESTION[i - 2],
                            AllQuestions.ALL_QUESTION[i - 1],
                            AllQuestions.ALL_QUESTION[i],
                            AllQuestions.ALL_QUESTION[i + 1]
                    };
                    i += 1;
                } else {
                    addQuestion(ques, options);

                    continue;
                }
                if (i + 1 < AllQuestions.ALL_QUESTION.length && !AllQuestions.ALL_QUESTION[i + 1].contains("Question:")) {
                    options = new String[]{
                            AllQuestions.ALL_QUESTION[i - 3],
                            AllQuestions.ALL_QUESTION[i - 2],
                            AllQuestions.ALL_QUESTION[i - 1],
                            AllQuestions.ALL_QUESTION[i],
                            AllQuestions.ALL_QUESTION[i + 1]
                    };
                    i += 1;
                } else {
                    addQuestion(ques, options);

                    continue;
                }
                addQuestion(ques, options);

            }
        }
    }

    private void addQuestion(String body, String... options) {
        Question question = new Question(body, options);
        questions.add(question);
    }

    public List<Question> getQuestions() {
        return questions;
    }
}
