package com.untildawn.Models;

import com.untildawn.Enums.Heros;
import com.untildawn.Enums.Time;
import com.untildawn.Enums.Weapons;

public class GamePreferences {
    private Time gameTime;
    private Weapons weapon;
    private boolean autoReload;
    private Heros hero;

    public GamePreferences(Time gameTime, Weapons weapon, Heros hero, boolean autoReload) {
        this.gameTime = gameTime;
        this.weapon = weapon;
        this.hero = hero;
        this.autoReload = autoReload;
    }

    public Heros getHero() {
        return hero;
    }

    public Time getGameTime() {
        return gameTime;
    }

    public Weapons getWeapon() {
        return weapon;
    }

    public void setGameTime(Time gameTime) {
        this.gameTime = gameTime;
    }

    public void setWeapon(Weapons weapon) {
        this.weapon = weapon;
    }

    public void setHero(Heros hero) {
        this.hero = hero;
    }

    public void setAutoReload(boolean autoReload) {
        this.autoReload = autoReload;
    }

    public boolean isAutoReload() {
        return autoReload;
    }
}
