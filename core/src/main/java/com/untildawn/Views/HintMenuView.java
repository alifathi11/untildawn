package com.untildawn.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.untildawn.Controllers.MenuControllers.HintMenuController;
import com.untildawn.Models.GameAssetManager;

import java.util.function.Consumer;

public class HintMenuView implements Screen, AppMenu {
    private Stage stage;
    private Texture backgroundTexture;
    private Image backgroundImage;


    private final Label hintMenuLabel;

    private final TextButton heroStatsButton;
    private final TextButton inputPreferencesButton;
    private final TextButton cheatCodesButton;
    private final TextButton abilityStatsButton;

    private final TextButton backButton;

    private Table table;

    private HintMenuController controller;

    private CheatCodesScreen cheatCodesScreen;
    private HeroStatsScreen heroStatsScreen;
    private AbilityStatsScreen abilityStatsScreen;
    private InputPreferencesScreen inputPreferencesScreen;

    public HintMenuView(HintMenuController controller, Skin skin) {
        this.controller = controller;

        this.hintMenuLabel = new Label("HINT MENU", skin);
        this.heroStatsButton = new TextButton("HERO STATS", skin);
        this.inputPreferencesButton = new TextButton("INPUT PREFERENCES", skin);
        this.cheatCodesButton = new TextButton("CHEAT CODES", skin);
        this.abilityStatsButton = new TextButton("ABILITY STATS", skin);
        this.backButton = new TextButton("BACK", skin);

        this.table = new Table();


        this.backgroundTexture = new Texture("images/background-image-4.png");
        backgroundImage = new Image(backgroundTexture);

        controller.setView(this);
        controller.handleHintMenuButtons();
    }

    @Override
    public void show() {
        if (stage != null) {
            stage.clear();
            table.clear();
        }
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.center();
        table.add(hintMenuLabel);
        table.row().pad(20, 5, 10, 5);
        table.add(heroStatsButton).width(600).height(100);
        table.row().pad(10, 5, 10, 5);
        table.add(inputPreferencesButton).width(600).height(100);
        table.row().pad(10, 5, 10, 5);
        table.add(cheatCodesButton).width(600).height(100);
        table.row().pad(10, 5, 10, 5);
        table.add(abilityStatsButton).width(600).height(100);
        table.row().pad(40, 5, 10, 5);
        table.add(backButton).width(200).height(60);


        backgroundImage.setSize(stage.getWidth(), stage.getHeight());
        stage.addActor(backgroundImage);
        stage.addActor(table);

        table.setVisible(true);
    }

    public void showCheatScreen() {
        stage.clear();

        cheatCodesScreen = new CheatCodesScreen(() -> {
            cheatCodesScreen.hide();
            this.show();
            Gdx.input.setInputProcessor(stage);
            cheatCodesScreen = null;
        });

        cheatCodesScreen.show();
    }

    public void showInputPreferencesScreen() {
        stage.clear();

        inputPreferencesScreen = new InputPreferencesScreen(() -> {
            inputPreferencesScreen.hide();
            this.show();
            Gdx.input.setInputProcessor(stage);
            inputPreferencesScreen = null;
        });

        inputPreferencesScreen.show();
    }

    public void showHeroStatsScreen() {
        stage.clear();

        heroStatsScreen = new HeroStatsScreen(() -> {
            heroStatsScreen.hide();
            this.show();
            Gdx.input.setInputProcessor(stage);
            cheatCodesScreen = null;
        });

        heroStatsScreen.show();
    }

    public void showAbilityStatsScreen() {
        stage.clear();

        abilityStatsScreen = new AbilityStatsScreen(() -> {
            abilityStatsScreen.hide();
            this.show();
            Gdx.input.setInputProcessor(stage);
            abilityStatsScreen = null;
        });

        abilityStatsScreen.show();
    }


    @Override
    public void render(float delta) {
        if (cheatCodesScreen != null && cheatCodesScreen.isVisible()) {
            cheatCodesScreen.render(delta);
        } else if (heroStatsScreen != null && heroStatsScreen.isVisible()) {
            heroStatsScreen.render(delta);
        } else if (abilityStatsScreen != null && abilityStatsScreen.isVisible()) {
            abilityStatsScreen.render(delta);
        } else if (inputPreferencesScreen != null && inputPreferencesScreen.isVisible()) {
            inputPreferencesScreen.render(delta);
        } else {
            stage.act(delta);
            stage.draw();
        }
    }

    @Override
    public void resize(int width, int height) {
        if (cheatCodesScreen != null && cheatCodesScreen.isVisible()) {
            cheatCodesScreen.resize(width, height);
        } else if (stage != null) {
            stage.getViewport().update(width, height, true);
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    public void dispose() {
        stage.dispose();
    }

    private void resetUI() {
        stage.clear();
        table.clear();

    }

    public void showError(String error) {
        Skin skin = GameAssetManager.getGameAssetManager().getSkin();
        Dialog errorDialog = new Dialog("Error", skin) {
            @Override
            protected void result(Object object) {
            }
        };

        errorDialog.text(error);

        TextButton closeButton = new TextButton("Close", skin);
        closeButton.getStyle().fontColor = Color.RED;
        errorDialog.button(closeButton);

        errorDialog.show(stage);

        errorDialog.setSize(400, 200);
        errorDialog.setPosition(
            (stage.getWidth() - errorDialog.getWidth()) / 2,
            (stage.getHeight() - errorDialog.getHeight()) / 2
        );
    }
    public void showMessage(String message) {
        Skin skin = GameAssetManager.getGameAssetManager().getSkin();
        Dialog messageDialog = new Dialog("Alert", skin) {
            @Override
            protected void result(Object object) {
            }
        };

        messageDialog.text(message);

        TextButton closeButton = new TextButton("Close", skin);
        closeButton.getStyle().fontColor = Color.RED;
        messageDialog.button(closeButton);

        messageDialog.show(stage);

        messageDialog.setSize(400, 200);
        messageDialog.setPosition(
            (stage.getWidth() - messageDialog.getWidth()) / 2,
            (stage.getHeight() - messageDialog.getHeight()) / 2
        );
    }

    public void showMessageAndExecute(String message, Runnable onClose) {
        Skin skin = GameAssetManager.getGameAssetManager().getSkin();
        Dialog messageDialog = new Dialog("Alert", skin) {
            @Override
            protected void result(Object object) {
                if (onClose != null) {
                    onClose.run();
                }
            }
        };

        messageDialog.text(message);
        TextButton closeButton = new TextButton("Close", skin);
        closeButton.getStyle().fontColor = Color.RED;

        messageDialog.button(closeButton, true);
        messageDialog.show(stage);

        messageDialog.setSize(400, 200);
        messageDialog.setPosition(
            (stage.getWidth() - messageDialog.getWidth()) / 2,
            (stage.getHeight() - messageDialog.getHeight()) / 2
        );
    }

    public void showConfirmation(String message, final Consumer<Boolean> resultCallback) {
        Skin skin = GameAssetManager.getGameAssetManager().getSkin();
        Dialog dialog = new Dialog("Confirm", skin) {
            @Override
            protected void result(Object object) {
                if (object instanceof Boolean) {
                    resultCallback.accept((Boolean) object);
                }
            }
        };

        dialog.text(message);

        dialog.button("Yes", true);
        dialog.button("No", false);

        dialog.show(stage);

        dialog.setSize(400, 200);
        dialog.setPosition(
            (stage.getWidth() - dialog.getWidth()) / 2,
            (stage.getHeight() - dialog.getHeight()) / 2
        );
    }

    public TextButton getCheatCodesButton() {
        return cheatCodesButton;
    }

    public HintMenuController getController() {
        return controller;
    }

    public TextButton getAbilityStatsButton() {
        return abilityStatsButton;
    }

    public TextButton getHeroStatsButton() {
        return heroStatsButton;
    }

    public TextButton getInputPreferencesButton() {
        return inputPreferencesButton;
    }

    public Stage getStage() {
        return stage;
    }

    public TextButton getBackButton() {
        return backButton;
    }
}
