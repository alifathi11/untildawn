package com.untildawn.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import java.io.File;


public class GameAssetManager {

    private static GameAssetManager gameAssetManager;

    // skin
    private Skin skin = new Skin(Gdx.files.internal("skin/tracer-ui.json"));

    // first  frames of animations

    // make animations
    Animation<Texture> character1_idle_animation = buildAnimation(1, "idle");
    Animation<Texture> character1_walk_animation = buildAnimation(1, "walk");


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

    public Animation<Texture> getCharacter1_walk_animation() {
        return character1_walk_animation;
    }

    public String getCharacter1_idle0() {
        return "1/idle/idle_0.png";
    }

    public String getCharacter1_walking0() {
        return "1/walking/walking_0.png";
    }

    public String getSmg() {
        return smg;
    }

    public String getBullet() {
        return bullet;
    }



    private Animation<Texture> buildAnimation(int characterNumber, String type) {

        String pathToFolder = String.format("assets/%d/%s", characterNumber, type);
        int numberOfFrames = countFiles(pathToFolder);

        Texture[] textureArray = new Texture[numberOfFrames];

        for (int i = 0; i < numberOfFrames; i++) {
            String rawPath = String.format("%d/%s/%s_%d.png", characterNumber, type, type, i);
            Texture texture = new Texture(rawPath);
            textureArray[i] = texture;
        }

        Animation<Texture> animation = new Animation<>(0.1f, textureArray);

        return animation;
    }


    private int countFiles(String path) {

        File folder = new File(path);


        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            int fileCount = 0;
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    fileCount++;
                }
            }
            return fileCount;
        } else {
            return 0;
        }
    }
}
