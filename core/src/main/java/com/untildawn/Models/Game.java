package com.untildawn.Models;

public class Game {
    private GamePreferences gamePreferences;
    private Player player;
    private float time;
    private boolean isWon;

    public Game(GamePreferences gameSetting, Player player) {
        this.gamePreferences = gameSetting;
        this.player = player;
        this.time = 0f;
        this.isWon = false;;
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

        if (time >= gamePreferences.getGameTime().getTime()) {
            isWon = true;
        }
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
}
