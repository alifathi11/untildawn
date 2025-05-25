package com.untildawn.Models;

import com.badlogic.gdx.Input;
import com.untildawn.Enums.Actions;

import java.util.HashMap;
import java.util.Map;

public class InputPreferences {

    private Map<Actions, InputBinding> inputBindings;

    public InputPreferences() {
        inputBindings = new HashMap<>();

        inputBindings.put(Actions.MOVE_UP,    new InputBinding(InputBinding.InputType.KEYBOARD, Input.Keys.W));
        inputBindings.put(Actions.MOVE_DOWN,  new InputBinding(InputBinding.InputType.KEYBOARD, Input.Keys.S));
        inputBindings.put(Actions.MOVE_LEFT,  new InputBinding(InputBinding.InputType.KEYBOARD, Input.Keys.A));
        inputBindings.put(Actions.MOVE_RIGHT, new InputBinding(InputBinding.InputType.KEYBOARD, Input.Keys.D));
        inputBindings.put(Actions.RUN, new InputBinding(InputBinding.InputType.KEYBOARD, Input.Keys.SHIFT_LEFT));

        inputBindings.put(Actions.SHOOT,   new InputBinding(InputBinding.InputType.MOUSE, Input.Buttons.LEFT));
        inputBindings.put(Actions.AUTO_SHOOTING, new InputBinding(InputBinding.InputType.KEYBOARD, Input.Keys.SPACE));
        inputBindings.put(Actions.RELOAD,  new InputBinding(InputBinding.InputType.KEYBOARD, Input.Keys.R));
        inputBindings.put(Actions.DANCE,   new InputBinding(InputBinding.InputType.KEYBOARD, Input.Keys.E));

        inputBindings.put(Actions.WEAPON_1, new InputBinding(InputBinding.InputType.KEYBOARD, Input.Keys.NUM_1));
        inputBindings.put(Actions.WEAPON_2, new InputBinding(InputBinding.InputType.KEYBOARD, Input.Keys.NUM_2));
        inputBindings.put(Actions.WEAPON_3, new InputBinding(InputBinding.InputType.KEYBOARD, Input.Keys.NUM_3));

        inputBindings.put(Actions.PAUSE, new InputBinding(InputBinding.InputType.KEYBOARD, Input.Keys.ESCAPE));
        inputBindings.put(Actions.OPEN_CHEAT_CONSOLE, new InputBinding(InputBinding.InputType.KEYBOARD, Input.Keys.J));

    }

    public void setInputBinding(Actions action, InputBinding.InputType type, int code) {
        inputBindings.put(action, new InputBinding(type, code));
    }

    public InputBinding getInputBinding(Actions action) {
        return inputBindings.get(action);
    }

    public boolean isInputInUse(InputBinding.InputType type, int code, Actions excludeAction) {
        for (Map.Entry<Actions, InputBinding> entry : inputBindings.entrySet()) {
            if (entry.getKey() == excludeAction) continue;
            InputBinding binding = entry.getValue();
            if (binding.getType() == type && binding.getCode() == code) {
                return true;
            }
        }
        return false;
    }

    public Map<Actions, InputBinding> getInputBindings() {
        return inputBindings;
    }

    public void setInputBindings(Map<Actions, InputBinding> inputBindings) {
        this.inputBindings = inputBindings;
    }
}
