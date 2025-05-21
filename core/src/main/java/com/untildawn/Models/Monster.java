package com.untildawn.Models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.untildawn.Enums.Monsters;

public class Monster {
    private Texture texture;
    private Sprite sprite;
    private Position position;

    private float speed;
    private float HP;
    private int killXP;

    private boolean isEyeMonster;
    private boolean reachedHoverPoint = false;
    private Vector2 hoverOffsetTarget;

    private float time;
    private float elapsedTime = 0f;

    private float shootCoolDown = 3f;
    private float timeSinceLastShot = 0f;


    private CollisionRect collisionRect;

    private Monsters monsterType;

    public Monster(Monsters monsterType) {
        this.texture = GameAssetManager.getGameAssetManager().getMonsterTexture(monsterType);
        this.sprite = new Sprite(texture);
        this.sprite.setSize(50, 50);
        this.collisionRect = new CollisionRect(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());

        this.monsterType = monsterType;
        this.HP = monsterType.getHP();
        this.speed = monsterType.getSpeed();
        this.killXP = monsterType.getKillXP();
    }

    public void setPosition(Position position) {
        this.position = position;
        this.sprite.setPosition(position.getX(), position.getY());
        this.collisionRect.move(position.getX(), position.getY());
    }

    public Position getPosition() {
        return position;
    }

    public CollisionRect getCollisionRect() {
        return collisionRect;
    }

    public void setCollisionRect(CollisionRect collisionRect) {
        this.collisionRect = collisionRect;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getSpeed() {
        return speed;
    }

    public void setHP(float HP) {
        this.HP = HP;
    }

    public float getHP() {
        return HP;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Texture getTexture() {
        return texture;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public int getKillXP() {
        return killXP;
    }

    public void decreaseHP(int deltaHP) {
        this.HP -= deltaHP;
    }

    public Monsters getMonsterType() {
        return monsterType;
    }

    public void setEyeMonsterBehavior(boolean isEye) {
        this.isEyeMonster = isEye;
    }

    public boolean hasReachedHover() {
        return reachedHoverPoint;
    }

    public void setReachedHover(boolean reached) {
        this.reachedHoverPoint = reached;
    }

    public Vector2 getHoverOffsetTarget() {
        return hoverOffsetTarget;
    }

    public void setHoverOffsetTarget(Vector2 target) {
        this.hoverOffsetTarget = target;
    }

    public boolean isEyeMonster() {
        return isEyeMonster;
    }

    public boolean isReachedHoverPoint() {
        return reachedHoverPoint;
    }

    public void setEyeMonster(boolean eyeMonster) {
        isEyeMonster = eyeMonster;
    }

    public void setReachedHoverPoint(boolean reachedHoverPoint) {
        this.reachedHoverPoint = reachedHoverPoint;
    }

    public void setElapsedTime(float elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public float getElapsedTime() {
        return elapsedTime;
    }

    public void addElapsedTime(float dt) {
        elapsedTime += dt;
    }

    public float getTimeSinceLastShot() {
        return timeSinceLastShot;
    }

    public void setTimeSinceLastShot(float time) {
        this.timeSinceLastShot = time;
    }

    public void addTimeSinceLastShot(float delta) {
        this.timeSinceLastShot += delta;
    }

    public void resetShotCooldown() {
        this.timeSinceLastShot = 0f;
    }

}
