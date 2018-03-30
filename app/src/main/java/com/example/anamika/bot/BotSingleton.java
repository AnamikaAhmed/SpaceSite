package com.example.anamika.bot;


import com.example.anamika.model.User;

/**
 * Created by tahmid on 30-Sep-17.
 */

public class BotSingleton {

    private static User bot;

    private BotSingleton() {
    }

    public static User createBot() {
        if (bot == null) {
            bot = new User("sohana", "Sohana", null);
        }
        return bot;
    }
}
