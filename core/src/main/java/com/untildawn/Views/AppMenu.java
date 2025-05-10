package com.untildawn.Views;

public interface AppMenu {
    void showError(String error);
    void showMessage(String message);
    void showMessageAndExecute(String message, Runnable onClose);
}
