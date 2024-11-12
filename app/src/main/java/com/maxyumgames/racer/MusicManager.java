package com.maxyumgames.racer;

import android.content.Context;
import android.media.MediaPlayer;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MusicManager {

    private List<String> audio_names = new ArrayList<String>();
    private List<Integer> audios = new ArrayList<Integer>();

    private MediaPlayer mediaPlayer;
    private Random rand = new Random();
    int chance = 0;
    private Context context;
    public MusicManager(Context context) {

        audio_names.add("OMGF - Hello");
        audio_names.add("Ching Cheng Hanji");
        audio_names.add("M|O|O|M - Crystals");

        audios.add(R.raw.bg_music);
        audios.add(R.raw.bg_music2);
        audios.add(R.raw.bg_music3);

        chance = rand.nextInt(3);

        mediaPlayer = MediaPlayer.create(context, audios.get(chance)); // Замените на имя вашего файла
        mediaPlayer.setLooping(true); // Зацикливаем музыку

        this.context = context;
    }

    public void play() {
        if (!mediaPlayer.isPlaying()) {
            Toast.makeText(context, "Сейчас играет: " + audio_names.get(chance), Toast.LENGTH_SHORT).show();
            mediaPlayer.start();
        }
    }

    public void pause() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    public void stop() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.prepareAsync(); // Подготовка для повторного использования
        }
    }

    public void release() {
        mediaPlayer.release();
    }
}