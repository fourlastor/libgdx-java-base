package io.github.fourlastor.game.level.di;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.CpuSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import dagger.Module;
import dagger.Provides;
import io.github.fourlastor.game.di.ScreenScoped;
import io.github.fourlastor.game.level.system.ClearScreenSystem;
import io.github.fourlastor.game.level.system.DrawSystem;
import io.github.fourlastor.game.level.system.MoveSystem;
import io.github.fourlastor.game.level.system.TransformHierarchySystem;

@Module
public class LevelModule {

    @Provides
    @ScreenScoped
    public Engine engine(
            MoveSystem moveSystem,
            TransformHierarchySystem transformHierarchySystem,
            DrawSystem drawSystem,
            ClearScreenSystem clearScreenSystem) {
        Engine engine = new Engine();
        engine.addSystem(moveSystem);
        engine.addSystem(transformHierarchySystem);
        engine.addSystem(clearScreenSystem);
        engine.addSystem(drawSystem);
        return engine;
    }

    @Provides
    @ScreenScoped
    public CpuSpriteBatch batch() {
        return new CpuSpriteBatch();
    }

    @Provides
    @ScreenScoped
    public Viewport viewport() {
        return new FitViewport(160f, 90f);
    }

    @Provides
    @ScreenScoped
    public Stage stage(Viewport viewport, SpriteBatch batch) {
        return new Stage(viewport, batch);
    }

    @Provides
    @ScreenScoped
    public Camera camera(Viewport viewport) {
        return viewport.getCamera();
    }

    @Provides
    @ScreenScoped
    public MessageDispatcher messageDispatcher() {
        return new MessageDispatcher();
    }
}
