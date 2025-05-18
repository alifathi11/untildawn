package com.untildawn.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class World {
    private Texture backgroundTexture;
    private float backgroundX;
    private float backgroundY;

    public World(int x, int y) {
        this.backgroundTexture = new Texture(Gdx.files.internal("map_elements/map.png"));
        this.backgroundX = x;
        this.backgroundY = y;

    }

    public void setBackgroundTexture(Texture backgroundTexture) {
        this.backgroundTexture = backgroundTexture;
    }

    public void setBackgroundX(float backgroundX) {
        this.backgroundX = backgroundX;
    }

    public void setBackgroundY(float backgroundY) {
        this.backgroundY = backgroundY;
    }

    public float getBackgroundX() {
        return backgroundX;
    }

    public float getBackgroundY() {
        return backgroundY;
    }

    public Texture getBackgroundTexture() {
        return backgroundTexture;
    }
}
