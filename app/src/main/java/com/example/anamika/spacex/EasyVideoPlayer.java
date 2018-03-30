package com.example.anamika.spacex;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.afollestad.easyvideoplayer.EasyVideoCallback;

public class EasyVideoPlayer extends AppCompatActivity implements EasyVideoCallback {

    private static final String TEST_URL = "https://youtu.be/Di8nIR44Gfg";

    private com.afollestad.easyvideoplayer.EasyVideoPlayer easyVideoPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_video_player);

        easyVideoPlayer = (com.afollestad.easyvideoplayer.EasyVideoPlayer) findViewById(R.id.easyvideoplayer);
        // Sets the callback to this Activity, since it inherits EasyVideoCallback
        easyVideoPlayer.setCallback(this);

        // Sets the source to the HTTP URL held in the TEST_URL variable.
        // To play files, you can use Uri.fromFile(new File("..."))
        easyVideoPlayer.setSource(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.marcdoc));

        // From here, the player view will show a progress indicator until the player is prepared.
        // Once it's prepared, the progress indicator goes away and the controls become enabled for the user to begin playback.
    }

    @Override
    public void onStarted(com.afollestad.easyvideoplayer.EasyVideoPlayer player) {

    }

    @Override
    public void onPaused(com.afollestad.easyvideoplayer.EasyVideoPlayer player) {
        player.pause();
    }

    @Override
    public void onPreparing(com.afollestad.easyvideoplayer.EasyVideoPlayer player) {

    }

    @Override
    public void onPrepared(com.afollestad.easyvideoplayer.EasyVideoPlayer player) {

    }

    @Override
    public void onBuffering(int percent) {

    }

    @Override
    public void onError(com.afollestad.easyvideoplayer.EasyVideoPlayer player, Exception e) {

    }

    @Override
    public void onCompletion(com.afollestad.easyvideoplayer.EasyVideoPlayer player) {

    }

    @Override
    public void onRetry(com.afollestad.easyvideoplayer.EasyVideoPlayer player, Uri source) {

    }

    @Override
    public void onSubmit(com.afollestad.easyvideoplayer.EasyVideoPlayer player, Uri source) {

    }
}
