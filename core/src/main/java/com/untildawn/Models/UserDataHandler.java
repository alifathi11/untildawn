package com.untildawn.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;

import java.util.ArrayList;
import java.util.List;

public class UserDataHandler {
    public static void loadUsers() {
        List<User> users = new ArrayList<>();
        FileHandle file = Gdx.files.internal("com/untildawn/Data/UserData.json");

        if (!file.exists()) {
            System.err.println("users.json not found!");
            return;
        }

        Json json = new Json();
        JsonReader reader = new JsonReader();
        JsonValue root = reader.parse(file);

        for (JsonValue userJson : root) {
            User user = json.readValue(User.class, userJson);
            users.add(user);
        }

        App.setUsers(users);
    }

    public static void saveUsers() {
        List<User> users = App.getUsers();
        Json json = new Json();
        json.setOutputType(JsonWriter.OutputType.json);

        String usersJson = json.toJson(users);
        FileHandle file = Gdx.files.local("com/untildawn/Data/UserData.json");

        file.writeString(usersJson, false);
    }
}
