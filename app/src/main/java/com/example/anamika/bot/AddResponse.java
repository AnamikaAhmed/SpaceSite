package com.example.anamika.bot;

import com.stfalcon.chatkit.messages.MessageInput;
import com.stfalcon.chatkit.messages.MessagesListAdapter;

import java.util.Date;

import com.example.anamika.model.Message;
import com.example.anamika.model.Question;
import com.example.anamika.model.User;

/**
 * Created by tahmid on 30-Sep-17.
 */

public class AddResponse {

    private static User bot;
    private static TimerExecution timerExecution;

    public static void addResponse(long millisInFuture, String text, MessagesListAdapter<Message> messageMessagesListAdapter, MessageInput messageInput, String editTextHint) {
        bot = BotSingleton.createBot();
        Message msg = new Message(bot.getId(), text, bot, new Date());
        timerExecution = new TimerExecution(millisInFuture, 200L, msg, messageMessagesListAdapter, messageInput, editTextHint);
        timerExecution.start();
    }

    public static void addResponse(long millisInFuture, Question question, MessagesListAdapter<Message> messageMessagesListAdapter, MessageInput messageInput) {
        String[] options = question.getOptions();
        String text = question.getBody() + "\n\n";

        for (int i = 0; i < options.length; i++) {
            if (i == 0) {
                text += "A. " + options[i] + "\n";
            } else if (i == 1) {
                text += "B. " + options[i] + "\n";
            } else if (i == 2) {
                text += "C. " + options[i] + "\n";
            } else if (i == 3) {
                text += "D. " + options[i] + "\n";
            } else if (i == 4) {
                text += "E. " + options[i] + "\n";
            }
        }

        addResponse(millisInFuture, text, messageMessagesListAdapter, messageInput, question.getEditTextHint());
    }
}
