package com.untildawn.Models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class DeathAnimation {
    private Animation<Texture> animation;
    private float stateTime;
    private Vector2 position;
    private boolean finished;

    public DeathAnimation(Animation<Texture> animation, Vector2 position) {
        this.animation = animation;
        this.position = position;
        this.stateTime = 0f;
        this.finished = false;
    }

    public void update(float deltaTime) {
        stateTime += deltaTime;
        if (animation.isAnimationFinished(stateTime)) {
            finished = true;
        }
    }

    public void render(SpriteBatch batch) {
        if (!finished) {
            Texture currentFrame = animation.getKeyFrame(stateTime, false);
            batch.draw(currentFrame, position.x, position.y);
        }
    }

    public boolean isFinished() {
        return finished;
    }
}
