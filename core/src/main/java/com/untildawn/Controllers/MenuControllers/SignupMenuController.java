package com.untildawn.Controllers.MenuControllers;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.untildawn.Enums.MenuManager;
import com.untildawn.Enums.Menus;
import com.untildawn.Models.App;
import com.untildawn.Models.SecurityQuestion;
import com.untildawn.Models.User;
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
                    String question = view.getSecurityQuestionBox().getSelected();
                    String answer = view.getUserAnswer().getText();

                    register(username, email, password, question, answer);
                }
            });

            view.getPlayAsGuestButton().addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                   MenuManager.setScreen(Menus.MAIN_MENU);
                   App.setPlayingAsGuest(true);
                }
            });

            view.getLoginButton().addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    MenuManager.setScreen(Menus.LOGIN_MENU);
                }
            });

        }
    }


    private void register(String username,
                          String email,
                          String password,
                          String question,
                          String answer) {
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
        if (answer.isEmpty()) {
            this.view.showError("Please answer one security question.");
            return;
        }

        if (App.hasUser(username)) {
            this.view.showError("Username exists.");
            return;
        }

        Validation validator = new Validation();

        if (!validator.isUsernameValid(username)) {
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
        if (!validator.isPasswordStrong(password)) {
            this.view.showError("Password is not strong enough.");
            return;
        }

        SecurityQuestion securityQuestion = new SecurityQuestion(question, answer);
        User newUser = new User(username, email, password, securityQuestion);
        App.addUser(newUser);
        this.view.showMessageAndExecute("User has created successfully", () -> {
            MenuManager.setScreen(Menus.LOGIN_MENU);
        });
    }



}
