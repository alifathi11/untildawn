package com.untildawn.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.untildawn.Main;
import com.untildawn.Views.LoginMenuView;

public class LoginMenuController {
    private LoginMenuView view;
    public void setView(LoginMenuView view) {
        this.view = view;
    }

    public void handleLoginMenuButtons() {
        if (view != null) {
            view.getLoginButton().addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    String username = view.getUsernameField().getText();
                    String email = view.getEmailField().getText();

                    if (!username.isEmpty() && !email.isEmpty()) {
                        Gdx.app.exit();
                    }
                }
            });

        }
    }
}
