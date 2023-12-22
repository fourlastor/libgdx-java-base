package io.github.fourlastor.game.level.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import io.github.fourlastor.game.level.component.ControlsComponent;
import io.github.fourlastor.game.level.component.TransformComponent;
import javax.inject.Inject;

public class MoveSystem extends IteratingSystem {

    private static final Family FAMILY =
            Family.all(ControlsComponent.class, TransformComponent.class).get();

    private final ComponentMapper<ControlsComponent> controls = ComponentMapper.getFor(ControlsComponent.class);
    private final ComponentMapper<TransformComponent> transforms = ComponentMapper.getFor(TransformComponent.class);

    private final Color tmpColor = new Color();

    @Inject
    public MoveSystem() {
        super(FAMILY);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        ControlsComponent controlsComponent = controls.get(entity);
        TransformComponent transformComponent = transforms.get(entity);
        float xDelta = 0f;
        if (Gdx.input.isKeyPressed(controlsComponent.xDown)) {
            xDelta -= 1f;
        }
        if (Gdx.input.isKeyPressed(controlsComponent.xUp)) {
            xDelta += 1f;
        }
        float rotationDelta = 0f;
        if (Gdx.input.isKeyPressed(controlsComponent.rotDown)) {
            rotationDelta -= 1f;
        }
        if (Gdx.input.isKeyPressed(controlsComponent.rotUp)) {
            rotationDelta += 1f;
        }
        float scale = 1f;
        if (Gdx.input.isKeyPressed(controlsComponent.scaleDown)) {
            scale -= 0.1f;
        }
        if (Gdx.input.isKeyPressed(controlsComponent.scaleUp)) {
            scale += 0.1f;
        }
        transformComponent.local.scale(scale, scale);
        transformComponent.local.rotate(rotationDelta);
        transformComponent.local.translate(xDelta * deltaTime, 0);
    }
}
