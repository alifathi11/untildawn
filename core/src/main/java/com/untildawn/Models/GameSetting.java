package com.untildawn.Models;

import com.untildawn.Enums.Hero;
import com.untildawn.Enums.Time;
import com.untildawn.Enums.Weapon;

public class GameSetting {
    private Time gameTime;
    private Weapon weapon;
    private Hero hero;

    public GameSetting(Time gameTime, Weapon weapon, Hero hero) {
        this.gameTime = gameTime;
        this.weapon = weapon;
        this.hero = hero;
    }

    public Hero getHero() {
        return hero;
    }

    public Time getGameTime() {
        return gameTime;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setGameTime(Time gameTime) {
        this.gameTime = gameTime;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }
}
