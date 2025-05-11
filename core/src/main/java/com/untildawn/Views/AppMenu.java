package com.untildawn.Views;

import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.untildawn.Models.GameAssetManager;

import java.util.function.Consumer;

public interface AppMenu {
    void showError(String error);
    void showMessage(String message);
    void showMessageAndExecute(String message, Runnable onClose);
//    void showConfirmation(String message, final Consumer<Boolean> resultCallback);
//    void resetUI();
}
