package com.untildawn.Controllers.MenuControllers;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.untildawn.Enums.MenuManager;
import com.untildawn.Enums.Menus;
import com.untildawn.Models.App;
import com.untildawn.Models.User;
import com.untildawn.Views.ProfileMenuView;

public class ProfileMenuController {
    private ProfileMenuView view;

    public void setView(ProfileMenuView view) {
        this.view = view;
    }

    public void handleProfileMenuButtons() {
        if (view != null) {
            this.view.getSubmitButton().addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    String newUsername = view.getUsernameField().getText();
                    String newPassword = view.getPasswordField().getText();

                    changeUsernamePassword(newUsername, newPassword);
                }
            });

            this.view.getDeleteUserButton().addListener(new ClickListener() {
               @Override
               public void clicked(InputEvent event, float x, float y) {
                   deleteCurrentUser();
               }
            });
        }
    }


    private void changeUsernamePassword(String username, String password) {

        User currentUser = App.getCurrentUser();
        String oldUsername = currentUser.getUsername();
        String oldPassword = currentUser.getPassword();

        Validation validator = new Validation();

        if (username.isEmpty()) {
            this.view.showError("Please enter your username.");
            return;
        }
        if (password.isEmpty()) {
            this.view.showError("Please enter your password.");
            return;
        }

        if (App.hasUser(username)
            && !username.equalsIgnoreCase(oldUsername)) {

            this.view.showError("Username exist.");
            return;
        }
        if (!validator.isUsernameValid(username)) {
            this.view.showError("Username format is not valid.");
            return;
        }
        if (!validator.isPasswordValid(password)) {
            this.view.showError("Password format is not valid.");
            return;
        }
        if (!validator.isPasswordStrong(password)) {
            this.view.showError("Password is not strong enough.");
            return;
        }

        currentUser.setUsername(username);
        currentUser.setPassword(password);
        if (username.equalsIgnoreCase(oldUsername) && password.equals(oldPassword)) {
            MenuManager.setScreen(Menus.MAIN_MENU);
        }
        else {
            this.view.showMessageAndExecute("Your profile is updated.", () -> {
                MenuManager.setScreen(Menus.MAIN_MENU);
            });
        }
    }

    private void deleteCurrentUser() {
        User currentuser = App.getCurrentUser();
        this.view.showConfirmation(
            "Are you sure?",
            (result) -> {
                if (result) {
                    App.removeUser(currentuser);
                    MenuManager.setScreen(Menus.LOGIN_MENU);
                }
            });
    }
}
