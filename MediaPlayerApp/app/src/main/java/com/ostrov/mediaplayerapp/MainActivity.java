package com.ostrov.mediaplayerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openMediaPlayer(View view) {
        Intent intent = new Intent(this, MediaPlayerActivity.class);
        startActivity(intent);
    }

    public void openSoundPool(View view) {
        Intent intent = new Intent(this, SoundPoolActivity.class);
        startActivity(intent);
    }

    public void openMediaPlayer2(View view) {
        Intent intent = new Intent(this, MediaPlayer2Activity.class);
        startActivity(intent);
    }
}
