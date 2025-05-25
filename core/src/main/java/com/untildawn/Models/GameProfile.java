package com.untildawn.Models;

public class GameProfile {
    private int score;
    private int killCount;
    private float maxTimeAlive;

    public GameProfile() {}

    public GameProfile(int score,
                       int killCount,
                       float maxTimeAlive) {
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

    public float getMaxTimeAlive() {
        return maxTimeAlive;
    }

    public void increaseScore(int deltaScore) {
        this.score += deltaScore;
    }

    public void increaseKillCount(int deltaKillCount) {
        this.killCount += deltaKillCount;
    }

    public void setMaxTimeAlive(float maxTimeAlive) {
        this.maxTimeAlive = maxTimeAlive;
    }
}
