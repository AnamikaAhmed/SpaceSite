package com.example.anamika.model;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tahmid on 30-Sep-17.
 */

public class Question {

    private String body;
    private String[] options;
    private String correctAnswer;
    private String inputHint;
    private String editTextHint;
    private Map<String, String> optionAnswerMap;

    private static final String[] OPTIONS = {"A", "B", "C", "D", "E"};

    public Question(String body, String[] options) {
        this.body = body;
        this.options = options;
        this.optionAnswerMap = new HashMap<>();

        for (int i = 0; i < this.options.length; i++) {
            if (this.options[i].contains("Correct:")) {
                this.options[i] = this.options[i].replaceAll("Correct: ", "");
                setCorrectAnswer(this.options[i]);
                Log.d("Sohana -> Question.java", getBody() + "->" + getCorrectAnswer());
                break;
            }
        }

        int index = 0;
        if (this.options.length >= 4) {
            Log.d("Sohana -> Question.java", "=======================================");
            Log.d("Sohana -> Question.java", "Body: " + getBody());

            for (; index < this.options.length; index++) {
                optionAnswerMap.put(OPTIONS[index], this.options[index]);
                Log.d("Sohana -> Question.java", "OPTIONS: " + OPTIONS[index]);
                Log.d("Sohana -> Question.java", "Options: " + this.options[index]);
            }
        } else if (this.options.length == 2) {
            optionAnswerMap.put("A", "True");
            optionAnswerMap.put("B", "False");
        }
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getInputHint() {
        if (this.options.length == 4) {
            setInputHint("টাইপ কর:\n\n" +
                    "A - যদি তুমি মনে কর সঠিক উত্তর " + this.options[0] + "\n\n" +
                    "B - যদি তুমি মনে কর সঠিক উত্তর " + this.options[1] + "\n\n" +
                    "C - যদি তুমি মনে কর সঠিক উত্তর " + this.options[2] + "\n\n" +
                    "D - যদি তুমি মনে কর সঠিক উত্তর " + this.options[3] + "\n\n" +
                    "Skip - যদি তুমি পরবর্তী প্রশ্নে যেতে চাও\n\n" +
                    "Help - যদি তোমার কোন সাহায্য প্রয়োজন হয়\n");
        } else if (this.options.length == 5) {
            setInputHint("টাইপ কর:\n\n" +
                    "A - যদি তুমি মনে কর সঠিক উত্তর " + this.options[0] + "\n\n" +
                    "B - যদি তুমি মনে কর সঠিক উত্তর " + this.options[1] + "\n\n" +
                    "C - যদি তুমি মনে কর সঠিক উত্তর " + this.options[2] + "\n\n" +
                    "D - যদি তুমি মনে কর সঠিক উত্তর " + this.options[3] + "\n\n" +
                    "E - যদি তুমি মনে কর সঠিক উত্তর " + this.options[4] + "\n\n" +
                    "Skip - যদি তুমি পরবর্তী প্রশ্নে যেতে চাও\n\n" +
                    "Help - যদি তোমার কোন সাহায্য প্রয়োজন হয়\n");
        } else if (this.options.length == 2) {
            setInputHint("টাইপ কর:\n\n" +
                    "A - যদি তুমি মনে কর উপরের কথাটি সত্য\n\n" +
                    "B - যদি তুমি মনে কর উপরের কথাটি মিথ্যা\n\n" +
                    "Skip - যদি তুমি পরবর্তী প্রশ্নে যেতে চাও\n\n" +
                    "Help - যদি তোমার কোন সাহায্য প্রয়োজন হয়\n");
        } else {
            setInputHint("No Hint");
        }
        return inputHint;
    }

    public void setInputHint(String inputHint) {
        this.inputHint = inputHint;
    }

    public int totalOptions() {
        return this.options.length;
    }

    public Map<String, String> getOptionAnswerMap() {
        return optionAnswerMap;
    }

    public String getEditTextHint() {
        if (this.options.length == 4) {
            setEditTextHint("A/B/C/D/help");
        } else if (this.options.length == 5) {
            setEditTextHint("A/B/C/D/E/help");
        } else if (this.options.length == 2) {
            setEditTextHint("A/B/help");
        } else {
            setEditTextHint("No Hint");
        }
        return editTextHint;
    }

    public void setEditTextHint(String editTextHint) {
        this.editTextHint = editTextHint;
    }
}
