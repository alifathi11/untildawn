package com.untildawn.Models;

public class Game {
    private GamePreferences gamePreferences;
    private Player player;

    public Game(GamePreferences gameSetting, Player player) {
        this.gamePreferences = gameSetting;
        this.player = player;
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
}
