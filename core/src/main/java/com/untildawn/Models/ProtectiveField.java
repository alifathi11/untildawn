package com.untildawn.Models;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class ProtectiveField {
    private float centerX, centerY;
    private float width, height;
    private float shrinkSpeed;
    private boolean active;
    private Game game;

    public ProtectiveField(float initialWidth, float initialHeight, float shrinkSpeed, float centerX, float centerY) {
        this.width = initialWidth;
        this.height = initialHeight;
        this.shrinkSpeed = shrinkSpeed;
        this.centerX = centerX;
        this.centerY = centerY;
        this.active = false;
        this.game = App.getCurrentGame();
    }

    public void activate() {
        this.active = true;
    }

    public void deactivate() {
        this.active = false;
    }

    public boolean isActive() {
        return active;
    }

    public void update(float deltaTime, Player player) {
        if (!active) return;

        float shrink = shrinkSpeed * deltaTime;

        width -= shrink;
        height -= shrink;

        if (width < 800) width = 800;
        if (height < 800) height = 800;

        float halfW = width / 2f;
        float halfH = height / 2f;

        float minX = centerX - halfW;
        float maxX = centerX + halfW;
        float minY = centerY - halfH;
        float maxY = centerY + halfH;

        float px = player.getPosition().getX();
        float py = player.getPosition().getY();

        boolean outside = px <= minX || px >= maxX || py <= minY || py >= maxY;

        if (outside) {
            if (!player.isInvincible()) {
                player.decreaseHP();
                player.setInvincible(true);
                // TODO: sfx
            }
        }

        px = MathUtils.clamp(px, minX, maxX);
        py = MathUtils.clamp(py, minY, maxY);

        player.setPosition(new Position(px, py));
        player.getPlayerSprite().setPosition(px, py);
    }

    public void render(ShapeRenderer shapeRenderer) {
        if (!active) return;

        if (game.isWon() ||
            game.isLost() ||
            game.isGaveUp() ||
            game.getGameController().getPauseMenuController().isPaused()) return;

        float halfW = width / 2f;
        float halfH = height / 2f;

        float x = centerX - halfW;
        float y = centerY - halfH;

        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(x, y, width, height);
    }
}

