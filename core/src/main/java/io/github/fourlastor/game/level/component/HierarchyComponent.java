package io.github.fourlastor.game.level.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

public class HierarchyComponent implements Component {
    public final Entity parent;

    public HierarchyComponent(Entity parent) {
        this.parent = parent;
    }
}
