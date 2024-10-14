package io.github.fourlastor.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
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
public class GdxGame extends Game implements Router {

    private final InputMultiplexer multiplexer;

    private final LevelComponent.Builder levelScreenFactory;
    private final IntroComponent.Builder introScreenFactory;
    private final Runnable theRun;
    private Screen pendingScreen = null;

    @Inject
    public GdxGame(
            InputMultiplexer multiplexer,
            LevelComponent.Builder levelScreenFactory,
            IntroComponent.Builder introScreenFactory,
            Runnable theRun) {
        this.multiplexer = multiplexer;
        this.levelScreenFactory = levelScreenFactory;
        this.introScreenFactory = introScreenFactory;
        this.theRun = theRun;
        Harlequin.LIST_CREATOR = new Harlequin.ListCreator() {
            @Override
            public <T> List<T> newList() {
                return new ObjectList<>();
            }

            @Override
            public <T> List<T> newList(int size) {
                return new ObjectList<>(size);
            }
        };
    }

    @Override
    public void create() {
        if (Gdx.app.getType() != Application.ApplicationType.Android) {

            Cursor customCursor =
                    Gdx.graphics.newCursor(new Pixmap(Gdx.files.internal("images/included/cursor.png")),
 0, 0);
            Gdx.graphics.setCursor(customCursor);
        }
        theRun.run();
        Gdx.input.setInputProcessor(multiplexer);
        goToIntro();
    }

    @Override
    public void render() {
        if (pendingScreen != null) {
            setScreen(pendingScreen);
            pendingScreen = null;
        }
        super.render();
    }

    public static GdxGame createGame(Runnable theRun) {
        return GameComponent.component(theRun).game();
    }

    @Override
    public void goToIntro() {
        pendingScreen = introScreenFactory.router(this).build().screen();
    }

    @Override
    public void goToLevel() {
        pendingScreen = levelScreenFactory.router(this).build().screen();
    }
}
