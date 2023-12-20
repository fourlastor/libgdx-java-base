package io.github.fourlastor.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.github.tommyettinger.ds.ObjectList;
import io.github.fourlastor.game.di.GameComponent;
import io.github.fourlastor.game.intro.IntroComponent;
import io.github.fourlastor.game.level.di.LevelComponent;
import io.github.fourlastor.game.route.Router;
import io.github.fourlastor.harlequin.Harlequin;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class GdxGame extends Game {

    private Stage stage;


    @Inject
    public GdxGame() {
    }

    @Override
    public void create() {
        stage = new Stage(new FitViewport(800, 600));
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = new BitmapFont(Gdx.files.internal("fonts/roboto.fnt"));
        style.fontColor = Color.CORAL;
        stage.addActor(new Label("the lazy dog jumps over the quick brown", style));
    }

    @Override
    public void render() {
        ScreenUtils.clear(Color.GRAY, true);
        stage.getViewport().apply();
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        stage.getViewport().update(width, height);
    }

    public static GdxGame createGame() {
        return GameComponent.component().game();
    }

}
