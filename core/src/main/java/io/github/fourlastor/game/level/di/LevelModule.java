package io.github.fourlastor.game.level.di;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.CpuSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import dagger.Module;
import dagger.Provides;
import io.github.fourlastor.game.di.ScreenScoped;
import io.github.fourlastor.game.level.Layer;
import io.github.fourlastor.game.level.system.ClearScreenSystem;
import io.github.fourlastor.game.level.system.DrawSystem;
import io.github.fourlastor.game.level.system.MoveSystem;
import io.github.fourlastor.game.level.system.TransformHierarchySystem;
import io.github.fourlastor.harlequin.system.StageSystem;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Qualifier;

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
    public StageSystem stageSystem(Stage stage, @Layers Class<? extends Enum<?>> layers) {
        return new StageSystem(stage, layers);
    }

    @Provides
    @Layers
    public Class<? extends Enum<?>> layersEnum() {
        return Layer.class;
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
    @Gravity
    public Vector2 gravity() {
        return new Vector2(0f, -10f);
    }

    @Provides
    @ScreenScoped
    public World world(@Gravity Vector2 gravity) {
        return new World(gravity, true);
    }

    @Provides
    @ScreenScoped
    public MessageDispatcher messageDispatcher() {
        return new MessageDispatcher();
    }

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Layers {}
}
