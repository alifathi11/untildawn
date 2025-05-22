package com.untildawn.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import java.util.ArrayList;

public class MusicManager {
    private static final ArrayList<String> musicPaths;
    private static Music currentMusic;
    private static int currentTrackIndex = 0;
    private static float volume = 1.0f;
    private static boolean isMusicOn = true;

    static {
        musicPaths = new ArrayList<>();

        musicPaths.add("music/compromise.mp3");
        musicPaths.add("music/baller.mp3");
        musicPaths.add("music/complex.mp3");
        musicPaths.add("music/government.mp3");
        musicPaths.add("music/score.mp3");
        musicPaths.add("music/wrath.mp3");


        loadMusic(currentTrackIndex);
    }

    private static void loadMusic(int index) {
        if (currentMusic != null) {
            currentMusic.stop();
            currentMusic.dispose();
        }

        currentMusic = Gdx.audio.newMusic(Gdx.files.internal(musicPaths.get(index)));
        currentMusic.setLooping(true);
        currentMusic.setVolume(volume);

        if (isMusicOn) {
            currentMusic.play();
        }
    }

    public static void play() {
        if (isMusicOn && currentMusic != null && !currentMusic.isPlaying()) {
            currentMusic.play();
        }
    }

    public static void pause() {
        if (currentMusic != null && currentMusic.isPlaying()) {
            currentMusic.pause();
        }
    }

    public static void toggleMusic() {
        isMusicOn = !isMusicOn;
        if (isMusicOn) {
            currentMusic.play();
        } else {
            currentMusic.pause();
        }
    }

    public static void changeTrack(int index) {
        if (index >= 0 && index < musicPaths.size()) {
            currentTrackIndex = index;
            loadMusic(index);
        }
    }

    public static void nextTrack() {
        currentTrackIndex = (currentTrackIndex + 1) % musicPaths.size();
        loadMusic(currentTrackIndex);
    }

    public static void setVolume(float newVolume) {
        volume = Math.max(0f, Math.min(1f, newVolume));
        if (currentMusic != null) {
            currentMusic.setVolume(volume);
        }
    }

    public static float getVolume() {
        return volume;
    }

    public static boolean isMusicPlaying() {
        return isMusicOn && currentMusic.isPlaying();
    }

    public static int getCurrentTrackIndex() {
        return currentTrackIndex + 1;
    }

    public static void dispose() {
        if (currentMusic != null) {
            currentMusic.stop();
            currentMusic.dispose();
        }
    }
}
