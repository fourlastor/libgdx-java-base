package io.github.fourlastor.game.intro;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
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

    @Inject
    public IntroScreen(InputMultiplexer inputMultiplexer, AssetManager assetManager) {
        this.inputMultiplexer = inputMultiplexer;

        viewport = new FitViewport(256, 144);
        stage = new Stage(viewport);

        Group group = new Group();
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = assetManager.get("fonts/quan-pixel-32.fnt");
        TextButton actor = new TextButton("click me", style);
        actor.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("clicked");
            }
        });
        group.addActor(actor);
        stage.addActor(group);
//        AnimationNode.Group node =
//                assetManager.get("images/included/animations/dancer/dancer.json", AnimationNode.Group.class);
//        animationGroup = new AnimationStateMachine(node);
    }

    @Override
    public void show() {
        inputMultiplexer.addProcessor(stage);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void hide() {
        inputMultiplexer.removeProcessor(stage);
    }


    @Override
    public void render(float delta) {
        ScreenUtils.clear(CLEAR_COLOR, true);
        stage.act(delta);
        stage.draw();
    }
}
