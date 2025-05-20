package com.untildawn.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;

public class World {
    private Texture backgroundTexture;
    private Sprite backgroundSprite;

    private ArrayList<Tree> trees;
    private ArrayList<BrainMonster> brainMonsters;
    private ArrayList<Monster> monsters;
    private ArrayList<XP> xps;

    public World() {
        this.trees = new ArrayList<>();
    }

    public void setBackgroundTexture(Texture backgroundTexture) {
        this.backgroundTexture = backgroundTexture;
    }
    public void setSprite() {
        this.backgroundSprite = new Sprite(backgroundTexture);
        this.backgroundSprite.setSize(3296, 2304);

        this.trees = new ArrayList<>();
        this.brainMonsters = new ArrayList<>();
        this.monsters = new ArrayList<>();
        this.xps = new ArrayList<>();
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

    public void addBrainMonster(BrainMonster monster) {
        this.brainMonsters.add(monster);
        this.monsters.add(monster);
    }
    public void deleteBrainMonster(BrainMonster monster) {
        this.brainMonsters.remove(monster);
        this.monsters.remove(monster);
    }

    public void deleteMonster(Monster monster) {
        this.monsters.remove(monster);
        if (monster instanceof BrainMonster) this.brainMonsters.remove(monster);
        // TODO: add other types
    }

    public ArrayList<BrainMonster> getBrainMonsters() {
        return brainMonsters;
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
}
