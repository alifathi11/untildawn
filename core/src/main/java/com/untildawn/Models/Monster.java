package com.untildawn.Models;

public class Monster {
    private CollisionRect collisionRect;
    private float HP;
    private float speed;
    private int killXP;

    private Position position;

    public Monster(float HP, float speed, int killXP) {
        this.HP = HP;
        this.speed = speed;
        this.killXP = killXP;
    }

    public CollisionRect getCollisionRect() {
        return collisionRect;
    }

    public void decreaseHP(float deltaHP) {
        this.HP = Math.max(0, this.HP - deltaHP);
    }

    public float getHP() {
        return HP;
    }

    public float getSpeed() {
        return speed;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getKillXP() {
        return killXP;
    }
}
