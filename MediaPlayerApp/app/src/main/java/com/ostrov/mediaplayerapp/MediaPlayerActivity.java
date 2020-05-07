package com.ostrov.mediaplayerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.ostrov.mediaplayerapp.databinding.ActivityMediaPlayerBinding;

import java.util.HashMap;

public class MediaPlayerActivity extends AppCompatActivity {
    private Handler handler = new Handler();
    private static final int DELAY = 100;
    private double currTime = 0;
    private ActivityMediaPlayerBinding binding;
    private MediaPlayer mediaPlayer;
    private HashMap<Integer, Song> soundsMap;
    private int number = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMediaPlayerBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.seekBar.setClickable(false);
        setData();
        setSong();
    }

    private void setData() {
        soundsMap = new HashMap<>();
        soundsMap.put(0, new Song(R.raw.alumina, "Nightmare", "Alumina", ""));
        soundsMap.put(1, new Song(R.raw.black_diamond, "Mizuki Nana", "Black diamond", "OST Shugo Chara"));
        soundsMap.put(2, new Song(R.raw.exorcist_concerto_first_movement_me_and_creed, "Mika Kobayashi", "Exorcist Concerto First Movement Me & Creed", "OST Ao no Exorcist"));
        soundsMap.put(3, new Song(R.raw.hill_of_sorrow, "Hiroyuki Sawano", "Hill Of Sorrow", "OST Guilty Crown"));
        soundsMap.put(4, new Song(R.raw.hitomi_no_kotae, "Shiraishi Noria", "Hitomi no Kotae", "OST 07-Ghost"));
        soundsMap.put(5, new Song(R.raw.inishie, "Rayflower", "Inishie", "OST Uragiri wa Boku no Namae o Shitteiru"));
    }

    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            currTime = mediaPlayer.getCurrentPosition();
            binding.seekBar.setProgress((int)currTime);
            handler.postDelayed(this, DELAY);
        }
    };

    private void setSong() {
        releaseMediaPlayer();
        mediaPlayer = MediaPlayer.create(this, soundsMap.get(number).getSongResourceId());
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                playNextSong(null);
            }
        });

        currTime = 0;
        binding.seekBar.setMax(mediaPlayer.getDuration());
        binding.seekBar.setProgress((int) currTime);
        handler.postDelayed(UpdateSongTime, DELAY);

        binding.textArtist.setText(soundsMap.get(number).getArtist());
        binding.textSong.setText(soundsMap.get(number).getTitle());
        binding.textDescription.setText(soundsMap.get(number).getDescription());

        if (soundsMap.get(number).getDescription().isEmpty())
            binding.textDescription.setVisibility(View.INVISIBLE);
        else
            binding.textDescription.setVisibility(View.VISIBLE);
    }

    public void playFistSong(View view) {
        number = 0;
        setSong();
        playCurrentSong(null);
    }

    public void playPreviousSong(View view) {
        if (number > 0)
            number--;
        setSong();
        playCurrentSong(null);
    }

    public void playCurrentSong(View view) {
        mediaPlayer.start();
    }

    public void pauseCurrentSong(View view) {
        mediaPlayer.pause();
    }

    public void playNextSong(View view) {
        if (number < soundsMap.size() - 1)
            number++;
        setSong();
        playCurrentSong(null);
    }

    public void playLastSong(View view) {
        number = soundsMap.size() - 1;
        setSong();
        playCurrentSong(view);
    }

    private void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    protected void onDestroy() {
        releaseMediaPlayer();
        super.onDestroy();
    }
}
