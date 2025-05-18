package com.untildawn.Controllers.MenuControllers;

import java.util.ArrayList;
import java.util.Arrays;

public class Validation {
    public boolean isUsernameValid(String username) {
        return username.matches("^[a-zA-Z0-9-]+$");
    }
    public boolean isEmailValid(String email) {
        String emailRegex = "^(?!.*\\.\\.)[a-zA-Z0-9](?:[a-zA-Z0-9._-]*[a-zA-Z0-9])?@(?=[a-zA-Z0-9])[a-zA-Z0-9.-]*[a-zA-Z0-9]\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }
    public boolean isPasswordValid(String password) {
        return password.matches("^[a-zA-Z0-9?><,\"';:/\\\\|\\]\\[}{+=)(*&^%$#!]*$"
        );
    }
    public boolean isPasswordStrong(String password) {
        if (password.length() < 8) return false;
        ArrayList<Character> specialChars = new ArrayList<>(Arrays.asList('?', '>', '<', ',', '"', '\'', ';', ':', '/',
            '|', ']', '[', '}', '{', '+', '=', ')', '(', '*', '&', '^', '%', '$', '#', '!'));
        boolean containsSpecialChars = false;
        for (Character ch : specialChars) {
            if (password.indexOf(ch) != -1) {
                containsSpecialChars = true;
                break;
            }
        }
        if (!containsSpecialChars) return false;

        boolean hasLowerCase =  false;
        boolean hasUppercase = false;
        for (Character ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) hasUppercase = true;
            if (Character.isLowerCase(ch)) hasLowerCase = true;
        }
        if (!hasUppercase || !hasLowerCase) return false;

        return true;
    }
}
