package io.github.fourlastor.game.level;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.utils.viewport.Viewport;
import java.util.List;
import javax.inject.Inject;

public class LevelScreen extends ScreenAdapter {

    private final EntitiesFactory entitiesFactory;
    private final Viewport viewport;
    private final Engine engine;

    private Entity root;
    private Entity child;

    @Inject
    public LevelScreen(EntitiesFactory entitiesFactory, Engine engine, Viewport viewport) {
        this.entitiesFactory = entitiesFactory;
        this.engine = engine;
        this.viewport = viewport;
    }

    @Override
    public void show() {
        List<Entity> demoEntities = entitiesFactory.demoEntities();
        root = demoEntities.get(0);
        child = demoEntities.get(1);
        for (Entity entity : demoEntities) {
            engine.addEntity(entity);
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.M) && root != null) {
            engine.removeEntity(root);
            root = null;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.N) && child != null) {
            engine.removeEntity(child);
            child = null;
        }
        engine.update(delta);
    }
}
