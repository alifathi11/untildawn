package com.untildawn.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import java.util.HashMap;

public class SFXManager {

    private static final HashMap<String, Sound> sounds = new HashMap<>();

    public static void load() {
        sounds.put("smg_reload", Gdx.audio.newSound(Gdx.files.internal("sounds/reload/smg_reload.wav")));
        sounds.put("shotgun_reload", Gdx.audio.newSound(Gdx.files.internal("sounds/reload/shotgun_reload.wav")));
        sounds.put("revolver_reload", Gdx.audio.newSound(Gdx.files.internal("sounds/reload/revolver_reload.wav")));
        sounds.put("ammo_collect", Gdx.audio.newSound(Gdx.files.internal("sounds/collect/ammo_collect.mp3"))); // TODO: wav
        sounds.put("heart_collect", Gdx.audio.newSound(Gdx.files.internal("sounds/collect/heart_collect.wav")));
        sounds.put("shotgun_shot", Gdx.audio.newSound(Gdx.files.internal("sounds/shot/shotgun_shot.mp3")));
        sounds.put("smg_shot", Gdx.audio.newSound(Gdx.files.internal("sounds/shot/smg_shot.wav")));
        sounds.put("revolver_shot", Gdx.audio.newSound(Gdx.files.internal("sounds/shot/revolver_shot.mp3")));
    }

    public static void play(String name) {
        Sound sound = sounds.get(name);
        if (sound != null) {
            sound.play(1.0f);
        }
    }

    public static void play(String name, float volume, float pitch, float pan) {
        Sound sound = sounds.get(name);
        if (sound != null) {
            sound.play(volume, pitch, pan);
        }
    }

    public static void dispose() {
        for (Sound sound : sounds.values()) {
            sound.dispose();
        }
        sounds.clear();
    }
}
