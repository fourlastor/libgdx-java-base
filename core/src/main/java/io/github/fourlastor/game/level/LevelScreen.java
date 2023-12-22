package io.github.fourlastor.game.level;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.utils.viewport.Viewport;
import javax.inject.Inject;

public class LevelScreen extends ScreenAdapter {

    private final EntitiesFactory entitiesFactory;
    private final Viewport viewport;
    private final Engine engine;

    @Inject
    public LevelScreen(EntitiesFactory entitiesFactory, Engine engine, Viewport viewport) {
        this.entitiesFactory = entitiesFactory;
        this.engine = engine;
        this.viewport = viewport;
    }

    @Override
    public void show() {
        for (Entity entity : entitiesFactory.demoEntities()) {
            engine.addEntity(entity);
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void render(float delta) {
        engine.update(delta);
    }
}
