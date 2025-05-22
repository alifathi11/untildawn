package com.untildawn.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.untildawn.Enums.Weapons;
import com.untildawn.Main;
import com.untildawn.Models.*;

import java.util.HashMap;
import java.util.Map;

public class WeaponController {
    private WeaponAnimations weaponAnimations;
    private final ProjectileController projectileController;
    private Player player;

    private final Map<Integer, Weapon> weapons;

    public WeaponController(ProjectileController projectileController, Weapons weaponType) {
        this.projectileController = projectileController;
        weapons = new HashMap<>();
        weapons.put(1, new Weapon(Weapons.REVOLVER));
        weapons.put(2, new Weapon(Weapons.SMG));
        weapons.put(3, new Weapon(Weapons.SHOTGUN));

        this.player = App.getCurrentGame().getPlayer();

        switch (weaponType) {
            case REVOLVER:
                player.setCurrentWeapon(weapons.get(1));
                weapons.get(1).setTotalAmmo(weapons.get(1).getTotalAmmo() * 2);
                break;
            case SMG:
                player.setCurrentWeapon(weapons.get(2));
                weapons.get(2).setTotalAmmo(weapons.get(2).getTotalAmmo() * 2);
                break;
            case SHOTGUN:
                player.setCurrentWeapon(weapons.get(3));
                weapons.get(3).setTotalAmmo(weapons.get(3).getTotalAmmo() * 2);
                break;
            default: return;
        }

        this.weaponAnimations = new WeaponAnimations();

    }

    public void update() {
        float playerX = player.getPosition().getX();
        float playerY = player.getPosition().getY();

        player.getCurrentWeapon().setPosition(new Position(playerX + 40, playerY + 60));
        player.getCurrentWeapon().getWeaponSprite().setCenter(playerX + 20, playerY + 20);
        player.getCurrentWeapon().getWeaponSprite().draw(Main.getBatch());

        if (player.getCurrentWeapon().isReloading()) {
            weaponAnimations.setWeapon(player.getCurrentWeapon());
            weaponAnimations.reloadAnimation();
        }
        player.getCurrentWeapon().setTimeSinceLastShot(player.getCurrentWeapon().getTimeSinceLastShot() + Gdx.graphics.getDeltaTime());
    }

    public void handleWeaponRotation(int x, int y) {
        Sprite weaponSprite = player.getCurrentWeapon().getWeaponSprite();

        float weaponCenterX = (float) Gdx.graphics.getWidth() / 2;
        float weaponCenterY = (float) Gdx.graphics.getHeight() / 2;

        float angle = (float) Math.atan2(y - weaponCenterY, x - weaponCenterX);

        weaponSprite.setRotation((float) (Math.PI - angle * MathUtils.radiansToDegrees));
    }

    public void handleWeaponShoot(Vector2 playerPosition, Vector2 mouseWorldPosition) {
        Weapon weapon = player.getCurrentWeapon();
        if (weapon.getTimeSinceLastShot() >= weapon.getShootCoolDown()
            && weapon.getAmmo() > 0) {

            Vector2 direction = new Vector2(mouseWorldPosition).sub(playerPosition).nor();
            Projectile projectile = new Projectile(
                playerPosition,
                direction,
                weapon.getWeaponType().getProjectile()
            );
            projectileController.addProjectile(projectile);

            String sfxName = player.getCurrentWeapon().getWeaponType().name().toLowerCase() + "_shot";
            SFXManager.play(sfxName);

            weapon.setAmmo(weapon.getAmmo() - 1);
            player.getCurrentWeapon().setTimeSinceLastShot(0f);

            GamePreferences gamePreferences = App.getCurrentGame().getGamePreferences();
            if (gamePreferences.isAutoReload() && weapon.getAmmo() == 0) weapon.reload();
        }
    }

    public void changeWeapon(int weaponNumber) {
        switch (weaponNumber) {
            case 1:
                player.setCurrentWeapon(weapons.get(1));
                break;
            case 2:
                player.setCurrentWeapon(weapons.get(2));
                break;
            case 3:
                player.setCurrentWeapon(weapons.get(3));
                break;
        }
    }

    public Weapon getCurrentWeapon() {
        return player.getCurrentWeapon();
    }
    public Weapon getWeaponByType(Weapons weaponType) {
        switch (weaponType) {
            case REVOLVER:
                return weapons.get(1);
            case SMG:
                return weapons.get(2);
            case SHOTGUN:
                return weapons.get(3);
            default: return null;
        }
    }
}
