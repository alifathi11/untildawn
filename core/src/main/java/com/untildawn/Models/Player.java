package com.untildawn.Models;

public class Player {
    private User user;
    private int score;
    private int HP;
    private Game game;

    public Player(User user,
                  int score,
                  int HP,
                  Game game) {
        this.user = user;
        this.score = score;
        this.HP = HP;
        this.game = game;
    }

    public int getHP() {
        return HP;
    }

    public int getScore() {
        return score;
    }

    public Game getGame() {
        return game;
    }

    public User getUser() {
        return user;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
