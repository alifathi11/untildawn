package com.untildawn.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.untildawn.Enums.MenuManager;
import com.untildawn.Enums.Menus;
import com.untildawn.Main;
import com.untildawn.Models.App;
import com.untildawn.Models.User;
import com.untildawn.Models.UserDataHandler;
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
                    String password = view.getPasswordField().getText();

                    login(username, password);
                }
            });

            view.getForgetPasswordButton().addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    String username = view.getUsernameField().getText();

                    forgetPassword(username);
                }
            });
        }
    }


    private void login(String username, String password) {
        if (username.isEmpty()) {
            this.view.showError("Please enter your username.");
            return;
        }
        if (password.isEmpty()) {
            this.view.showError("Please enter your password.");
            return;
        }
        if (!App.hasUser(username)) {
            this.view.showError("User doesn't exist.");
            return;
        }

        User targetUser = App.getUser(username);
        if (targetUser == null) {
            this.view.showError("Unknown error.");
            return;
        }

        String correctPassword = targetUser.getPassword();
        if (!password.equals(correctPassword)) {
            this.view.showError("Password is not correct.");
            return;
        }

        App.setCurrentUser(targetUser);
        this.view.showMessageAndExecute("You have logged in successfully.", () -> {
            MenuManager.setScreen(Menus.MAIN_MENU);
        });
    }

    public void forgetPassword(String username) {
        if (username.isEmpty()) {
            this.view.showError("Please enter your username.");
            return;
        }
        if (!App.hasUser(username)) {
            this.view.showError("User doesn't exist.");
            return;
        }

        User targetUser = App.getUser(username);
        if (targetUser == null) {
            this.view.showError("Unknown Error.");
            return;
        }

        String password = targetUser.getPassword();
        String email = targetUser.getEmail();
        if (EmailSender.sendEmail(email, String.format("Your password: %s", password))) {
            this.view.showMessage("Your password has been sent to your email.");
        } else {
            this.view.showError("Error occurred. Please try again later.");
        }
    }


}
