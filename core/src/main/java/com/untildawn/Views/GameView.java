package com.untildawn.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sun.mail.util.logging.MailHandler;
import com.untildawn.Controllers.CheatConsoleController;
import com.untildawn.Controllers.GameController;
import com.untildawn.Controllers.PauseMenuController;
import com.untildawn.Main;
import com.untildawn.Models.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.awt.*;

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

    private CheatCodesScreen cheatCodesScreen;
    private GainedAbilitiesScreen gainedAbilitiesScreen;

    private WinScreen winScreen;
    private DeadScreen deadScreen;

    private Texture activeHeartTexture;
    private Texture inactiveHeartTexture;
    private float heartAnimTime = 0f;

    private Texture coinTexture;
    private Texture skeletonTexture;

    private BitmapFont timerFont;
    private Label timeLabel;

    private Texture fullBulletTexture;
    private Texture emptyBulletTexture;

    private Game game;
    private Player player;

    private float xpProgress;
    private Texture xpBarBackground;
    private Texture xpBarFill;

    private ShaderProgram grayscaleShader;


    public GameView(GameController controller, Skin skin) {
        camera = new OrthographicCamera();
        hudCamera = new OrthographicCamera();

        this.controller = controller;
        controller.setView(this);
        this.pauseMenuView = controller.getPauseMenuView();
        this.pauseMenuController = controller.getPauseMenuController();

        xpBarBackground = new Texture("levelUp/xp_bar_bg.png");
        xpBarFill = new Texture("levelUp/xp_bar_fill.png");

        cheatConsoleView = new CheatConsoleView();
        cheatConsoleController = new CheatConsoleController(cheatConsoleView);

        this.game = App.getCurrentGame();
        this.player = game.getPlayer();

        this.deadScreen = new DeadScreen(game);
        this.winScreen = new WinScreen(game);
    }

    @Override
    public void show() {
        create();
        SFXManager.load();

        viewport = new ScreenViewport(camera);
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

        hudCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport = new ScreenViewport(hudCamera);
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(this);

        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(1.5f);
        layout = new GlyphLayout();

        activeHeartTexture = new Texture("Heart/activeHeart.png");
        inactiveHeartTexture = new Texture("Heart/inactiveHeart.png");
        coinTexture = new Texture("images/coin.png");
        skeletonTexture = new Texture("images/skeleton.png");
        fullBulletTexture = new Texture("images/fullBullet.png");
        emptyBulletTexture = new Texture("images/emptyBullet.png");

        timerFont = new BitmapFont();
        Label.LabelStyle timeStyle = new Label.LabelStyle(timerFont, Color.WHITE);
        timeLabel = new Label("", timeStyle);
        timeLabel.setFontScale(2f);

        Table topTable = new Table();
        topTable.top();
        topTable.setFillParent(true);
        topTable.add(timeLabel).expandX().padTop(10);

        stage.addActor(topTable);
    }

    @Override
    public void render(float delta) {

        camera.position.set(player.getPosition().getX(), player.getPosition().getY(), 0);
        camera.update();
        stage.getViewport().apply();
        Main.getBatch().setProjectionMatrix(camera.combined);

        ScreenUtils.clear(Color.BLACK, true);

        if (game.getGamePreferences().isGrayScaleOn()) {
            Main.getBatch().setShader(grayscaleShader);
        } else {
            Main.getBatch().setShader(null);
        }

        if (cheatCodesScreen != null && cheatCodesScreen.isVisible()) {
            Gdx.input.setInputProcessor(cheatCodesScreen.getStage());
            cheatCodesScreen.render(delta);
            return;
        }

        if (gainedAbilitiesScreen != null && gainedAbilitiesScreen.isVisible()) {
            Gdx.input.setInputProcessor(gainedAbilitiesScreen.getStage());
            gainedAbilitiesScreen.render(delta);
            return;
        }

        if (!cheatConsoleView.isVisible()) {
            Main.getBatch().begin();

            if (pauseMenuController.isPaused()) {

                Gdx.input.setInputProcessor(pauseMenuView.getStage());
                pauseMenuView.show();

            } else if (game.isLost() || game.isGaveUp()) {

                Gdx.input.setInputProcessor(deadScreen.getStage());
                deadScreen.show();

            } else if (game.isWon()) {

                Gdx.input.setInputProcessor(winScreen.getStage());
                winScreen.show();

            } else {

                Gdx.input.setInputProcessor(this);
                controller.updateGame(delta);

            }

            deadScreen.render(Main.getBatch());
            winScreen.render(Main.getBatch());
            pauseMenuView.render(Main.getBatch());
            Main.getBatch().end();
        } else {
            Gdx.input.setInputProcessor(cheatConsoleView.getStage());
        }

        cheatConsoleController.updateAndRender();

        controller.getWorldController().renderShapes(camera);

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));

        if (!game.isGaveUp()
            && !game.isLost()
            && !game.isWon()
            && !pauseMenuController.isPaused()) {
            stage.draw();
            drawHUDText();
            drawAmmo();
            drawHearts(delta);
            drawTime();
            drawXPBar();
        }
    }



    private void drawHUDText() {
        Main.getBatch().setProjectionMatrix(hudCamera.combined);
        Main.getBatch().begin();

        String bottomLeftText = "Level: " + player.getLevel();

        float padding = 10f;

        layout.setText(font, bottomLeftText);
        font.draw(Main.getBatch(), bottomLeftText, padding, 20);

        float coinSize = 50;
        float coinX = hudCamera.viewportWidth - coinSize - padding;
        float coinY = hudCamera.viewportHeight - coinSize - padding;

        Main.getBatch().draw(coinTexture, coinX, coinY, coinSize, coinSize);

        String scoreText = String.valueOf(player.getScore());
        layout.setText(font, scoreText);
        font.draw(Main.getBatch(), scoreText, coinX - layout.width - 5, coinY + coinSize / 2 + layout.height / 2);

        float skeletonSize = 50;
        float skeletonX = coinX;
        float skeletonY = coinY - skeletonSize - 5;

        Main.getBatch().draw(skeletonTexture, skeletonX, skeletonY, skeletonSize, skeletonSize);

        String killText = String.valueOf(player.getKill());
        layout.setText(font, killText);
        font.draw(Main.getBatch(), killText, skeletonX - layout.width - 5, skeletonY + skeletonSize / 2 + layout.height / 2);

        Main.getBatch().end();
    }


    private void drawXPBar() {

        xpProgress = MathUtils.clamp((float) player.getXp() / player.getXpToLevelUp(), 0f, 1f);


        float barX = 20;
        float barY = 50;
        float barWidth = 30;
        float barHeight = 300;

        Main.getBatch().setProjectionMatrix(hudCamera.combined);
        Main.getBatch().begin();

        Main.getBatch().draw(xpBarBackground, barX, barY, barWidth, barHeight);

        float filledHeight = barHeight * xpProgress;

        Main.getBatch().draw(
            xpBarFill,
            barX,
            barY,
            barWidth,
            filledHeight,
            0, 0,
            xpBarFill.getWidth(),
            (int)(xpBarFill.getHeight() * xpProgress),
            false, false
        );

        Main.getBatch().end();
    }

    private void drawAmmo() {

        int magazineAmmo = player.getCurrentWeapon().getAmmoPerMagazine();
        int currentAmmo = player.getCurrentWeapon().getAmmo();

        int ammoWidth = 60;
        int ammoHeight= 100;
        float spacing = 20;

        float startX = camera.position.x + camera.viewportWidth / 2 - (Math.min(magazineAmmo, 60) * spacing) - 40;
        float startY = camera.position.y - camera.viewportHeight / 2 + 20;

        Main.getBatch().setProjectionMatrix(camera.combined);
        Main.getBatch().begin();

        for (int i = 0; i < Math.min(currentAmmo, 40); i++) {
            float x = startX + i * spacing;
            Main.getBatch().draw(fullBulletTexture, x, startY, ammoWidth, ammoHeight);
        }
        for (int i = 0; i < Math.min((magazineAmmo - currentAmmo), 40); i++) {
            float x = startX + (Math.min(currentAmmo, 40) + i) * spacing;
            Main.getBatch().draw(emptyBulletTexture, x, startY, ammoWidth, ammoHeight);
        }

        Main.getBatch().end();
    }

    private void drawTime() {
        float remainingTime = game.getGamePreferences().getGameTime().getTime() - game.getElapsedTime();

        int minutes = (int)(remainingTime / 60);
        int seconds = (int)(remainingTime % 60);

        String timeStr = String.format("%02d:%02d", minutes, seconds);
        timeLabel.setText(timeStr);

        if (remainingTime < 60) {
            timeLabel.setColor(Color.RED);
        } else {
            timeLabel.setColor(Color.WHITE);
        }
    }

    private void drawHearts(float delta) {
        heartAnimTime += delta;

        int hp = (int) player.getHP();
        int maxHp = (int) player.getMaxHP();
        float scale = 1.0f + 0.1f * MathUtils.sin(heartAnimTime * 4);
        float baseSize = 50f;
        float heartSize = baseSize * scale;
        float spacing = baseSize + 10;

        float startX = camera.position.x - camera.viewportWidth / 2 + 20;
        float startY = camera.position.y + camera.viewportHeight / 2 - 20 - heartSize;
        float startYStatic = camera.position.y + camera.viewportHeight / 2 - 20 - baseSize;

        Main.getBatch().setProjectionMatrix(camera.combined);
        Main.getBatch().begin();

        for (int i = 0; i < Math.min(hp, 12); i++) {
            float x = startX + i * spacing;
            Main.getBatch().draw(activeHeartTexture, x, startY, heartSize, heartSize);
        }
        for (int i = 0; i < Math.min((maxHp - hp), 12); i++) {
            float x = startX + (Math.min(hp, 12) + i) * spacing;
            Main.getBatch().draw(inactiveHeartTexture, x, startYStatic, baseSize, baseSize);
        }

        Main.getBatch().end();
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

    public void showCheatScreen() {
        cheatCodesScreen = new CheatCodesScreen(() -> {
            pauseMenuView.show();
            Gdx.input.setInputProcessor(pauseMenuView.getStage());
            cheatCodesScreen = null;
        });

        cheatCodesScreen.show();
        Gdx.input.setInputProcessor(cheatCodesScreen.getStage());
    }

    public void showGainedAbilitiesScreen() {
        gainedAbilitiesScreen = new GainedAbilitiesScreen(() -> {
            pauseMenuView.show();
            Gdx.input.setInputProcessor(pauseMenuView.getStage());
            gainedAbilitiesScreen = null;
        }, game);

        gainedAbilitiesScreen.show();
        Gdx.input.setInputProcessor(gainedAbilitiesScreen.getStage());
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

    public DeadScreen getDeadScreen() {
        return deadScreen;
    }

    public WinScreen getWinScreen() {
        return winScreen;
    }

    public void dispose() {
        stage.dispose();
    }
}
