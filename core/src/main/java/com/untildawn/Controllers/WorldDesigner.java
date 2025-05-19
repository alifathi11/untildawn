package com.untildawn.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.untildawn.Models.CollisionRect;
import com.untildawn.Models.Position;
import com.untildawn.Models.Tree;
import com.untildawn.Models.World;

import java.util.ArrayList;
import java.util.Random;

public class WorldDesigner {
    public void designWorld(World world) {
        world.setBackgroundTexture(new Texture(Gdx.files.internal("map_elements/map_1.png")));
        spawnRandomTrees(world);
    }

    private void spawnRandomTrees(World world) {
        ArrayList<Tree> trees = new ArrayList<>();
        Random random = new Random();

        int numberOfTrees = 60;
        int maxTriesPerTree = 100;

        int bgWidth = world.getBackgroundTexture().getWidth();
        int bgHeight = world.getBackgroundTexture().getHeight();

        float minDistance = 50f; // Minimum distance between trees

        for (int i = 0; i < numberOfTrees; i++) {
            Tree tree = new Tree();
            float treeWidth = tree.getTreeSprite().getWidth();
            float treeHeight = tree.getTreeSprite().getHeight();

            boolean placed = false;
            int tries = 0;

            while (!placed && tries < maxTriesPerTree) {
                float x = random.nextFloat() * (bgWidth - treeWidth);
                float y = random.nextFloat() * (bgHeight - treeHeight);

                boolean tooClose = false;
                for (Tree other : trees) {
                    float dx = x - other.getPosition().getX();
                    float dy = y - other.getPosition().getY();
                    float distance = (float) Math.sqrt(dx * dx + dy * dy);

                    if (distance < minDistance) {
                        tooClose = true;
                        break;
                    }
                }

                if (!tooClose) {
                    Position position = new Position((int) x, (int) y);
                    tree.setPosition(position);
                    tree.getTreeSprite().setPosition(x, y);
                    tree.setCollisionRect(new CollisionRect(x, y, treeWidth, treeHeight));

                    trees.add(tree);
                    placed = true;
                }

                tries++;
            }

            if (!placed) {
                System.out.println("Failed to place tree #" + i + " without overlap.");
            }
        }

        world.setTrees(trees);
    }


}
