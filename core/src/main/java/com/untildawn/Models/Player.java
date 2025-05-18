package com.untildawn.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player {
    private Texture playerTexture = new Texture(
        GameAssetManager.getGameAssetManager().getCharacter1_idle0()
    );
    private Sprite playerSprite = new Sprite(playerTexture);
    private Position position;
    private CollisionRect collisionRect;

    private User user;
    private int score;
    private float HP;
    private float speed;
    private Game game;
    private float time;

    private boolean isPlayerIdle = true;
    private boolean isPlayerRunning = false;

    public Player(User user,
                  int score,
                  float HP,
                  float speed,
                  Game game) {
        this.user = user;
        this.score = score;
        this.HP = HP;
        this.game = game;
        this.speed = speed;
        this.position = new Position(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);

        this.playerSprite.setPosition((float) Gdx.graphics.getWidth() / 2, (float) Gdx.graphics.getHeight() / 2);
        this.playerSprite.setSize(playerTexture.getWidth() * 3, playerTexture.getHeight() * 3);
        this.collisionRect = new CollisionRect((float) Gdx.graphics.getWidth() / 2,
                                               (float) Gdx.graphics.getHeight() / 2,
                                               (float) playerTexture.getWidth(),
                                               (float) playerTexture.getHeight());
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public float getHP() {
        return HP;
    }

    public int getScore() {
        return score;
    }

    public Game getGame() {
        return game;
    }

    public User getUser() {
        return user;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public CollisionRect getCollisionRect() {
        return collisionRect;
    }

    public void setCollisionRect(CollisionRect collisionRect) {
        this.collisionRect = collisionRect;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public Sprite getPlayerSprite() {
        return playerSprite;
    }

    public void setPlayerSprite(Sprite playerSprite) {
        this.playerSprite = playerSprite;
    }

    public Texture getPlayerTexture() {
        return playerTexture;
    }

    public void setHP(float HP) {
        this.HP = HP;
    }

    public void setPlayerTexture(Texture playerTexture) {
        this.playerTexture = playerTexture;
    }

    public boolean isPlayerIdle() {
        return isPlayerIdle;
    }

    public boolean isPlayerRunning() {
        return isPlayerRunning;
    }
}
