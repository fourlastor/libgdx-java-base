package io.github.fourlastor.game.level;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.github.tommyettinger.ds.ObjectList;
import io.github.fourlastor.game.di.ScreenScoped;
import io.github.fourlastor.game.level.component.ControlsComponent;
import io.github.fourlastor.game.level.component.HierarchyComponent;
import io.github.fourlastor.game.level.component.TextureComponent;
import io.github.fourlastor.game.level.component.TransformComponent;
import java.util.List;
import javax.inject.Inject;

/**
 * Factory to create various entities: player, buildings, enemies..
 */
@ScreenScoped
public class EntitiesFactory {

    private static final float SCALE_XY = 1f / 32f;
    private final TextureAtlas textureAtlas;

    @Inject
    public EntitiesFactory(TextureAtlas textureAtlas) {
        this.textureAtlas = textureAtlas;
    }

    public List<Entity> demoEntities() {
        ObjectList<Entity> entities = new ObjectList<>(2);
        TextureAtlas.AtlasRegion whitePixel = textureAtlas.findRegion("whitePixel");
        Entity parent = new Entity();
        TransformComponent parentTransform = parent.addAndReturn(new TransformComponent());
        parentTransform.local.setToTranslation(160f / 2, 90f / 2);
        parentTransform.local.setToScaling(10f, 10f);
        parent.add(new HierarchyComponent(null));
        parent.add(new TextureComponent(whitePixel, Color.WHITE));
        parent.add(new ControlsComponent(
                Input.Keys.A, Input.Keys.S, Input.Keys.Q, Input.Keys.W, Input.Keys.Z, Input.Keys.X));
        entities.add(parent);
        Entity child = new Entity();
        child.addAndReturn(new TransformComponent());
        child.add(new HierarchyComponent(parent));
        child.add(new TextureComponent(whitePixel, Color.RED));
        child.add(new ControlsComponent(
                Input.Keys.D, Input.Keys.F, Input.Keys.E, Input.Keys.R, Input.Keys.C, Input.Keys.V));
        entities.add(child);

        return entities;
    }
}
