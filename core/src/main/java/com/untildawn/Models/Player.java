package com.untildawn.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.untildawn.Enums.Heros;

public class Player {
    private Texture playerTexture;
    private Sprite playerSprite;
    private Position position;
    private CollisionRect collisionRect;

    private Weapon currentWeapon;

    private User user;
    private int kill;
    private int level;
    // TODO: Longest time alive

    private float maxHP;
    private float HP;
    private float speed;
    private float runSpeed;
    private Game game;
    private Heros hero;

    private float time;
    private boolean isInvincible = false;
    private boolean isGhost = false;
    private float invincibilityDuration = 1f;
    private float invincibleTime = 0f;

    private int xp;

    private boolean isPlayerIdle = false;
    private boolean isPlayerWalking = false;
    private boolean isPlayerRunning = false;

    public Player(User user,
                  Heros hero,
                  Game game) {

        this.user = user;

        this.hero = hero;
        this.playerTexture = GameAssetManager.getGameAssetManager().getCharacter_walk0(hero);
        this.playerSprite = new Sprite(playerTexture);


        this.level = 1;
        this.xp = 0;
        this.maxHP = hero.getHP();
        this.HP = maxHP;
        this.kill = 0;
        this.speed = hero.getSpeed();
        this.runSpeed = hero.getRunSpeed();
        this.game = game;
        this.position = new Position((float) Gdx.graphics.getWidth() / 2, (float) Gdx.graphics.getHeight() / 2);

        this.playerSprite.setPosition((float) Gdx.graphics.getWidth() / 2, (float) Gdx.graphics.getHeight() / 2);
        this.playerSprite.setSize(playerTexture.getWidth() * 3, playerTexture.getHeight() * 3);
        this.collisionRect = new CollisionRect(
            position.getX(),
            position.getY(),
            (float) playerTexture.getWidth(),
            (float) playerTexture.getHeight()
        );
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public float getHP() {
        return HP;
    }

    public Game getGame() {
        return game;
    }

    public User getUser() {
        return user;
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

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public CollisionRect getCollisionRect() {
        return collisionRect;
    }

    public void setCollisionRect(CollisionRect collisionRect) {
        this.collisionRect = collisionRect;
    }

    public float getSpeed() {
        if (isPlayerRunning) return runSpeed;
        else return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public Sprite getPlayerSprite() {
        return playerSprite;
    }

    public void setPlayerSprite(Sprite playerSprite) {
        this.playerSprite = playerSprite;
    }

    public Texture getPlayerTexture() {
        return playerTexture;
    }

    public void setHP(float HP) {
        this.HP = HP;
    }

    public void setPlayerTexture(Texture playerTexture) {
        this.playerTexture = playerTexture;
    }

    public boolean isPlayerIdle() {
        return isPlayerIdle;
    }

    public boolean isPlayerWalking() {
        return isPlayerWalking;
    }

    public void setPlayerIdle(boolean playerIdle) {
        isPlayerIdle = playerIdle;
    }

    public void setPlayerWalking(boolean playerWalking) {
        isPlayerWalking = playerWalking;
    }

    public Heros getHero() {
        return hero;
    }

    public void setHero(Heros hero) {
        this.hero = hero;
    }

    public boolean isPlayerRunning() {
        return isPlayerRunning;
    }

    public void setPlayerRunning(boolean playerRunning) {
        isPlayerRunning = playerRunning;
    }

    public void decreaseHP() {
        this.HP--;
    }

    public int getXpToLevelUp() {
        return level * 20;
    }

    public void increaseXP(int deltaXP) {

        int xpToLevelUp = getXpToLevelUp();

        this.xp += deltaXP;
        if (this.xp >= xpToLevelUp) {
            levelUP();
        }
    }

    public int getLevel() {
        return level;
    }

    public void levelUP() {
        this.level++;
        // TODO: show abilities
    }

    public int getXp() {
        return xp;
    }

    public void setCurrentWeapon(Weapon currentWeapon) {
        this.currentWeapon = currentWeapon;
    }

    public Weapon getCurrentWeapon() {
        return currentWeapon;
    }

    public float getInvincibilityDuration() {
        return invincibilityDuration;
    }

    public void setInvincibilityDuration(float invincibilityDuration) {
        this.invincibilityDuration = invincibilityDuration;
    }

    public void setInvincibleTime(float invincibleTime) {
        this.invincibleTime = invincibleTime;
    }

    public void setInvincible(boolean invincible) {
        isInvincible = invincible;
    }

    public float getInvincibleTime() {
        return invincibleTime;
    }

    public boolean isInvincible() {
        return isInvincible;
    }

    public void increaseInvincibleTime(float deltaTime) {
        this.invincibleTime += deltaTime;
    }

    public void increaseHP() {
        this.HP = Math.min(this.HP + 1, this.maxHP);
    }

    public void increaseMaxHP() {
        this.maxHP++;
    }

    public float getMaxHP() {
        return maxHP;
    }

    public void increaseKill() {
        this.kill++;
    }

    public int getKill() {
        return kill;
    }

    public void setOnGodMode() {
        this.maxHP = Integer.MAX_VALUE;
        this.HP = Integer.MAX_VALUE;
        this.currentWeapon.setTotalAmmo(Integer.MAX_VALUE);
    }

    public void setGhostMode(boolean ghostMode) {
        this.getCollisionRect().setGhostMode(ghostMode);
        this.isGhost = ghostMode;
        if (ghostMode) {
            this.speed = 20f;
            this.runSpeed = 20f;
        } else {
            this.speed = hero.getSpeed();
            this.runSpeed = hero.getRunSpeed();
        }
    }

    public boolean isOnGhostMode() {
        return isGhost;
    }
}
