package com.untildawn.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.untildawn.Controllers.CheatConsoleController;
import com.untildawn.Controllers.GameController;
import com.untildawn.Controllers.PauseMenuController;
import com.untildawn.Enums.Monsters;
import com.untildawn.Main;
import com.untildawn.Models.*;

public class GameView implements Screen, InputProcessor {
    private Stage stage;
    private Viewport viewport;
    private OrthographicCamera camera;
    OrthographicCamera hudCamera;

    private GameController controller;
    private PauseMenuView pauseMenuView;
    private PauseMenuController pauseMenuController;
    private BitmapFont font;
    private GlyphLayout layout;

    private CheatConsoleView cheatConsoleView;
    private CheatConsoleController cheatConsoleController;

    private Game game;
    private Player player;

    private ShaderProgram grayscaleShader;

    public GameView(GameController controller, Skin skin) {
        camera = new OrthographicCamera();
        hudCamera = new OrthographicCamera();

        this.controller = controller;
        controller.setView(this);
        this.pauseMenuView = controller.getPauseMenuView();
        this.pauseMenuController = controller.getPauseMenuController();

        cheatConsoleView = new CheatConsoleView();
        cheatConsoleController = new CheatConsoleController(cheatConsoleView);

        this.game = App.getCurrentGame();
        this.player = game.getPlayer();
    }

    @Override
    public void show() {
        create();
        SFXManager.load();

        viewport = new ScreenViewport(camera);
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

        hudCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(this);

        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(1.5f);
        layout = new GlyphLayout();
    }

    @Override
    public void render(float delta) {

        camera.position.set(player.getPosition().getX(), player.getPosition().getY(), 0);
        camera.update();
        Main.getBatch().setProjectionMatrix(camera.combined);

        ScreenUtils.clear(Color.BLACK, true);

        if (game.getGamePreferences().isGrayScaleOn()) {
            Main.getBatch().setShader(grayscaleShader);
        } else {
            Main.getBatch().setShader(null);
        }
        if (!cheatConsoleView.isVisible()) {
            Main.getBatch().begin();

            if (!pauseMenuController.isPaused()) {
                Gdx.input.setInputProcessor(this);
                controller.updateGame();
            } else {
                Gdx.input.setInputProcessor(pauseMenuView.getStage());
                pauseMenuView.show();
            }

            pauseMenuView.render(Main.getBatch());
            Main.getBatch().end();
        } else {
            Gdx.input.setInputProcessor(cheatConsoleView.getStage());
        }

        cheatConsoleController.updateAndRender();

        controller.getWorldController().renderShapes(camera);

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }




    public void create() {
        ShaderProgram.pedantic = false;

        grayscaleShader = new ShaderProgram(
            Gdx.files.internal("shaders/grayscale.vert"),
            Gdx.files.internal("shaders/grayscale.frag")
        );

        if (!grayscaleShader.isCompiled()) {
            Gdx.app.error("SHADER", "Error compiling shader: " + grayscaleShader.getLog());
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
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

    @Override
    public void dispose() {

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        controller.getWeaponController().handleWeaponRotation(screenX, screenY);
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public CheatConsoleController getCheatConsoleController() {
        return cheatConsoleController;
    }
}
