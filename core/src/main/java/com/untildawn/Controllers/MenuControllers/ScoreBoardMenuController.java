package com.untildawn.Controllers.MenuControllers;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.untildawn.Models.MenuManager;
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
    private final String[] options;

    public ScoreBoardMenuController() {
        sortMethodIndex = 0;
        options = new String[] {
            "SORT BY SCORE",
            "SORT BY KILL",
            "SORT BY LONGEST TIME ALIVE",
            "SORT BY USERNAME"
        };
    }

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

                    sortMethodIndex = (sortMethodIndex + 1) % 4;
                    view.getSortTypeButton().setText(options[sortMethodIndex]);

                    view.setUsersList(getUsersBySelectedOrder());

                    view.update();

                }
            });
        }
    }

    public List<User> getUsersBySelectedOrder() {
        List<User> sortedUsers;
        List<User> users = App.getUsers();

        switch (sortMethodIndex) {
            case 0:
                sortedUsers = users.stream()
                    .sorted(Comparator.comparingInt(user -> ((User) user).getGameProfile().getScore()).reversed())
                    .collect(Collectors.toCollection(ArrayList::new));
                break;
            case 1:
                sortedUsers = users.stream()
                    .sorted(Comparator.comparingInt(user -> ((User) user).getGameProfile().getKillCount()).reversed())
                    .collect(Collectors.toCollection(ArrayList::new));
                break;
            case 2:
                sortedUsers = users.stream()
                    .sorted(Comparator.comparingDouble(user -> ((User) user).getGameProfile().getMaxTimeAlive()).reversed())
                    .collect(Collectors.toCollection(ArrayList::new));
                break;
            case 3:
                sortedUsers = users.stream()
                    .sorted(Comparator.comparing(user -> ((User) user).getUsername()))
                    .collect(Collectors.toCollection(ArrayList::new));
                break;
            default:
                this.view.showError("Unknown error. ScoreBoardMenuController -> line 51");
                return null;
        }

        return sortedUsers;
    }
}
