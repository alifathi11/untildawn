package com.untildawn.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.untildawn.Models.GameAssetManager;
import com.untildawn.Models.Weapon;

public class WeaponAnimations {
    private Weapon weapon;

    public WeaponAnimations(Weapon weapon) {
        this.weapon = weapon;
    }

    public void reloadAnimation() {
        if (!weapon.isReloading()) return;

        Animation<Texture> animation = GameAssetManager.getGameAssetManager().getReloadAnimation();
        weapon.getSmgSprite().setRegion(animation.getKeyFrame(weapon.getTime()));
        weapon.setTime(weapon.getTime() + Gdx.graphics.getDeltaTime());
        weapon.setReloadTimeElapsed(weapon.getReloadTimeElapsed() + Gdx.graphics.getDeltaTime());

        if (weapon.getReloadTimeElapsed() >= weapon.getRELOAD_DURATION()) {
            weapon.setReloading(false);
            weapon.setTime(0f);
            weapon.getSmgSprite().setRegion(animation.getKeyFrame(0f));
        }

        animation.setPlayMode(Animation.PlayMode.LOOP);
    }
}
