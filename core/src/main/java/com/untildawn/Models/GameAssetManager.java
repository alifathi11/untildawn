package com.untildawn.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class GameAssetManager {

    private static GameAssetManager gameAssetManager;
    private Skin skin = new Skin(Gdx.files.internal("skin/tracer-ui.json"));

    private final String character1_idle0 = "1/idle/idle_0.png";
    private final String character1_idle1 = "1/idle/idle_1.png";
    private final String character1_idle2 = "1/idle/idle_2.png";
    private final String character1_idle3 = "1/idle/idle_3.png";
    private final String character1_idle4 = "1/idle/idle_4.png";
    private final String character1_idle5 = "1/idle/idle_5.png";
    private final Texture character1_idle0_tex = new Texture(character1_idle0);
    private final Texture character1_idle1_tex = new Texture(character1_idle1);
    private final Texture character1_idle2_tex = new Texture(character1_idle2);
    private final Texture character1_idle3_tex = new Texture(character1_idle3);
    private final Texture character1_idle4_tex = new Texture(character1_idle4);
    private final Texture character1_idle5_tex = new Texture(character1_idle5);
    private final Animation<Texture> character1_idle_animation = new Animation<>(
        0.1f,
        character1_idle0_tex,
        character1_idle1_tex,
        character1_idle2_tex,
        character1_idle3_tex,
        character1_idle4_tex,
        character1_idle5_tex
    );

    private final String smg = "smg/SMGStill.png";
    private final Texture smgTexture = new Texture(smg);

    private final String bullet = "bullet/bullet.png";

    public static GameAssetManager getGameAssetManager() {
        if (gameAssetManager == null)
            gameAssetManager = new GameAssetManager();
        return gameAssetManager;
    }

    public Skin getSkin() {
        return skin;
    }

    public Animation<Texture> getCharacter1_idle_animation() {
        return character1_idle_animation;
    }

    public String getCharacter1_idle0() {
        return character1_idle0;
    }

    public String getSmg() {
        return smg;
    }

    public String getBullet() {
        return bullet;
    }
}
