package com.untildawn.Enums;

import com.badlogic.gdx.Screen;
import com.untildawn.Controllers.*;
import com.untildawn.Models.GameAssetManager;
import com.untildawn.Views.*;
import com.untildawn.Main;

import java.util.EnumMap;
import java.util.Map;

public class MenuManager {
    private static final Map<Menus, Screen> screens = new EnumMap<>(Menus.class);

    static {
        screens.put(Menus.LOGIN_MENU, new LoginMenuView(new LoginMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
        screens.put(Menus.SIGNUP_MENU, new SignupMenuView(new SignupMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
        screens.put(Menus.MAIN_MENU, new MainMenuView(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
        screens.put(Menus.PREGAME_MENU, new PreGameMenuView(new PreGameMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
        screens.put(Menus.PROFILE_MENU, new ProfileMenuView(new ProfileMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
        screens.put(Menus.GRAPHICS_AUDIO_SETTING_MENU, new GraphicsAudioSettingView(new GraphicsAudioSettingController(), GameAssetManager.getGameAssetManager().getSkin()));
        screens.put(Menus.KEYBOARD_SETTING_MENU, new KeyboardPreferencesSettingView(new KeyboardPreferencesSettingController(), GameAssetManager.getGameAssetManager().getSkin()));
        screens.put(Menus.SCOREBOARD_MENU, new ScoreBoardMenuView(new ScoreBoardMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
        screens.put(Menus.HINT_MENU, new HintMenuView(new HintMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
        screens.put(Menus.NEW_GAME_MENU, new NewGameMenuView(new NewGameMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
        screens.put(Menus.RESUME_LAST_GAME_MENU, new ResumeLastGameMenuView(new ResumeLastGameMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
        screens.put(Menus.LOGOUT_MENU, new LogoutMenuView(new LogoutMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
    }

    public static Screen getScreen(Menus menu) {
        return screens.get(menu);
    }

    public static void setScreen(Menus menu) {
        Main.getInstance().setScreen(getScreen(menu));
    }
}
