package com.untildawn.Models;

public class GameProfile {
    private int score;
    private int killCount;
    private int maxTimeAlive;

    public GameProfile(int score,
                       int killCount,
                       int maxTimeAlive) {
        this.score = score;
        this.killCount = killCount;
        this.maxTimeAlive = maxTimeAlive;
    }

    public int getScore() {
        return score;
    }

    public int getKillCount() {
        return killCount;
    }

    public int getMaxTimeAlive() {
        return maxTimeAlive;
    }

    public void increaseScore(int deltaScore) {
        this.score += deltaScore;
    }

    public void increaseKillCount(int deltaKillCount) {
        this.killCount += deltaKillCount;
    }

    public void increaseMaxTimeAlive(int deltaMaxTimeAlive) {
        this.maxTimeAlive += deltaMaxTimeAlive;
    }
}
