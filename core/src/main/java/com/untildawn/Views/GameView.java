package com.untildawn.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.untildawn.Controllers.GameController;
import com.untildawn.Controllers.WeaponController;
import com.untildawn.Main;
import com.untildawn.Models.Player;
import com.untildawn.Models.Weapon;

public class GameView implements Screen, InputProcessor {
    private Stage stage;
    private Viewport viewport;
    private OrthographicCamera camera;
    OrthographicCamera hudCamera;

    private GameController controller;
    private BitmapFont font;
    private GlyphLayout layout;

    private Player player;

    public GameView(GameController controller, Skin skin) {
        camera = new OrthographicCamera();
        hudCamera = new OrthographicCamera();

        this.controller = controller;
        controller.setView(this);

        this.player = controller.getPlayerController().getPlayer();
    }

    @Override
    public void show() {
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

        Main.getBatch().begin();
        controller.updateGame();
        drawCornerTexts();
        Main.getBatch().end();

        controller.getWorldController().renderShapes(camera);

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    private void drawCornerTexts() {
        int screenWidth = Gdx.graphics.getWidth();
        int screenHeight = Gdx.graphics.getHeight();

        Weapon weapon = player.getCurrentWeapon();
        // TODO: temporary
        String HP = Float.toString(player.getHP());
        String ammo = Integer.toString(weapon.getAmmo());
        String XP = Integer.toString(player.getXp());
        String totalAmmo = Integer.toString(weapon.getTotalAmmo());

        Main.getBatch().setProjectionMatrix(hudCamera.combined);
        hudCamera.update();

        font.draw(Main.getBatch(), HP + " " + XP, 10, screenHeight - 10);
        font.draw(Main.getBatch(), ammo + " " + totalAmmo, 10, 20);
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
}
