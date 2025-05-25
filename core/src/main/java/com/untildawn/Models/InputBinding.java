package com.untildawn.Models;

public class InputBinding {

    public enum InputType {
        KEYBOARD,
        MOUSE
    }

    private InputType type;
    private int code;

    public InputBinding() {}

    public InputBinding(InputType type, int code) {
        this.type = type;
        this.code = code;
    }

    public InputType getType() {
        return type;
    }

    public int getCode() {
        return code;
    }

    public void set(InputType type, int code) {
        this.type = type;
        this.code = code;
    }
}

