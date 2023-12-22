package io.github.fourlastor.game.level.di;

import com.badlogic.ashley.core.ComponentMapper;
import dagger.Module;
import dagger.Provides;
import io.github.fourlastor.game.di.ScreenScoped;
import io.github.fourlastor.game.level.component.ControlsComponent;
import io.github.fourlastor.game.level.component.HierarchyComponent;
import io.github.fourlastor.game.level.component.TextureComponent;
import io.github.fourlastor.game.level.component.TransformComponent;

@Module
public class EcsModule {

    @Provides
    @ScreenScoped
    public ComponentMapper<ControlsComponent> controls() {
        return ComponentMapper.getFor(ControlsComponent.class);
    }

    @Provides
    @ScreenScoped
    public ComponentMapper<TextureComponent> textures() {
        return ComponentMapper.getFor(TextureComponent.class);
    }

    @Provides
    @ScreenScoped
    public ComponentMapper<TransformComponent> transforms() {
        return ComponentMapper.getFor(TransformComponent.class);
    }

    @Provides
    @ScreenScoped
    public ComponentMapper<HierarchyComponent> hierarchies() {
        return ComponentMapper.getFor(HierarchyComponent.class);
    }
}
