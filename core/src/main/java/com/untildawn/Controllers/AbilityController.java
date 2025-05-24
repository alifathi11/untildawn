package com.untildawn.Controllers;

import com.untildawn.Enums.Abilities;
import com.untildawn.Models.App;
import com.untildawn.Models.Player;
import com.untildawn.Models.Weapon;

import java.util.Random;

public class AbilityController {

    private Abilities[] abilities;
    private Player player;
    private Weapon weapon;

    public AbilityController() {
        abilities = new Abilities[] {
            Abilities.DAMAGER,
            Abilities.AMMOCREASE,
            Abilities.SPEEDY,
            Abilities.VITALITY,
            Abilities.PROCREASE
        };
    }

    public void addAbility() {
        Random random = new Random();
        Abilities ability = abilities[random.nextInt(abilities.length)];

        player = App.getCurrentGame().getGameController().getPlayerController().getPlayer();
        weapon = player.getCurrentWeapon();

        switch (ability) {
            case VITALITY:
                player.increaseMaxHP();
                break;
            case DAMAGER:
                weapon.setDamagerAbilityTimer(0f);
                weapon.setDamager(true);
                break;
            case AMMOCREASE:
                weapon.setAmmoPerMagazine(weapon.getAmmoPerMagazine() + 5);
                weapon.setAmmo(weapon.getAmmoPerMagazine());
                weapon.setTotalAmmo(Math.max(weapon.getTotalAmmo(), weapon.getAmmoPerMagazine()));
                break;
            case SPEEDY:
                if (player.isOnSpeedy()) return;
                player.setSpeed(player.getSpeed() * 2);
                player.setRunSpeed(player.getRunSpeed() * 2);
                player.setSpeedyAbilityTimer(0f);
                player.setSpeedy(true);
                break;
            case PROCREASE:
                weapon.setProjectileCount(weapon.getProjectileCount() * 2);
                break;
        }

        player.addAbility(ability);
    }
}
