package com.untildawn.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class World {
    private Texture backgroundTexture;
    private ArrayList<Tree> trees;

    public World() {
        this.trees = new ArrayList<>();
    }

    public void setBackgroundTexture(Texture backgroundTexture) {
        this.backgroundTexture = backgroundTexture;
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
}
