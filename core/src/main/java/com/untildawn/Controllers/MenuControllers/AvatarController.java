package com.untildawn.Controllers.MenuControllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.untildawn.Enums.Menus;
import com.untildawn.Models.App;
import com.untildawn.Models.GameAssetManager;
import com.untildawn.Models.MenuManager;

import java.util.ArrayList;

public class AvatarController {
    private ArrayList<Texture> iconTextures = new ArrayList<>();
    private ArrayList<String> iconPaths = new ArrayList<>();
    private ArrayList<Image> iconImages = new ArrayList<>();
    private Texture selectedCustomImage;


    public ArrayList<Texture> getIconTextures() {
        return iconTextures;
    }

    public ArrayList<Image> getIconImages() {
        return iconImages;
    }

    public void loadIcons(int count) {
        for (int i = 0; i < count; i++) {
            String path = "userAvatars/" + i + ".png";
            Texture texture = new Texture(Gdx.files.internal(path));
            iconTextures.add(texture);
            iconPaths.add(path);

            Image image = new Image(texture);
            final int index = i;
            image.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ClickListener() {
                @Override
                public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                    handleIconSelected(index);
                }
            });

            iconImages.add(image);
        }
    }

    public void handleIconSelected(int index) {
        String avatarPath = iconPaths.get(index);
        App.getCurrentUser().setAvatarPath(avatarPath);
        MenuManager.setScreen(Menus.PROFILE_MENU);
    }

    public void setCustomImage(String path) {
        App.getCurrentUser().setAvatarPath(path);
        MenuManager.setScreen(Menus.PROFILE_MENU);
    }


    public Texture getSelectedCustomImage() {
        return selectedCustomImage;
    }

    public void dispose() {
        for (Texture t : iconTextures) t.dispose();
        if (selectedCustomImage != null) selectedCustomImage.dispose();
    }
}
