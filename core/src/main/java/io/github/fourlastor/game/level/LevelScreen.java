package io.github.fourlastor.game.level;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;
import javax.inject.Inject;

public class LevelScreen extends ScreenAdapter {

    private final Engine engine;
    private final Viewport viewport;
    private final EntitiesFactory entitiesFactory;

    private final World world;

    @Inject
    public LevelScreen(Engine engine, Viewport viewport, EntitiesFactory entitiesFactory, World world) {
        this.engine = engine;
        this.viewport = viewport;
        this.entitiesFactory = entitiesFactory;
        this.world = world;
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void render(float delta) {
        engine.update(delta);
    }

    @Override
    public void show() {
        // entitiesFactory.create(...)
    }

    @Override
    public void hide() {
        engine.removeAllEntities();
        engine.removeAllSystems();
    }

    @Override
    public void dispose() {
        super.dispose();
        world.dispose();
    }
}
