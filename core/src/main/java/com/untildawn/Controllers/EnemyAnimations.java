package com.untildawn.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.untildawn.Models.GameAssetManager;
import com.untildawn.Models.Tree;

public class EnemyAnimations {

    public void treeAnimations(Tree tree) {
        Animation<Texture> animation = GameAssetManager.getGameAssetManager().getTreeAnimation();

        if (tree.isAnimating()) {
            tree.setTime(tree.getTime() + Gdx.graphics.getDeltaTime());
            animation.setPlayMode(Animation.PlayMode.LOOP);
            tree.getTreeSprite().setRegion(animation.getKeyFrame(tree.getTime()));
        } else {
            tree.getTreeSprite().setRegion(tree.getTreeTexture());
        }
    }

}
