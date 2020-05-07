package com.ostrov.mediaplayerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

public class MediaPlayer2Activity extends AppCompatActivity {
    TextView artist;
    TextView title;
    private MediaPlayer player;
    private ArrayList<Song> songs;
    private int number = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player2);

        setData();
        artist = findViewById(R.id.text_artist2);
        artist.setText(songs.get(number).getArtist());
        title = findViewById(R.id.text_song2);
        title.setText(songs.get(number).getTitle());

        player = new MediaPlayer();
        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
            }
        });
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.reset();
                onClickNext(null);
            }
        });
    }

    private void setData() {
        songs = new ArrayList<>();
        songs.add(new Song(R.raw.hill_of_sorrow, "Hiroyuki Sawano", "Hill Of Sorrow"));
        songs.add(new Song(R.raw.hitomi_no_kotae, "Shiraishi Noria", "Hitomi no Kotae"));
        songs.add(new Song(R.raw.black_diamond, "Mizuki Nana", "Black diamond"));
        songs.add(new Song(R.raw.exorcist_concerto_first_movement_me_and_creed, "Mika Kobayashi", "Exorcist Concerto First Movement Me & Creed"));
        songs.add(new Song(R.raw.inishie, "Rayflower", "Inishie"));
        songs.add(new Song(R.raw.alumina, "Nightmare", "Alumina"));
    }

    private void play(){
        Song song = songs.get(number);
        artist.setText(song.getArtist());
        title.setText(song.getTitle());

        int rawResId = song.getSongResourceId();
        AssetFileDescriptor afd = getResources().openRawResourceFd(rawResId);
        if (afd != null) {
            player.reset();
            try {
                player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getDeclaredLength());
            } catch (IOException e) {
                e.printStackTrace();
            }
            player.prepareAsync();
        }
    }

    public void onClickPrev(View view) {
        if (number > 0) {
            number--;
            play();
        }
    }

    public void onClickPlay(View view) {
        play();
    }

    public void onClickStop(View view) {
        player.stop();
    }

    public void onClickNext(View view) {
        if (number < songs.size() - 1) {
            number++;
            play();
        }
    }

    @Override
    protected void onDestroy() {
        if (player != null) {
            player.release();
            player = null;
        }
        super.onDestroy();
    }
}
