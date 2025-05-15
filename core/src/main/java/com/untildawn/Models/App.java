package com.untildawn.Models;

import com.untildawn.Enums.Menus;
import sun.rmi.server.UnicastServerRef;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class App {
    private static User currentUser = null;
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
            if (user.getUsername().equalsIgnoreCase(username)) {
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

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        App.currentUser = currentUser;
    }

    public static User getUser(String username) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        return null;
    }
    public static void removeUser(User user) {
        App.users.remove(user);
    }
}
