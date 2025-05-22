package com.untildawn.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Timer;

import java.util.HashMap;

public class SFXManager {

    private static final HashMap<String, Sound> sounds = new HashMap<>();
    private static final HashMap<String, Long> activeSoundIds = new HashMap<>();

    public static void load() {
        sounds.put("smg_reload", Gdx.audio.newSound(Gdx.files.internal("sounds/reload/smg_reload.wav")));
        sounds.put("shotgun_reload", Gdx.audio.newSound(Gdx.files.internal("sounds/reload/shotgun_reload.wav")));
        sounds.put("revolver_reload", Gdx.audio.newSound(Gdx.files.internal("sounds/reload/revolver_reload.wav")));
        sounds.put("ammo_collect", Gdx.audio.newSound(Gdx.files.internal("sounds/collect/ammo_collect.mp3")));
        sounds.put("heart_collect", Gdx.audio.newSound(Gdx.files.internal("sounds/collect/heart_collect.wav")));
        sounds.put("xp_collect", Gdx.audio.newSound(Gdx.files.internal("sounds/collect/xp_collect.wav")));
        sounds.put("shotgun_shot", Gdx.audio.newSound(Gdx.files.internal("sounds/shot/shotgun_shot.mp3")));
        sounds.put("smg_shot", Gdx.audio.newSound(Gdx.files.internal("sounds/shot/smg_shot.wav")));
        sounds.put("revolver_shot", Gdx.audio.newSound(Gdx.files.internal("sounds/shot/revolver_shot.mp3")));
        sounds.put("brain_death", Gdx.audio.newSound(Gdx.files.internal("sounds/monster_death/brain_death.wav")));
        sounds.put("eye_death", Gdx.audio.newSound(Gdx.files.internal("sounds/monster_death/eye_death.wav")));
        sounds.put("elder_death", Gdx.audio.newSound(Gdx.files.internal("sounds/monster_death/elder_death.wav")));
        sounds.put("boss_fight", Gdx.audio.newSound(Gdx.files.internal("sounds/events/boss_fight.wav")));
        sounds.put("tree_damage", Gdx.audio.newSound(Gdx.files.internal("sounds/damage/tree_damage.mp3")));
        sounds.put("monster_damage", Gdx.audio.newSound(Gdx.files.internal("sounds/damage/monster_damage.mp3")));
        sounds.put("projectile_damage", Gdx.audio.newSound(Gdx.files.internal("sounds/damage/projectile_damage.mp3")));
    }

    public static void play(String name) {
        Sound sound = sounds.get(name);
        if (sound != null) {
            long id = sound.play(1.0f);
            activeSoundIds.put(name, id);
        }
    }

    public static void play(String name, float volume, float pitch, float pan) {
        Sound sound = sounds.get(name);
        if (sound != null) {
            long id = sound.play(volume, pitch, pan);
            activeSoundIds.put(name, id);
        }
    }

    public static void fadeOutAndStop(final String name, final float duration) {
        final Sound sound = sounds.get(name);
        final Long id = activeSoundIds.get(name);
        if (sound == null || id == null) return;

        final int steps = 10;
        final float interval = duration / steps;

        for (int i = 0; i <= steps; i++) {
            final float volume = 1.0f - ((float) i / steps);
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    sound.setVolume(id, volume);
                    if (volume <= 0.01f) {
                        sound.stop(id);
                        activeSoundIds.remove(name);
                    }
                }
            }, i * interval);
        }
    }

    public static void dispose() {
        for (Sound sound : sounds.values()) {
            sound.dispose();
        }
        sounds.clear();
        activeSoundIds.clear();
    }
}
