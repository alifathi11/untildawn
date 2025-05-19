package com.untildawn.Models;

import com.badlogic.gdx.Input;
import com.untildawn.Enums.Actions;

import java.util.HashMap;
import java.util.Map;

public class InputPreferences {

    private final Map<Actions, InputBinding> inputBindings;

    public InputPreferences() {
        inputBindings = new HashMap<>();

        inputBindings.put(Actions.MOVE_UP,    new InputBinding(InputBinding.InputType.KEYBOARD, Input.Keys.W));
        inputBindings.put(Actions.MOVE_DOWN,  new InputBinding(InputBinding.InputType.KEYBOARD, Input.Keys.S));
        inputBindings.put(Actions.MOVE_LEFT,  new InputBinding(InputBinding.InputType.KEYBOARD, Input.Keys.A));
        inputBindings.put(Actions.MOVE_RIGHT, new InputBinding(InputBinding.InputType.KEYBOARD, Input.Keys.D));
        inputBindings.put(Actions.RUN, new InputBinding(InputBinding.InputType.KEYBOARD, Input.Keys.SHIFT_LEFT));

        inputBindings.put(Actions.SHOOT,   new InputBinding(InputBinding.InputType.MOUSE, Input.Buttons.LEFT));
        inputBindings.put(Actions.RELOAD,  new InputBinding(InputBinding.InputType.KEYBOARD, Input.Keys.R));
        inputBindings.put(Actions.DANCE,   new InputBinding(InputBinding.InputType.KEYBOARD, Input.Keys.E));
    }

    public void setInputBinding(Actions action, InputBinding.InputType type, int code) {
        inputBindings.put(action, new InputBinding(type, code));
    }

    public InputBinding getInputBinding(Actions action) {
        return inputBindings.get(action);
    }
}
