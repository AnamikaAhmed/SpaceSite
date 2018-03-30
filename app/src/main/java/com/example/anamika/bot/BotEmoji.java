package com.example.anamika.bot;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tahmid on 09-Oct-17.
 */

public class BotEmoji {

    private static Map<String, String> emoji;

    public BotEmoji() {
        emoji = new HashMap<>();
        addEmoji();
    }

    private void addEmoji() {
        emoji.put("DIZZY_FACE", "\uD83D\uDE35");
        emoji.put("SMILING_FACE_WITH_HEART_EYES", "\uD83D\uDE0D");
        emoji.put("SKULL", "\uD83D\uDC80");
        emoji.put("SMILING_FACE", "☺️");
        emoji.put("SMILING_FACE_WITH_OPEN_MOUTH", "\uD83D\uDE03");
        emoji.put("FACE_WITH_COLD_SWEAT", "\uD83D\uDE13");
        emoji.put("THUMBS_UP", "\uD83D\uDC4D");
    }

    public String getEmoji(String emojiCode) {
        return emoji.get(emojiCode);
    }
}
