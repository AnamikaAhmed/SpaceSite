package com.example.anamika.spacex;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.TextView;

import com.stfalcon.chatkit.messages.MessageInput;
import com.stfalcon.chatkit.messages.MessagesList;
import com.stfalcon.chatkit.messages.MessagesListAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.example.anamika.bot.AddResponse;
import com.example.anamika.bot.BotEmoji;
import com.example.anamika.bot.GreetMessageResponse;
import com.example.anamika.bot.QuestionServer;
import com.example.anamika.model.Message;
import com.example.anamika.model.Question;
import com.example.anamika.model.User;

public class ThirdActivity extends AppCompatActivity {

    private static final String HELP_TEXT = "You can type:\n\n" +
            "A - If the correct answer is option A\n\n" +
            "B - If the correct answer is option B\n\n" +
            "C - If the correct answer is option C\n\n" +
            "D - If the correct answer is option D\n\n" +
            "Skip - If you want to skip this question\n\n" +
            "Help - Just when you need help\n";
    private static final String[] CORRECT_ANSWER_RESPONSES = {
            "সঠিক উত্তর \uD83D\uDE03",
            "\uD83D\uDE0D অসাধারণ। তোমার উত্তর একদম ঠিক",
            "দারুন। তুমি পেরেছ \uD83D\uDE35"
    };
    private static final String[] WRONG_ANSWER_RESPONSES = {
            "ভুল উত্তর \uD83D\uDC80",
            "দুঃখিত, তোমার উত্তরটি ভুল হয়েছে \uD83D\uDE13",
            "ভুল \uD83D\uDE13 \nপরেরগুলো আশা করি ঠিক হবে"
    };

    private static final long TIME_GAP = 5000L;
    private MessageInput messageInput;
    private MessagesList messagesList;
    private User me, bot;
    private MessagesListAdapter.HoldersConfig holdersConfig;
    private MessagesListAdapter<Message> messageMessagesListAdapter;
    private TextView coinTextView;

    private Question lastQuestionServed;
    private QuestionServer questionServer;
    private int totalCoinsEarned;
    private BotEmoji botEmoji;
    private Random random;

    private SweetAlertDialog sweetAlertDialog;

    private NotificationManager notificationManager;
    private NotificationCompat.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_activity);
        ButterKnife.bind(this);

        /* Object Initializing Start */
        messageInput = (MessageInput) findViewById(R.id.input);
        messageInput.getInputEditText().setEnabled(false);
        messageInput.getInputEditText().setHint("Typing disabled...");
        coinTextView = (TextView) findViewById(R.id.coin_collection);
        totalCoinsEarned = 0;
        botEmoji = new BotEmoji();
        random = new Random();

        messagesList = (MessagesList) findViewById(R.id.messagesList);

        me = new User("anamika", "আনামিকা আহমেদ", null);
        bot = new User("groot", "Groot", null);

        holdersConfig = new MessagesListAdapter.HoldersConfig();
        holdersConfig.setIncomingLayout(R.layout.custom_incoming_text_message);
        holdersConfig.setOutcomingLayout(R.layout.custom_outcoming_text_message);
        messageMessagesListAdapter = new MessagesListAdapter<>(me.getId(), holdersConfig, null);

        messagesList.setAdapter(messageMessagesListAdapter);


        // Notification
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.cross_32px);

        questionServer = QuestionServer.getInstance();
        /* Object Initializing End */

        /* Event Listener for MessageInput Start */
        messageInput.setInputListener(new MessageInput.InputListener() {
            @Override
            public boolean onSubmit(CharSequence input) {

                if (input.toString().equalsIgnoreCase("A") ||
                        input.toString().equalsIgnoreCase("B") ||
                        input.toString().equalsIgnoreCase("C") ||
                        input.toString().equalsIgnoreCase("D") ||
                        input.toString().equalsIgnoreCase("E")) {

                    if (lastQuestionServed != null) {

                        Log.d("Sohana -> MainAc.java", lastQuestionServed.getBody());
                        Log.d("Sohana -> MainAc.java", "CA: " + lastQuestionServed.getCorrectAnswer());
                        Log.d("Sohana -> MainAc.java", "MAP: " + lastQuestionServed.getOptionAnswerMap().toString());
                        Log.d("Sohana -> MainAc.java", "MAP: " + lastQuestionServed.getOptionAnswerMap().toString().toUpperCase());
                        Message msg = new Message(me.getId(), lastQuestionServed.getOptionAnswerMap().get(input.toString().toUpperCase()), me, currentTime());
                        messageMessagesListAdapter.addToStart(msg, true);

                        try {
                            if (lastQuestionServed.getOptionAnswerMap().get(input.toString().toUpperCase()).equals(lastQuestionServed.getCorrectAnswer())) {
                                addOneCoin();
                                AddResponse.addResponse(2000L, random_correct_answer_response(), messageMessagesListAdapter, messageInput, lastQuestionServed.getEditTextHint());
                                serveNewQuestion();
                            } else {
                                AddResponse.addResponse(2000L, random_wrong_answer_response(), messageMessagesListAdapter, messageInput, lastQuestionServed.getEditTextHint());
                                builder.setContentText("সঠিক উত্তরঃ " + lastQuestionServed.getCorrectAnswer());
                                builder.setContentTitle(lastQuestionServed.getBody());
                                notificationManager.notify(0, builder.build());
                                serveNewQuestion();
                            }
                        } catch (NullPointerException nex) {
                            if (lastQuestionServed == null) {
                                sweetAlertDialog = new SweetAlertDialog(ThirdActivity.this)
                                        .setTitleText("সাহায্য")
                                        .setContentText(HELP_TEXT);
                            } else {
                                sweetAlertDialog = new SweetAlertDialog(ThirdActivity.this)
                                        .setTitleText("সাহায্য")
                                        .setContentText(lastQuestionServed.getInputHint());
                            }
                            sweetAlertDialog.show();
                        }
                    } else {
                        messageMessagesListAdapter.addToStart(new Message(me.getId(), input.toString(), me, currentTime()), true);
                    }
                    return true;
                } else if (input.toString().equalsIgnoreCase("help")) {
                    if (lastQuestionServed == null) {
                        sweetAlertDialog = new SweetAlertDialog(ThirdActivity.this)
                                .setTitleText("সাহায্য")
                                .setContentText(HELP_TEXT);
                    } else {
                        sweetAlertDialog = new SweetAlertDialog(ThirdActivity.this)
                                .setTitleText("সাহায্য")
                                .setContentText(lastQuestionServed.getInputHint());
                    }
                    sweetAlertDialog.show();
                } else if (input.toString().equalsIgnoreCase("skip")) {
                    if (lastQuestionServed != null) {
                        sweetAlertDialog = new SweetAlertDialog(ThirdActivity.this, SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("পরবর্তী প্রশ্ন!")
                                .setContentText("তুমি নিশ্চিত আপনি পরবর্তী প্রশ্নে যেতে চাও?")
                                .setConfirmText("হ্যাঁ")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        Message msg = new Message(bot.getId(), "পরবর্তী প্রশ্ন...", bot, currentTime());
                                        messageMessagesListAdapter.addToStart(msg, true);
                                        sweetAlertDialog.cancel();
                                        serveNewQuestion(1500L);
                                    }
                                });
                    } else {
                        sweetAlertDialog = new SweetAlertDialog(ThirdActivity.this)
                                .setTitleText("তথ্য")
                                .setContentText("আর কোন প্রশ্ন নেই");
                    }
                    sweetAlertDialog.show();
                } else {
                    if (lastQuestionServed == null) {
                        sweetAlertDialog = new SweetAlertDialog(ThirdActivity.this)
                                .setTitleText("সাহায্য")
                                .setContentText(HELP_TEXT);
                    } else {
                        sweetAlertDialog = new SweetAlertDialog(ThirdActivity.this)
                                .setTitleText("সাহায্য")
                                .setContentText(lastQuestionServed.getInputHint());
                    }
                    sweetAlertDialog.show();
                }
                return true;
            }
        });
        /* Event Listener for MessageInput End */

        /* START: Hard coding for serving questions */
        // Serve Greet Message
        List<Message> messageList = new ArrayList<>();
        messageList.add(new Message(bot.getId(), "হ্যালো ".concat(me.getName().split(" ")[0]).concat(" ☺️"), bot, currentTime()));
        messageList.add(new Message(bot.getId(), "মঙ্গল গ্রহে তোমাকে স্বাগতম। আমার নাম ".concat(bot.getName()).concat(", মঙ্গল গ্রহের প্রহরী").concat(" \uD83D\uDC7D"), bot, currentTime()));
        messageList.add(new Message(bot.getId(), "মঙ্গল গ্রহে প্রবেশ করতে হলে তোমাকে কিছু প্রশ্নের উত্তর দিতে হবে", bot, currentTime()));
        messageList.add(new Message(bot.getId(), "আশা করি তুমি মঙ্গল গ্রহের ব্যাপারে প্রামাণ্যচিত্র থেকে অনেক কিছু জেনেছ। এইবার তাহলে শুরু করা যাক".concat(" \uD83D\uDE03"), bot, currentTime()));
        messageList.add(new Message(bot.getId(), "প্রথম প্রশ্ন...", bot, currentTime()));
        GreetMessageResponse.serverGreetMessageAndOthers(messageList, messageMessagesListAdapter, messageInput);
        // Served
        serveNewQuestion(15000L + 3000L);
        /* END: Hard coding for serving questions */
    }

    private void addOneCoin() {
        totalCoinsEarned += 1;
        coinTextView.setText(String.valueOf(totalCoinsEarned).length() == 1 ? "0".concat(String.valueOf(totalCoinsEarned)) : String.valueOf(totalCoinsEarned));
    }

    private void serveNewQuestion() {
        if (questionServer.canServerServeNewQuestion()) {
            lastQuestionServed = questionServer.serve(TIME_GAP, messageMessagesListAdapter, messageInput);
            Log.d("Sohana -> MainAc.java", "serveNewQuestion()");
        } else {
            lastQuestionServed = null;
            AddResponse.addResponse(3000L, "প্রশ্নোত্তর পর্ব এইখানেই শেষ, আর কোন প্রশ্ন নেই", messageMessagesListAdapter, messageInput, "Type here...");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(ThirdActivity.this, track2.class);
                    startActivity(intent);
                }
            }, 5000L);
        }
    }

    /**
     * Serves a new question after delaying milliseconds passed in parameter
     *
     * @param millisInFuture Waits this much milliseconds before serving new question
     */
    private void serveNewQuestion(long millisInFuture) {
        if (questionServer.canServerServeNewQuestion()) {
            lastQuestionServed = questionServer.serve(millisInFuture, messageMessagesListAdapter, messageInput);
            Log.d("Sohana -> MainAc.java", "serveNewQuestion(millis)");
        } else {
            lastQuestionServed = null;
            AddResponse.addResponse(3000L, "প্রশ্নোত্তর পর্ব এইখানেই শেষ, আর কোন প্রশ্ন নেই", messageMessagesListAdapter, messageInput, "Type here...");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(getApplicationContext(), track2.class);
                    startActivity(intent);
                }
            }, 5000L);
        }
    }

    private Date currentTime() {
        return new Date();
    }

    private String random_correct_answer_response() {
        return CORRECT_ANSWER_RESPONSES[random.nextInt(CORRECT_ANSWER_RESPONSES.length)];
    }

    private String random_wrong_answer_response() {
        return WRONG_ANSWER_RESPONSES[random.nextInt(WRONG_ANSWER_RESPONSES.length)];
    }
}
