package com.untildawn.Models;

import com.untildawn.Enums.Menus;

import java.util.ArrayList;
import java.util.List;

public class App {
    private static Menus currentMenu = Menus.SIGNUP_MENU;
    private static List<User> users = new ArrayList<>();

    public static void setUsers(List<User> users) {
        App.users = new ArrayList<>(users);
    }

    public static List<User> getUsers() {
        return users;
    }
    public static boolean hasUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
    public static void addUser(User newUser) {
        App.users.add(newUser);
    }

    public static Menus getCurrentMenu() {
        return currentMenu;
    }

    public static void setCurrentMenu(Menus currentMenu) {
        App.currentMenu = currentMenu;
    }
}
