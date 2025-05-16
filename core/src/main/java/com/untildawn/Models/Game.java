package com.untildawn.Models;

public class Game {
    private GameSetting gameSetting;
    private Player player;

    public Game(GameSetting gameSetting, Player player) {
        this.gameSetting = gameSetting;
        this.player = player;
    }

    public GameSetting getGameSetting() {
        return gameSetting;
    }

    public Player getPlayer() {
        return player;
    }

    public void setGameSetting(GameSetting gameSetting) {
        this.gameSetting = gameSetting;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
