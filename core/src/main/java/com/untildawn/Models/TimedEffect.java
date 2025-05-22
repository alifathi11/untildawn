package com.untildawn.Models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class TimedEffect {
    private float x, y;
    private float timeToLive;

    public TimedEffect(float x, float y, float duration) {
        this.x = x;
        this.y = y;
        this.timeToLive = duration;
    }

    public void update(float deltaTime) {
        timeToLive -= deltaTime;
    }

    public boolean isFinished() {
        return timeToLive <= 0;
    }

    public void render(Batch batch, Texture texture) {
        if (!isFinished()) {
            batch.draw(texture, x, y);
        }
    }
}

