package com.untildawn.Enums;

public enum Heros {
    SHANA(4, 4, 6),
    DIAMOND(7, 1, 3),
    SCARLET(3, 5, 7),
    LILITH(5, 3, 5),
    DASHER(2, 10, 12),
    ;

    private final int HP;
    private final int speed;
    private final int runSpeed;

    Heros(int HP, int speed, int runSpeed) {
        this.HP = HP;
        this.speed = speed;
        this.runSpeed = runSpeed;
    }

    public int getHP() {
        return HP;
    }

    public int getSpeed() {
        return speed;
    }

    public int getRunSpeed() {
        return runSpeed;
    }
}
