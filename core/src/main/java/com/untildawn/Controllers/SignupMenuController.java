package com.untildawn.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.untildawn.Enums.MenuManager;
import com.untildawn.Enums.Menus;
import com.untildawn.Models.App;
import com.untildawn.Models.User;
import com.untildawn.Views.LoginMenuView;
import com.untildawn.Views.SignupMenuView;

public class SignupMenuController {
    private SignupMenuView view;
    public void setView(SignupMenuView view) {
        this.view = view;
    }

    public void handleSignupMenuButtons() {
        if (view != null) {
            view.getSignupButton().addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    String username = view.getUsernameField().getText();
                    String email = view.getEmailField().getText();
                    String password = view.getPasswordField().getText();

                    register(username, email, password);
                }
            });

        }
    }


    private void register(String username, String email, String password) {
        if (username.isEmpty()) {
            this.view.showError("Please enter your username");
            return;
        }
        if (email.isEmpty()) {
            this.view.showError("Please enter your email.");
            return;
        }
        if (password.isEmpty()) {
            this.view.showError("Please enter your password.");
            return;
        }
        if (App.hasUser(username)) {
            this.view.showError("Username exists.");
            return;
        }

        Validation validator = new Validation();

        if (!validator.isUserNameValid(username)) {
            this.view.showError("Username format is not valid.");
            return;
        }
        if (!validator.isEmailValid(email)) {
            this.view.showError("Email Address format is not valid.");
            return;
        }
        if (!validator.isPasswordValid(password)) {
            this.view.showError("Password format is not valid.");
            return;
        }

        User newUser = new User(username, email, password);
        App.addUser(newUser);
        this.view.showMessage("User has been created successfully."); 
        MenuManager.setScreen(Menus.LOGIN_MENU);
    }



}
