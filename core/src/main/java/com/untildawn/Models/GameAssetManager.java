package com.untildawn.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.untildawn.Enums.Projectiles;
import com.untildawn.Enums.Heros;
import com.untildawn.Enums.Monsters;
import com.untildawn.Enums.Weapons;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


public class GameAssetManager {

    private static GameAssetManager gameAssetManager;

    // skin
    private Skin skin = new Skin(Gdx.files.internal("skin/tracer-ui.json"));

    private Map<String, Animation<Texture>> characterAnimations = new HashMap<>();
    private Map<String, Animation<Texture>> weaponAnimations = new HashMap<>();
    private Map<String, Texture> bulletTextures = new HashMap<>();

    // character
    private Animation<Texture> diamond_idle_animation = buildCharacterAnimation("diamond", "idle");
    private Animation<Texture> diamond_walk_animation = buildCharacterAnimation("diamond", "walk");
    private Animation<Texture> diamond_run_animation = buildCharacterAnimation("diamond", "run");

    private Animation<Texture> shana_idle_animation = buildCharacterAnimation("shana", "idle");
    private Animation<Texture> shana_walk_animation = buildCharacterAnimation("shana", "walk");
    private Animation<Texture> shana_run_animation = buildCharacterAnimation("shana", "run");

    private Animation<Texture> scarlet_idle_animation = buildCharacterAnimation("scarlet", "idle");
    private Animation<Texture> scarlet_walk_animation = buildCharacterAnimation("scarlet", "walk");
    private Animation<Texture> scarlet_run_animation = buildCharacterAnimation("scarlet", "run");

    private Animation<Texture> lilith_idle_animation = buildCharacterAnimation("lilith", "idle");
    private Animation<Texture> lilith_walk_animation = buildCharacterAnimation("lilith", "walk");
    private Animation<Texture> lilith_run_animation = buildCharacterAnimation("lilith", "run");

//    private Animation<Texture> dasher_idle_animation = buildAnimation("dasher", "walk");
//    private Animation<Texture> dasher_walk_animation = buildAnimation("dasher", "walk");
//    private Animation<Texture> dasher_walk_animation = buildAnimation("dasher", "walk");


    // weapon
    private Animation<Texture> smg_reload_animation = buildReloadAnimation("smg");
    private Animation<Texture> shotgun_reload_animation = buildReloadAnimation("shotgun");
    private Animation<Texture> revolver_reload_animation = buildReloadAnimation("revolver");


    // projectile
    private final Texture shotgun_bullet = buildBulletTexture("shotgun");
    private final Texture smg_bullet = buildBulletTexture("smg");
    private final Texture revolver_bullet = buildBulletTexture("revolver");

    // tree
    private final Animation<Texture> tree_animation = buildTreeAnimation();
    private final Texture eyeMonster_projectile = new Texture(Gdx.files.internal("eyeMonsterProjectile.png"));

    // monster
    private final Animation<Texture> brainMonster_animation = buildBrainMonsterAnimation();
    private final Animation<Texture> eyeMonster_animation = buildEyeMonsterAnimation();
    private final Texture elderMonster_texture  = new Texture(Gdx.files.internal("elderMonster/ElderBrain.png"));

    // XP
    private final Texture xpTexture = new Texture(Gdx.files.internal("xp.png"));

    // ammo
    private final Texture ammoTexture = new Texture(Gdx.files.internal("ammo.png"));


    public GameAssetManager() {
        // character
        characterAnimations.put("diamond_walk_animation", diamond_walk_animation);
        characterAnimations.put("diamond_idle_animation", diamond_idle_animation);
        characterAnimations.put("diamond_run_animation", diamond_run_animation);

        characterAnimations.put("shana_idle_animation", shana_idle_animation);
        characterAnimations.put("shana_walk_animation", shana_walk_animation);
        characterAnimations.put("shana_run_animation", shana_run_animation);

        characterAnimations.put("scarlet_idle_animation", scarlet_idle_animation);
        characterAnimations.put("scarlet_walk_animation", scarlet_walk_animation);
        characterAnimations.put("scarlet_run_animation", scarlet_run_animation);

        characterAnimations.put("lilith_idle_animation", lilith_idle_animation);
        characterAnimations.put("lilith_walk_animation", lilith_walk_animation);
        characterAnimations.put("lilith_run_animation", lilith_run_animation);

//        animations.put("dasher_idle_animation", dasher_idle_animation);
//        animations.put("dasher_walk_animation", dasher_walk_animation);
//        animations.put("dasher_walk_animation", dasher_run_animation);

        // weapon
        weaponAnimations.put("smg_reload_animation", smg_reload_animation);
        weaponAnimations.put("shotgun_reload_animation", shotgun_reload_animation);
        weaponAnimations.put("revolver_reload_animation", revolver_reload_animation);

        // bullet
        bulletTextures.put("smg_bullet", smg_bullet);
        bulletTextures.put("shotgun_bullet", shotgun_bullet);
        bulletTextures.put("revolver_bullet", revolver_bullet);

    }
    public static GameAssetManager getGameAssetManager() {
        if (gameAssetManager == null)
            gameAssetManager = new GameAssetManager();
        return gameAssetManager;
    }

    public Skin getSkin() {
        return skin;
    }

    // character
    public Animation<Texture> getCharacterIdleAnimation(Heros hero) {
        String heroName = hero.name().toLowerCase();
        String animationName = String.format("%s_idle_animation", heroName);
        return characterAnimations.get(animationName);
    }

    public Animation<Texture> getCharacterWalkAnimation(Heros hero) {
        String heroName = hero.name().toLowerCase();
        String animationName = String.format("%s_walk_animation", heroName);
        return characterAnimations.get(animationName);
    }

    public Animation<Texture> getCharacterRunAnimation(Heros hero) {
        String heroName = hero.name().toLowerCase();
        String animationName = String.format("%s_run_animation", heroName);
        return characterAnimations.get(animationName);
    }

    public Texture getCharacter_idle0(Heros hero) {
        String heroName = hero.name().toLowerCase();
        String animationName = String.format("%s_idle_animation", heroName);
        return characterAnimations.get(animationName).getKeyFrames()[0];
    }

    public Texture getCharacter_walk0(Heros hero) {
        String heroName = hero.name().toLowerCase();
        String animationName = String.format("%s_walk_animation", heroName);
        return characterAnimations.get(animationName).getKeyFrames()[0];
    }

    public Texture getCharacter_run0(Heros hero) {
        String heroName = hero.name().toLowerCase();
        String animationName = String.format("%s_run_animation", heroName);
        return characterAnimations.get(animationName).getKeyFrames()[0];
    }


    // weapon
    public Animation<Texture> getReloadAnimation(Weapons weapon) {
        String weaponName = weapon.name().toLowerCase();
        String animationName = String.format("%s_reload_animation", weaponName);
        return weaponAnimations.get(animationName);
    }

    public Texture getWeaponTexture(Weapons weapon) {
        String weaponName = weapon.name().toLowerCase();
        String animationName = String.format("%s_reload_animation", weaponName);
        return weaponAnimations.get(animationName).getKeyFrames()[0];
    }

    // projectile
    public Texture getProjectileTexture(Projectiles projectile) {
        switch (projectile) {
            case SHOTGUN_BULLET:
                return shotgun_bullet;
            case SMG_BULLET:
                return smg_bullet;
            case REVOLVER_BULLET:
                return revolver_bullet;
            case EYE_MONSTER_PROJECTILE:
                return eyeMonster_projectile;
            default:
                return null;
        }
    }

    // tree
    public Animation<Texture> getTreeAnimation() {
        return tree_animation;
    }

    public Texture getTreeTexture() {
        return tree_animation.getKeyFrames()[0];
    }

    // monster
    public Animation<Texture> getMonsterAnimation(Monsters monsterType) {
        switch (monsterType) {
            case BRAIN_MONSTER:
                return brainMonster_animation;
            case EYE_MONSTER:
                return eyeMonster_animation;
            case ELDER_MONSTER:
                //TODO
            default: return null;
        }
    }

    public Texture getMonsterTexture(Monsters monsterType) {
        switch (monsterType) {
            case BRAIN_MONSTER:
                return brainMonster_animation.getKeyFrames()[0];
            case EYE_MONSTER:
                return eyeMonster_animation.getKeyFrames()[0];
            case ELDER_MONSTER:
                return elderMonster_texture;
                // TODO: must be an animation!
            default: return null;
        }
    }

    // XP
    public Texture getXpTexture() {
        return xpTexture;
    }

    // ammo
    public Texture getAmmoTexture() {
        return ammoTexture;
    }

    //character
    private Animation<Texture> buildCharacterAnimation(String characterName, String type) {

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

    // weapon
    private Animation<Texture> buildReloadAnimation(String weaponName) {

        String pathToFolder = String.format("assets/%s/reload", weaponName);
        int numberOfFrames = countFiles(pathToFolder);

        Texture[] textureArray = new Texture[numberOfFrames];

        for (int i = 0; i < numberOfFrames; i++) {
            String rawPath = String.format("%s/reload/reload_%d.png", weaponName, i);
            Texture texture = new Texture(rawPath);
            textureArray[i] = texture;
        }

        return new Animation<>(0.1f, textureArray);
    }

    // projectile
    private Texture buildBulletTexture(String bulletName) {
        String path = String.format("assets/bullet/%s_bullet.png", bulletName);

        return new Texture(path);
    }

    // tree
    public Animation<Texture> buildTreeAnimation() {
        String pathToFolder = "assets/map_elements/tree";
        int numberOfFrames = countFiles(pathToFolder);

        Texture[] textureArray = new Texture[numberOfFrames];

        for (int i = 0; i < numberOfFrames; i++) {
            String rawPath = String.format("map_elements/tree/tree_%d.png", i);
            Texture texture = new Texture(rawPath);
            textureArray[i] = texture;
        }

        return new Animation<>(0.3f, textureArray);
    }

    // monster
    public Animation<Texture> buildBrainMonsterAnimation() {
        String pathToFolder = "assets/brainMonster";
        int numberOfFrames = countFiles(pathToFolder);

        Texture[] textureArray = new Texture[numberOfFrames];

        for (int i = 0; i < numberOfFrames; i++) {
            String rawPath = String.format("brainMonster/BrainMonster_%d.png", i);
            Texture texture = new Texture(rawPath);
            textureArray[i] = texture;
        }

        return new Animation<>(0.1f, textureArray);
    }

    public Animation<Texture> buildEyeMonsterAnimation() {
        String pathToFolder = "assets/eyeMonster";
        int numberOfFrames = countFiles(pathToFolder);

        Texture[] textureArray = new Texture[numberOfFrames];

        for (int i = 0; i < numberOfFrames; i++) {
            String rawPath = String.format("eyeMonster/EyeMonster_%d.png", i);
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
