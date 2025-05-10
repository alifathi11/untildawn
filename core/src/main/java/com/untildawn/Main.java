package com.untildawn;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.untildawn.Controllers.LoginMenuController;
import com.untildawn.Controllers.SignupMenuController;
import com.untildawn.Models.GameAssetManager;
import com.untildawn.Models.UserDataHandler;
import com.untildawn.Views.LoginMenuView;
import com.untildawn.Views.SignupMenuView;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    private static Main main;
    private static SpriteBatch batch;
    private Texture image;

    @Override
    public void create() {
        main = this;
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");

        prepareStartingApp();
        main.setScreen(new SignupMenuView(new SignupMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }

    public static Main getMain() {
        return main;
    }

    public static SpriteBatch getBatch() {
        return batch;
    }

    private void prepareStartingApp() {
        UserDataHandler.loadUsers();
    }
}
