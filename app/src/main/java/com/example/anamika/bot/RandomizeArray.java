package com.example.anamika.bot;

import java.util.Map;
import java.util.Random;

/**
 * Created by tahmid on 01-Oct-17.
 */

public class RandomizeArray {

    private static final Random RANDOMIZER = new Random();
    private static int lengthOfParam;
    private static int randomPosition;

    public static void shuffle(String[] strArray) {
        lengthOfParam = strArray.length;
        for (int i = 0; i < lengthOfParam; i++) {
            randomPosition = RANDOMIZER.nextInt(lengthOfParam);
            String tempStr = strArray[i];
            strArray[i] = strArray[randomPosition];
            strArray[randomPosition] = tempStr;
        }
    }
}
