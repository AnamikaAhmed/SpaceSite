package com.example.anamika.bot;

import android.os.CountDownTimer;
import android.widget.ProgressBar;

import com.stfalcon.chatkit.messages.MessageInput;
import com.stfalcon.chatkit.messages.MessagesListAdapter;
import com.wang.avi.AVLoadingIndicatorView;

import com.example.anamika.model.Message;


public class TimerExecution extends CountDownTimer {

    private Message message;
    private MessagesListAdapter<Message> messageMessagesListAdapter;
    private MessageInput messageInput;
    private String editTextHint;

    public TimerExecution(long millisInFuture, long countDownInterval, Message message, MessagesListAdapter<Message> messageMessagesListAdapter, MessageInput messageInput, String editTextHint) {
        super(millisInFuture, countDownInterval);
        this.message = message;
        this.messageMessagesListAdapter = messageMessagesListAdapter;
        this.messageInput = messageInput;
        this.editTextHint = editTextHint;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        messageInput.getInputEditText().setEnabled(false);
    }

    @Override
    public void onFinish() {
        messageInput.getInputEditText().setEnabled(true);
        messageMessagesListAdapter.addToStart(message, true);
        messageInput.getInputEditText().setHint(this.editTextHint);
    }
}
