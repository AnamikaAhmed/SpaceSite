package com.example.anamika.bot;

import android.util.Log;

import com.stfalcon.chatkit.messages.MessageInput;
import com.stfalcon.chatkit.messages.MessagesListAdapter;

import java.util.List;

import com.example.anamika.dataprocessor.QuestionProcessor;
import com.example.anamika.model.Message;
import com.example.anamika.model.Question;


/**
 * Created by tahmid on 30-Sep-17.
 */

public class QuestionServer {

    private static int questionIndex;
    private static List<Question> questions;
    private static QuestionServer questionServer;

    private QuestionServer() {
        questionIndex = 0;
        questions = new QuestionProcessor().getQuestions();
    }

    public Question serve(long millisInFuture, final MessagesListAdapter<Message> messageMessagesListAdapter, final MessageInput messageInput) {
        if (questionIndex >= questions.size()) {
            Log.d("Sohana", "Last Question");
            AddResponse.addResponse(3000L, "No more questions!", messageMessagesListAdapter, messageInput, "Typing disabled...");
        } else {
            AddResponse.addResponse(millisInFuture, questions.get(questionIndex), messageMessagesListAdapter, messageInput);
            return questions.get(questionIndex++);
        }
        return null;
    }

    public boolean canServerServeNewQuestion() {
        return questionIndex < questions.size();
    }

    public static QuestionServer getInstance() {
        if (questionServer == null) {
            questionServer = new QuestionServer();
        }
        return questionServer;
    }
}
