package io.github.fourlastor.game.level.system;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.utils.ObjectIntMap;
import com.github.tommyettinger.ds.ObjectList;
import io.github.fourlastor.game.level.component.HierarchyComponent;
import io.github.fourlastor.game.level.component.TransformComponent;
import java.util.Comparator;
import java.util.List;
import javax.inject.Inject;

public class TransformHierarchySystem extends EntitySystem implements EntityListener {

    private static final Family FAMILY =
            Family.all(HierarchyComponent.class, TransformComponent.class).get();
    private static final Family FAMILY_LINKED_ENTITIES =
            Family.all(LinkedEntitiesComponent.class).get();
    private final ComponentMapper<HierarchyComponent> hierarchies = ComponentMapper.getFor(HierarchyComponent.class);
    private final ComponentMapper<TransformComponent> transforms = ComponentMapper.getFor(TransformComponent.class);
    private final ComponentMapper<LinkedEntitiesComponent> linkedEntities =
            ComponentMapper.getFor(LinkedEntitiesComponent.class);

    private final EntityListener linkedEntitiesRemoval = new EntityListener() {
        @Override
        public void entityAdded(Entity entity) {}

        @Override
        public void entityRemoved(Entity entity) {
            Engine engine = getEngine();
            for (Entity linkedEntity : linkedEntities.get(entity).linkedEntities) {
                engine.removeEntity(linkedEntity);
            }
        }
    };

    private ObjectList<Entity> sortedEntities;

    @Inject
    public TransformHierarchySystem() {}

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        ObjectIntMap<Entity> depthMap = new ObjectIntMap<>();
        ImmutableArray<Entity> unsorted = engine.getEntitiesFor(FAMILY);
        sortedEntities = new ObjectList<>(unsorted.size());
        for (Entity entity : unsorted) {
            int depth = calculateDepth(entity);
            depthMap.put(entity, depth);
            sortedEntities.add(entity);
        }

        sortedEntities.sort(Comparator.comparingInt(e -> depthMap.get(e, 0)));
        engine.addEntityListener(FAMILY, this);
        engine.addEntityListener(FAMILY_LINKED_ENTITIES, linkedEntitiesRemoval);
    }

    @Override
    public void update(float deltaTime) {
        for (int i = 0; i < sortedEntities.size(); i++) {
            Entity entity = sortedEntities.get(i);
            Entity parent = parentOf(entity);
            TransformComponent entityTransform = transforms.get(entity);
            if (parent != null) {
                TransformComponent parentTransform = transforms.get(parent);
                entityTransform.global.set(parentTransform.global).mul(entityTransform.local);
            } else {
                entityTransform.global.set(entityTransform.local);
            }
        }
    }

    @Override
    public void entityAdded(Entity entity) {
        int index = 0;
        Entity parent = parentOf(entity);
        if (parent != null) {
            int parentIndex = sortedEntities.indexOf(parent);
            index = parentIndex + 1;
            if (!linkedEntities.has(parent)) {
                parent.add(new LinkedEntitiesComponent());
            }
            linkedEntities.get(parent).linkedEntities.add(entity);
        }
        sortedEntities.add(index, entity);
    }

    @Override
    public void entityRemoved(Entity entity) {
        sortedEntities.remove(entity);
        Entity parent = parentOf(entity);
        if (parent != null) {
            linkedEntities.get(parent).linkedEntities.remove(entity);
        }
    }

    @Override
    public void removedFromEngine(Engine engine) {
        sortedEntities = null;
        super.removedFromEngine(engine);
    }

    private Entity parentOf(Entity entity) {
        return hierarchies.get(entity).parent;
    }

    private int calculateDepth(Entity entity) {
        int depth = 0;
        Entity parent = parentOf(entity);
        while (parent != null) {
            depth += 1;
            parent = parentOf(entity);
        }
        return depth;
    }

    private static class LinkedEntitiesComponent implements Component {
        public final List<Entity> linkedEntities = new ObjectList<>();
    }
}
