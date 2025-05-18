package com.untildawn;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.untildawn.Enums.MenuManager;
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

        //// cheat
        User user1 = new User("ali1", "", "1234", new SecurityQuestion("", ""));
        user1.getGameProfile().increaseKillCount(10);
        user1.getGameProfile().increaseScore(100);
        user1.getGameProfile().increaseMaxTimeAlive(55);
        User user2 = new User("ali2", "", "1234", new SecurityQuestion("", ""));
        user2.getGameProfile().increaseKillCount(23);
        user2.getGameProfile().increaseScore(21);
        user2.getGameProfile().increaseMaxTimeAlive(56);
        User user3 = new User("ali3", "", "1234", new SecurityQuestion("", ""));
        user3.getGameProfile().increaseKillCount(20);
        user3.getGameProfile().increaseScore(1000);
        user3.getGameProfile().increaseMaxTimeAlive(223);
        User user4 = new User("ali4", "", "1234", new SecurityQuestion("", ""));
        user4.getGameProfile().increaseKillCount(1400);
        user4.getGameProfile().increaseScore(233);
        user4.getGameProfile().increaseMaxTimeAlive(30);
        User user5 = new User("ali5", "", "1234", new SecurityQuestion("", ""));
        user5.getGameProfile().increaseKillCount(123);
        user5.getGameProfile().increaseScore(323);
        user5.getGameProfile().increaseMaxTimeAlive(34);
        User user6 = new User("ali6", "", "1234", new SecurityQuestion("", ""));
        user6.getGameProfile().increaseKillCount(3);
        user6.getGameProfile().increaseScore(2);
        user6.getGameProfile().increaseMaxTimeAlive(55);

        App.addUser(user1);
        App.addUser(user2);
        App.addUser(user3);
        App.addUser(user4);
        App.addUser(user5);
        App.addUser(user6);

        App.setCurrentUser(user1);
        App.setCurrentMenu(Menus.MAIN_MENU);
        ////


        UserDataHandler.loadUsers();
        MusicManager.changeTrack(0);
        MusicManager.setVolume(0.5f);
        MusicManager.play();
        MenuManager.setScreen(Menus.SIGNUP_MENU);
    }
}
