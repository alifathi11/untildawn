package com.untildawn.Enums;

public enum Hero {
    SHANA(4, 4),
    DIAMOND(7, 1),
    SCARLET(3, 5),
    LILITH(5, 3),
    DASHER(2, 10),
    ;

    private final int HP;
    private final int speed;

    Hero(int HP, int speed) {
        this.HP = HP;
        this.speed = speed;
    }

    public int getHP() {
        return HP;
    }

    public int getSpeed() {
        return speed;
    }
}
