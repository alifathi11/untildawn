package com.untildawn.Controllers;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.untildawn.Enums.MenuManager;
import com.untildawn.Enums.Menus;
import com.untildawn.Models.App;
import com.untildawn.Models.User;
import com.untildawn.Views.ScoreBoardMenuView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ScoreBoardMenuController {
    private ScoreBoardMenuView view;
    private int sortMethodIndex;
    private String[] options = {
        "SORT BY SCORE",
        "SORT BY KILL",
        "SORT BY LONGEST TIME ALIVE",
        "SORT BY USERNAME"
    };

    public ScoreBoardMenuController() { sortMethodIndex = 0; }

    public void setView(ScoreBoardMenuView view) {
        this.view = view;
    }

    public void handleScoreBoardMenuButtons() {
        if (view != null) {
            view.getBackButton().addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    MenuManager.setScreen(Menus.MAIN_MENU);
                }
            });

            view.getSortTypeButton().addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {

                    String sortMethod;

                    sortMethodIndex = (sortMethodIndex + 1) % 4;
                    view.getSortTypeButton().setText(options[sortMethodIndex]);

                    switch (sortMethodIndex) {
                        case 0:
                            view.setUsersList(getUsersBySelectedOrder("score"));
                            break;
                        case 1:
                            view.setUsersList(getUsersBySelectedOrder("kill"));
                            break;
                        case 2:
                            view.setUsersList(getUsersBySelectedOrder("maxTimeAlive"));
                            break;
                        case 3:
                            view.setUsersList(getUsersBySelectedOrder("username"));
                            break;
                    }

                    view.update();

                }
            });
        }
    }

    public List<User> getUsersBySelectedOrder(String order) {
        List<User> sortedUsers;
        List<User> users = App.getUsers();

        switch (order) {
            case "score":
                sortedUsers = users.stream()
                    .sorted(Comparator.comparingInt(user -> ((User) user).getGameProfile().getScore()).reversed())
                    .collect(Collectors.toCollection(ArrayList::new));
                break;
            case "username":
                sortedUsers = users.stream()
                    .sorted(Comparator.comparing(user -> ((User) user).getUsername()))
                    .collect(Collectors.toCollection(ArrayList::new));
                break;
            case "kill":
                sortedUsers = users.stream()
                    .sorted(Comparator.comparingInt(user -> ((User) user).getGameProfile().getKillCount()).reversed())
                    .collect(Collectors.toCollection(ArrayList::new));
                break;
            case "maxTimeAlive":
                sortedUsers = users.stream()
                    .sorted(Comparator.comparingInt(user -> ((User) user).getGameProfile().getMaxTimeAlive()).reversed())
                    .collect(Collectors.toCollection(ArrayList::new));
                break;
            default:
                this.view.showError("Unknown error. ScoreBoardMenuController -> line 51");
                return null;
        }

        return sortedUsers;
    }
}
