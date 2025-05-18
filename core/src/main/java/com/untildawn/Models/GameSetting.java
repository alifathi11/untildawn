package com.untildawn.Models;

import com.untildawn.Enums.Heros;
import com.untildawn.Enums.Time;
import com.untildawn.Enums.Weapons;

public class GameSetting {
    private Time gameTime;
    private Weapons weapon;
    private Heros hero;

    public GameSetting(Time gameTime, Weapons weapon, Heros hero) {
        this.gameTime = gameTime;
        this.weapon = weapon;
        this.hero = hero;
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
}
