package io.github.fourlastor.game.level.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.CpuSpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Affine2;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.fourlastor.game.level.component.TextureComponent;
import io.github.fourlastor.game.level.component.TransformComponent;
import javax.inject.Inject;

public class DrawSystem extends IteratingSystem {

    private static final Family FAMILY =
            Family.all(TextureComponent.class, TransformComponent.class).get();

    private final ComponentMapper<TextureComponent> textures = ComponentMapper.getFor(TextureComponent.class);
    private final ComponentMapper<TransformComponent> transforms = ComponentMapper.getFor(TransformComponent.class);

    private final Color tmpColor = new Color();
    private final CpuSpriteBatch batch;
    private final Viewport viewport;

    @Inject
    public DrawSystem(CpuSpriteBatch batch, Viewport viewport) {
        super(FAMILY);
        this.batch = batch;
        this.viewport = viewport;
    }

    @Override
    public void update(float deltaTime) {
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        super.update(deltaTime);
        batch.end();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent transformComponent = transforms.get(entity);
        TextureComponent textureComponent = textures.get(entity);
        Affine2 global = transformComponent.global;
        Color color = textureComponent.color;
        TextureRegion region = textureComponent.region;
        tmpColor.set(batch.getColor());
        batch.setColor(color);
        batch.setTransformMatrix(global);
        batch.draw(region, -region.getRegionWidth() / 2f, -region.getRegionHeight() / 2f);
        batch.setColor(tmpColor);
    }
}
