package com.untildawn;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.untildawn.Models.MenuManager;
import com.untildawn.Enums.Menus;
import com.untildawn.Models.*;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    private static Main main;
    private static SpriteBatch batch;
    private Texture image;

    public Main() {
        main = this;
    }

    public static Main getInstance() {
        return main;
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");

        prepareStartingApp();
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        System.out.println("Game shutting down...");

        MusicManager.dispose();

        if (App.getCurrentUser() != null) {
            UserDataHandler.saveUsers();
        }

        batch.dispose();
        image.dispose();

        super.dispose();
    }

    public static Main getMain() {
        return main;
    }

    public static SpriteBatch getBatch() {
        return batch;
    }

    private void prepareStartingApp() {
        UserDataHandler.loadUsers();
        MusicManager.changeTrack(0);
        MusicManager.setVolume(0.5f);
        MusicManager.play();

        setCustomCursor("cursor.png", 0, 0);

        MenuManager.setScreen(Menus.SIGNUP_MENU);
    }

    public static void setCustomCursor(String imagePath, int hotspotX, int hotspotY) {
        Pixmap pixmap = new Pixmap(Gdx.files.internal(imagePath));
        Cursor cursor = Gdx.graphics.newCursor(pixmap, hotspotX, hotspotY);
        Gdx.graphics.setCursor(cursor);
        pixmap.dispose();
    }
}
