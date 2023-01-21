package io.github.fourlastor.game.intro;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.fourlastor.game.di.modules.AssetsModule;
import io.github.fourlastor.harlequin.animation.Animation;
import io.github.fourlastor.harlequin.animation.KeyFrame;
import io.github.fourlastor.harlequin.animation.KeyFrameAnimation;
import io.github.fourlastor.harlequin.ui.AnimatedImage;
import java.util.Arrays;
import javax.inject.Inject;
import javax.inject.Named;

public class IntroScreen extends ScreenAdapter {

    public static final Color CLEAR_COLOR = Color.valueOf("000000");

    private static final Color COLOR_1 = Color.WHITE;
    private static final Color COLOR_2 = new Color(0.7f, 0.7f, 0.7f, 1f);
    private static final Color COLOR_3 = new Color(0.3f, 0.3f, 0.3f, 1f);
    private static final Color COLOR_4 = Color.BLACK;
    private static final int T0 = 0;
    private static final int T1 = 300;
    private static final int T2 = 800;
    private static final int T3 = 1600;
    private static final float DURATION = 2f;
    private static final Animation.PlayMode PLAY_MODE = Animation.PlayMode.NORMAL;
    private final InputMultiplexer inputMultiplexer;
    private final Stage stage;
    private final Viewport viewport;

    @Inject
    public IntroScreen(InputMultiplexer inputMultiplexer, @Named(AssetsModule.WHITE_PIXEL) TextureRegion pixel) {
        this.inputMultiplexer = inputMultiplexer;
        float screenHeight = Gdx.graphics.getHeight();
        float screenWidth = screenHeight / 9f * 16f;

        viewport = new FitViewport(screenWidth, screenHeight);
        stage = new Stage(viewport);
        TextureRegionDrawable white = new TextureRegionDrawable(pixel);
        Drawable drawable1 = white.tint(COLOR_1);
        Drawable drawable2 = white.tint(COLOR_2);
        Drawable drawable3 = white.tint(COLOR_3);
        Drawable drawable4 = white.tint(COLOR_4);

        KeyFrame<Drawable> frame1 = KeyFrame.create(T0, drawable1);
        KeyFrame<Drawable> frame2 = KeyFrame.create(T1, drawable2);
        KeyFrame<Drawable> frame3 = KeyFrame.create(T2, drawable3);
        KeyFrame<Drawable> frame4 = KeyFrame.create(T3, drawable4);
        KeyFrameAnimation<Drawable> animation =
                new KeyFrameAnimation<>(Arrays.asList(frame1, frame2, frame3, frame4), DURATION, PLAY_MODE);

        AnimatedImage image = new AnimatedImage(animation);
        image.setSize(screenWidth, screenHeight);
        stage.addActor(image);
    }

    @Override
    public void show() {
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
}
