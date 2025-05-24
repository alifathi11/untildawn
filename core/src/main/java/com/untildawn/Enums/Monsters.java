package com.untildawn.Enums;

public enum Monsters {
    BRAIN_MONSTER(5, 100f, 3),
    EYE_MONSTER(50, 100f, 10),
    ELDER_MONSTER(200, 100f, 50),
    ;

    private final int HP;
    private final float speed;
    private final int killXP;

    Monsters(int HP,
             float speed,
             int killXP) {
        this.HP = HP;
        this.speed = speed;
        this.killXP = killXP;
    }

    public int getHP() {
        return HP;
    }

    public int getKillXP() {
        return killXP;
    }

    public float getSpeed() {
        return speed;
    }
}
