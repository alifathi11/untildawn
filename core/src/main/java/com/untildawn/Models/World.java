package com.untildawn.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;

public class World {
    private Texture backgroundTexture;
    private Sprite backgroundSprite;
    private OrthographicCamera camera;

    private ArrayList<Tree> trees;
    private ArrayList<Monster> monsters;
    private ArrayList<XP> xps;
    private ArrayList<Ammo> ammo;
    private ArrayList<Heart> hearts;

    private ProtectiveField protectiveField;


    public World(OrthographicCamera camera) {

        this.camera = camera;

        this.trees = new ArrayList<>();
        this.monsters = new ArrayList<>();
        this.xps = new ArrayList<>();
        this.hearts = new ArrayList<>();
        this.ammo = new ArrayList<>();
    }

    public void setBackgroundTexture(Texture backgroundTexture) {
        this.backgroundTexture = backgroundTexture;
    }

    public void setSprite() {
        this.backgroundSprite = new Sprite(backgroundTexture);
        this.backgroundSprite.setSize(3296, 2304);

        // protected field
        float bgWidth = backgroundSprite.getWidth();
        float bgHeight = backgroundSprite.getHeight();

        protectiveField = new ProtectiveField(
            bgWidth,
            bgHeight,
            30f,
            bgWidth / 2f,
            bgHeight / 2f
        );
    }

    public Sprite getBackgroundSprite() {
        return backgroundSprite;
    }

    public Texture getBackgroundTexture() {
        return backgroundTexture;
    }

    public void setTrees(ArrayList<Tree> trees) {
        this.trees = trees;
    }

    public ArrayList<Tree> getTrees() {
        return trees;
    }

    public void addMonster(Monster monster) {
        this.monsters.add(monster);
    }

    public void deleteMonster(Monster monster) {
        this.monsters.remove(monster);
    }

    public ArrayList<Monster> getMonsters() {
        return monsters;
    }

    public void addXP(XP xp) {
        this.xps.add(xp);
    }
    public void deleteXP(XP xp) {
        this.xps.remove(xp);
    }

    public ArrayList<XP> getXps() {
        return xps;
    }

    public ArrayList<Ammo> getAmmo() {
        return ammo;
    }

    public void addAmmo(Ammo ammo) {
        this.ammo.add(ammo);
    }

    public void deleteAmmo(Ammo ammo) {
        this.ammo.remove(ammo);
    }

    public ProtectiveField getProtectiveField() {
        return protectiveField;
    }

    public void addHeart(Heart heart) {
        this.hearts.add(heart);
    }

    public void deleteHeart(Heart heart) {
        this.hearts.remove(heart);
    }

    public ArrayList<Heart> getHearts() {
        return hearts;
    }
}
