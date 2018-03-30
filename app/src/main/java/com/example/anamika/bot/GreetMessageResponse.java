package com.example.anamika.bot;
import android.widget.ProgressBar;

import com.stfalcon.chatkit.messages.MessageInput;
import com.stfalcon.chatkit.messages.MessagesListAdapter;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import com.example.anamika.model.Message;


/**
 * Created by tahmid on 01-Oct-17.
 */

public class GreetMessageResponse {

    private static TimerExecution timerExecution;
    private static final long TIME_GAP = 3000L;
    private static long prevMillisInFuture = 0L;

    public static void serverGreetMessageAndOthers(List<Message> messageList, MessagesListAdapter<Message> messageMessagesListAdapter, MessageInput messageInput) {
        for (Message msg : messageList) {
            long tempTime = prevMillisInFuture + TIME_GAP;
            prevMillisInFuture = tempTime;
            timerExecution = new TimerExecution(tempTime, 20L, msg, messageMessagesListAdapter, messageInput, "Typing disabled...");
            timerExecution.start();
        }
    }
}
