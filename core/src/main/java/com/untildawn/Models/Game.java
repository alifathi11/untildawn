package com.untildawn.Models;

import com.untildawn.Controllers.GameController;

public class Game {

    private GameController gameController;

    private GamePreferences gamePreferences;
    private Player player;
    private float time;
    private boolean isWon;
    private boolean isLost;
    private boolean isGaveUp;

    public Game(GamePreferences gameSetting, Player player) {
        this.gamePreferences = gameSetting;
        this.player = player;
        this.time = 0f;
        this.isWon = false;
        this.isLost = false;
        this.isGaveUp = false;
    }

    public void setController(GameController gameController) {
        this.gameController = gameController;
    }

    public GamePreferences getGamePreferences() {
        return gamePreferences;
    }

    public Player getPlayer() {
        return player;
    }

    public void setGamePreferences(GamePreferences gamePreferences) {
        this.gamePreferences = gamePreferences;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    public void increaseElapsedTime(float deltaTime) {
        this.time += deltaTime;
    }

    public void setLost(boolean lost) {
        isLost = lost;
    }

    public boolean isLost() {
        return isLost;
    }

    public boolean isWon() {
        return isWon;
    }

    public void setWon(boolean won) {
        isWon = won;
    }

    public float getElapsedTime() {
        return time;
    }

    public GameController getGameController() {
        return gameController;
    }

    public void setGaveUp(boolean gaveUp) {
        isGaveUp = gaveUp;
    }

    public boolean isGaveUp() {
        return isGaveUp;
    }
}
