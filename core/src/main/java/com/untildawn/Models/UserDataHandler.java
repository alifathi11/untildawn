package com.untildawn.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

public class UserDataHandler {

    public static void loadUsers() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            List<User> users = objectMapper.readValue(
                new File("core/src/main/java/com/untildawn/Data/UserData.json"),
                new TypeReference<List<User>>() {}
            );

            App.setUsers(users);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveUsers() {
        List<User> users = App.getUsers();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("core/src/main/java/com/untildawn/Data/UserData.json"), users);
            System.out.println("Users written to " + "core/src/main/java/com/untildawn/Data/UserData.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
