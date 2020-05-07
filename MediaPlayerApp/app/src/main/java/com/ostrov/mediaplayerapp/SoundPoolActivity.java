package com.ostrov.mediaplayerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;

import java.util.HashMap;

public class SoundPoolActivity extends AppCompatActivity {
    private static final int OCEAN = 0, LAVA = 1, RAIN = 2;
    private HashMap<Integer, Integer> soundsMap;
    private SoundPool sounds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_pool);

        sounds = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);

        soundsMap = new HashMap<>();
        soundsMap.put(OCEAN, sounds.load(this, R.raw.effect_ocean_edge, 1));
        soundsMap.put(LAVA, sounds.load(this, R.raw.effect_lava, 1));
        soundsMap.put(RAIN, sounds.load(this, R.raw.effect_rain_thunder, 1));
    }

    //play the sound depends on the input request
    public void play(int sound) {
        AudioManager mgr = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
        float streamVolumeCurrent = mgr.getStreamVolume(AudioManager.STREAM_RING);
        float streamVolumeMax = mgr.getStreamMaxVolume(AudioManager.STREAM_RING);
        float volume = streamVolumeCurrent / streamVolumeMax;

        sounds.play(soundsMap.get(sound), volume, volume, 1, 0, 1.f);
    }

    public void playOcean(View v) {
        play(OCEAN);
    }

    public void playLava(View v) {
        play(LAVA);
    }

    public void playRain(View v) {
        play(RAIN);
    }

    @Override
    protected void onDestroy() {
        sounds.release();
        super.onDestroy();
    }
}
