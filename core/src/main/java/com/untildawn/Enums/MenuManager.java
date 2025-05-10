package com.untildawn.Enums;

import com.badlogic.gdx.Screen;
import com.untildawn.Controllers.LoginMenuController;
import com.untildawn.Controllers.SignupMenuController;
import com.untildawn.Models.GameAssetManager;
import com.untildawn.Views.LoginMenuView;
import com.untildawn.Views.SignupMenuView;
import com.untildawn.Main;

import java.util.EnumMap;
import java.util.Map;

public class MenuManager {
    private static final Map<Menus, Screen> screens = new EnumMap<>(Menus.class);

    static {
        screens.put(Menus.LOGIN_MENU, new LoginMenuView(new LoginMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
        screens.put(Menus.SIGNUP_MENU, new SignupMenuView(new SignupMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
    }

    public static Screen getScreen(Menus menu) {
        return screens.get(menu);
    }

    public static void setScreen(Menus menu) {
        Main.getInstance().setScreen(getScreen(menu));
    }
}
