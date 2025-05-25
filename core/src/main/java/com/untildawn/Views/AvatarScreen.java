package com.untildawn.Views;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.*;
import com.untildawn.Controllers.MenuControllers.AvatarController;
import com.untildawn.Enums.Menus;
import com.untildawn.Models.App;
import com.untildawn.Models.FileChooser;
import com.untildawn.Models.GameAssetManager;
import com.untildawn.Models.MenuManager;

public class AvatarScreen implements Screen {
    private Stage stage;
    private Texture backgroundTexture;
    private Image backgroundImage;

    private Skin skin;
    private Table rootTable;
    private AvatarController controller;

    private TextButton backButton;

    public AvatarScreen() {
        controller = new AvatarController();
        skin = GameAssetManager.getGameAssetManager().getSkin();

        this.backgroundTexture = new Texture("images/background-image-2.png");
        backgroundImage = new Image(backgroundTexture);

        this.backButton = new TextButton("BACK", skin);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        controller.loadIcons(9);

        rootTable = new Table();
        rootTable.setFillParent(true);
        backgroundImage.setSize(stage.getWidth(), stage.getHeight());
        stage.addActor(backgroundImage);
        stage.addActor(rootTable);

        Table iconGrid = new Table();
        for (int i = 0; i < 9; i++) {
            iconGrid.add(controller.getIconImages().get(i)).size(100).pad(50);
            if ((i + 1) % 3 == 0) iconGrid.row();
        }

        TextButton fileButton = new TextButton("Choose from System", skin);
        fileButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
                    chooseFile();
                }
            }
        });

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                MenuManager.setScreen(Menus.PROFILE_MENU);
            }
        });

        rootTable.center();
        rootTable.add(iconGrid).colspan(2).center();
        rootTable.row().padTop(60);
        rootTable.add(fileButton).width(400).height(60).pad(60);
        rootTable.add(backButton).width(400).height(60).pad(60);
    }

    private void chooseFile() {
        FileChooser chooser = new FileChooser();
        chooser.setListener(file -> {
            Gdx.app.postRunnable(() -> {
                try {
                    String name = file.name().toLowerCase();
                    if (!(name.endsWith(".png") || name.endsWith(".jpg") || name.endsWith(".jpeg"))) {
                        System.out.println("Invalid image format selected.");
                        return;
                    }

                    controller.setCustomImage(file.path());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        });
        chooser.chooseFile();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.12f, 0.12f, 0.12f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override public void resize(int width, int height) { stage.getViewport().update(width, height, true); }
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
    }
}
