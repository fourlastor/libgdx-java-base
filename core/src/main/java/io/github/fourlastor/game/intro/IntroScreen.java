package io.github.fourlastor.game.intro;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.github.tommyettinger.textra.TypingLabel;
import io.github.fourlastor.game.di.modules.AssetsModule;
import io.github.fourlastor.game.route.Router;
import javax.inject.Inject;
import javax.inject.Named;

public class IntroScreen extends ScreenAdapter {

    public static final Color CLEAR_COLOR = Color.valueOf("000000");
    private final Router router;
    private final InputMultiplexer inputMultiplexer;
    private final Stage stage;
    private final Viewport viewport;

    private final TextureRegion pixel;
    private Image dragon_queen;
    private Image earth_ground;
    private Image earth_space;
    private Image missiles_and_explosion_1;
    private Image missiles_and_explosion_2;
    private Image missiles_and_explosion_3;
    private Image silo_and_skeleton;
    private Image sky_and_mountains;
    private Image sky_dragon;
    private Image space;
    private Image zebra_king;
    private Image lyze;
    private Image black_screen;

    private TypingLabel subtitles;

    @Inject
    public IntroScreen(
            Router router, InputMultiplexer inputMultiplexer, @Named(AssetsModule.WHITE_PIXEL) TextureRegion pixel) {
        this.router = router;
        this.inputMultiplexer = inputMultiplexer;
        this.pixel = pixel;
        float screenHeight = Gdx.graphics.getHeight();
        float screenWidth = screenHeight / 9f * 16f;

        viewport = new FitViewport(screenWidth, screenHeight);
        stage = new Stage(viewport);
    }

    @Override
    public void show() {
        imageSetup();
        subtitlesSetup();

        earth_space.addAction(Actions.sequence(actI(), actII()));
        inputMultiplexer.addProcessor(processor);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void hide() {
        inputMultiplexer.removeProcessor(processor);
    }

    private final InputProcessor processor = new InputAdapter() {
        @Override
        public boolean keyUp(int keycode) {
            System.out.println("Go to level");
            return true;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            System.out.println("Go to level");
            return true;
        }
    };

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(CLEAR_COLOR.r, CLEAR_COLOR.g, CLEAR_COLOR.b, CLEAR_COLOR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    private Action actI() {
        return Actions.sequence(
                Actions.fadeIn(2f),
                Actions.delay(2f),
                Actions.run(() -> {
                    missiles_and_explosion_1.addAction(Actions.fadeIn(1f));
                    subtitles.restart();
                    subtitles.setText("The war of the floating cities...");
                }),
                Actions.delay(.1f),
                Actions.run(() -> zebra_king.addAction(Actions.fadeIn(6f))),
                Actions.delay(.3f),
                Actions.run(() -> dragon_queen.addAction(Actions.fadeIn(6f))),
                Actions.delay(3f),
                Actions.run(() -> {
                    missiles_and_explosion_1.addAction(Actions.fadeOut(1f));
                    missiles_and_explosion_2.addAction(Actions.fadeIn(1f));
                    subtitles.restart();
                    subtitles.setText("left this world in ruins...");
                }),
                Actions.delay(2f),
                Actions.run(() -> {
                    missiles_and_explosion_2.addAction(Actions.fadeOut(1f));
                    missiles_and_explosion_3.addAction(Actions.fadeIn(1f));
                }),
                Actions.delay(.2f),
                Actions.delay(4f),
                Actions.run(() -> {
                    zebra_king.addAction(
                            Actions.sequence(Actions.fadeOut(.75f), Actions.run(() -> zebra_king.setVisible(false))));
                    dragon_queen.addAction(
                            Actions.sequence(Actions.fadeOut(1f), Actions.run(() -> dragon_queen.setVisible(false))));
                    missiles_and_explosion_3.addAction(Actions.sequence(
                            Actions.fadeOut(1f), Actions.run(() -> missiles_and_explosion_3.setVisible(false))));
                    earth_space.addAction(
                            Actions.sequence(Actions.fadeOut(1f), Actions.run(() -> earth_space.setVisible(false))));
                    space.addAction(Actions.sequence(Actions.fadeOut(1f), Actions.run(() -> space.setVisible(false))));
                    subtitles.addAction(Actions.fadeOut(1f));
                }));
    }

    private Action actII() {
        return Actions.sequence(
                Actions.run(() -> {
                    sky_and_mountains.addAction(Actions.fadeIn(2));
                    earth_ground.addAction(Actions.fadeIn(2));
                    silo_and_skeleton.addAction(Actions.fadeIn(2));
                    sky_dragon.addAction(Actions.sequence(
                            Actions.fadeIn(0),
                            Actions.moveTo(-screenWidth() * .6f, -screenHeight() * .1f, 0),
                            Actions.moveTo(screenWidth() * 2, screenHeight() * .4f, 30)));
                    lyze.setPosition(screenWidth() * .05f, 0);
                    lyze.addAction(Actions.sequence(
                            Actions.fadeIn(0f), Actions.moveTo(-screenWidth(), screenHeight() * .1f, 30f)));
                    subtitles.addAction(Actions.fadeIn(1f));
                    subtitles.setColor(new Color(0.039f, 0.039f, 0.043f, 1f));
                    subtitles.restart();
                    subtitles.setText("but from the destruction...");
                }),
                Actions.delay(3f),
                Actions.run(() -> {
                    subtitles.restart();
                    subtitles.setText("well, life always finds a way");
                }),
                Actions.delay(4f),
                Actions.run(() -> {
                    subtitles.restart();
                    subtitles.setText("(except for the zebras, they were all exterminated)");
                }),
                Actions.delay(2f),
                Actions.run(() -> subtitles.addAction(Actions.fadeOut(3f))),
                Actions.delay(2f),
                Actions.run(() -> black_screen.addAction(Actions.fadeIn(1f))),
                Actions.delay(3f),
                Actions.run(router::goToLevel));
    }

    private int screenWidth() {
        return viewport.getScreenWidth();
    }

    private int screenHeight() {
        return viewport.getScreenHeight();
    }

    private void subtitlesSetup() {
        Label.LabelStyle label32Style = new Label.LabelStyle();
        label32Style.font = new BitmapFont(Gdx.files.internal("fonts/quan-pixel-32.fnt"));
        label32Style.fontColor = Color.WHITE;
        label32Style.font.setColor(Color.WHITE);
        subtitles = new TypingLabel("(press any key to skip)", label32Style);
        subtitles.setPosition(screenWidth() / 32f, screenHeight() / 16f);
        subtitles.setWidth(screenWidth());
        subtitles.setWrap(true);
        subtitles.setAlignment(Align.center);
        subtitles.getColor().a = 0;
        subtitles.addAction(Actions.fadeIn(1f));
        subtitles.setColor(new Color(1f, 1f, 0.949f, 1f));
        Table table = new Table();
        table.setFillParent(true);
        table.add(subtitles).growX().expandY().bottom().padBottom(Gdx.graphics.getHeight() * .05f);
        stage.addActor(table);
    }

    private void imageSetup() {
        dragon_queen = new Image(pixel);
        dragon_queen.setColor(0, 1, 0, 1);
        earth_ground = new Image(pixel);
        earth_space = new Image(pixel);
        missiles_and_explosion_1 = new Image(pixel);
        missiles_and_explosion_2 = new Image(pixel);
        missiles_and_explosion_3 = new Image(pixel);
        silo_and_skeleton = new Image(pixel);
        sky_and_mountains = new Image(pixel);
        sky_dragon = new Image(pixel);
        lyze = new Image(pixel);
        space = new Image(pixel);
        zebra_king = new Image(pixel);
        black_screen = new Image(pixel);

        stage.addActor(space);
        stage.addActor(dragon_queen);
        stage.addActor(earth_ground);
        stage.addActor(earth_space);
        stage.addActor(missiles_and_explosion_1);
        stage.addActor(missiles_and_explosion_2);
        stage.addActor(missiles_and_explosion_3);
        stage.addActor(sky_and_mountains);
        stage.addActor(silo_and_skeleton);
        stage.addActor(sky_dragon);
        stage.addActor(lyze);
        stage.addActor(zebra_king);
        stage.addActor(black_screen);

        dragon_queen.getColor().a = 0;
        earth_ground.getColor().a = 0;
        earth_space.getColor().a = 0;
        missiles_and_explosion_1.getColor().a = 0;
        missiles_and_explosion_2.getColor().a = 0;
        missiles_and_explosion_3.getColor().a = 0;
        silo_and_skeleton.getColor().a = 0;
        sky_and_mountains.getColor().a = 0;
        sky_dragon.getColor().a = 0;
        lyze.getColor().a = 0;
        zebra_king.getColor().a = 0;
        black_screen.getColor().a = 0;

        space.setSize(screenWidth(), screenHeight());
        dragon_queen.setSize(screenWidth(), screenHeight());
        earth_space.setSize(screenWidth(), screenHeight());
        earth_ground.setSize(screenWidth(), screenHeight());
        silo_and_skeleton.setSize(screenWidth(), screenHeight());
        sky_dragon.setSize(screenWidth(), screenHeight());
        lyze.setSize(screenWidth(), screenHeight());
        sky_and_mountains.setSize(screenWidth(), screenHeight());
        missiles_and_explosion_1.setSize(screenWidth(), screenHeight());
        missiles_and_explosion_2.setSize(screenWidth(), screenHeight());
        missiles_and_explosion_3.setSize(screenWidth(), screenHeight());
        zebra_king.setSize(screenWidth(), screenHeight());
        black_screen.setSize(screenWidth(), screenHeight());
    }
}
