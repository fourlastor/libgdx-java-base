package io.github.fourlastor.game.intro;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.fourlastor.harlequin.animation.AnimationNode;
import io.github.fourlastor.harlequin.ui.AnimationStateMachine;
import javax.inject.Inject;

public class IntroScreen extends ScreenAdapter {

    public static Color CLEAR_COLOR = new Color(0x333333ff);

    private final InputMultiplexer inputMultiplexer;
    private final Stage stage;
    private final Viewport viewport;
    private final AnimationStateMachine animationGroup;

    @Inject
    public IntroScreen(InputMultiplexer inputMultiplexer, AssetManager assetManager) {
        this.inputMultiplexer = inputMultiplexer;

        viewport = new FitViewport(256, 144);
        stage = new Stage(viewport);

        AnimationNode.Group node =
                assetManager.get("images/included/animations/dancer/dancer.json", AnimationNode.Group.class);
        animationGroup = new AnimationStateMachine(node);
        stage.addActor(animationGroup);
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
            switch (keycode) {
                case Input.Keys.A:
                    animationGroup.enter("balancing");
                    return true;
                case Input.Keys.S:
                    animationGroup.enter("hips");
                    return true;
                case Input.Keys.D:
                    animationGroup.enter("skip");
                    return true;
                case Input.Keys.F:
                    animationGroup.enter("slide");
                    return true;
                case Input.Keys.G:
                    animationGroup.enter("snap");
                    return true;
            }
            return false;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            System.out.println("Go to level");
            return true;
        }
    };

    @Override
    public void render(float delta) {
        ScreenUtils.clear(CLEAR_COLOR, true);
        stage.act(delta);
        stage.draw();
    }
}
