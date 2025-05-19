package com.untildawn.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.untildawn.Enums.Heros;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


public class GameAssetManager {

    private static GameAssetManager gameAssetManager;

    // skin
    private Skin skin = new Skin(Gdx.files.internal("skin/tracer-ui.json"));

    private Map<String, Animation<Texture>> animations = new HashMap<>();

    // make animations
    private Animation<Texture> diamond_idle_animation = buildAnimation("diamond", "idle");
    private Animation<Texture> diamond_walk_animation = buildAnimation("diamond", "walk");
//    private Animation<Texture> shana_idle_animation = buildAnimation("shana", "idle");
//    private Animation<Texture> shana_walk_animation = buildAnimation("shana", "walk");
    private Animation<Texture> scarlet_idle_animation = buildAnimation("scarlet", "idle");
    private Animation<Texture> scarlet_walk_animation = buildAnimation("scarlet", "walk");
//    private Animation<Texture> lilith_idle_animation = buildAnimation("lilith", "idle");
//    private Animation<Texture> lilith_walk_animation = buildAnimation("lilith", "walk");
//    private Animation<Texture> dasher_idle_animation = buildAnimation("dasher", "walk");
//    private Animation<Texture> dasher_walk_animation = buildAnimation("dasher", "walk");

    public GameAssetManager() {
        animations.put("diamond_walk_animation", diamond_walk_animation);
        animations.put("diamond_idle_animation", diamond_idle_animation);
//        animations.put("shana_idle_animation", shana_idle_animation);
//        animations.put("shana_walk_animation", shana_walk_animation);
        animations.put("scarlet_idle_animation", scarlet_idle_animation);
        animations.put("scarlet_walk_animation", scarlet_walk_animation);
//        animations.put("lilith_idle_animation", shana_idle_animation);
//        animations.put("lilith_walk_animation", shana_walk_animation);
//        animations.put("dasher_idle_animation", shana_idle_animation);
//        animations.put("dasher_walk_animation", shana_walk_animation);
    }

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

    public Animation<Texture> getCharacterIdleAnimation(Heros hero) {
        String heroName = hero.name().toLowerCase();
        String animationName = String.format("%s_idle_animation", heroName);
        return animations.get(animationName);
    }

    public Animation<Texture> getCharacterWalkAnimation(Heros hero) {
        String heroName = hero.name().toLowerCase();
        String animationName = String.format("%s_walk_animation", heroName);
        return animations.get(animationName);
    }

    public Texture getCharacter_idle0(Heros hero) {
        String heroName = hero.name().toLowerCase();
        String animationName = String.format("%s_idle_animation", heroName);
        return animations.get(animationName).getKeyFrames()[0];
    }

    public Texture getCharacter_walk0(Heros hero) {
        String heroName = hero.name().toLowerCase();
        String animationName = String.format("%s_walk_animation", heroName);
        return animations.get(animationName).getKeyFrames()[0];
    }

    public String getSmg() {
        return smg;
    }

    public String getBullet() {
        return bullet;
    }



    private Animation<Texture> buildAnimation(String characterName, String type) {

        String pathToFolder = String.format("assets/%s/%s", characterName, type);
        int numberOfFrames = countFiles(pathToFolder);

        Texture[] textureArray = new Texture[numberOfFrames];

        for (int i = 0; i < numberOfFrames; i++) {
            String rawPath = String.format("%s/%s/%s_%d.png", characterName, type, type, i);
            Texture texture = new Texture(rawPath);
            textureArray[i] = texture;
        }

        return new Animation<>(0.1f, textureArray);
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
