package io.github.fourlastor.game.level.di;

import com.badlogic.ashley.core.ComponentMapper;
import dagger.Module;
import dagger.Provides;
import io.github.fourlastor.game.di.ScreenScoped;
import io.github.fourlastor.game.level.component.ActorComponent;
import io.github.fourlastor.game.level.component.AnimatedImageComponent;
import io.github.fourlastor.game.level.component.BodyBuilderComponent;
import io.github.fourlastor.game.level.component.BodyComponent;
import io.github.fourlastor.game.level.component.ChunkRemovalComponent;
import io.github.fourlastor.game.level.component.MovingComponent;
import io.github.fourlastor.game.level.component.PlayerComponent;
import io.github.fourlastor.game.level.component.SoundComponent;

@Module
public class EcsModule {

    @Provides
    @ScreenScoped
    public ComponentMapper<AnimatedImageComponent> animatedImageComponent() {
        return ComponentMapper.getFor(AnimatedImageComponent.class);
    }

    @Provides
    @ScreenScoped
    public ComponentMapper<ActorComponent> imageComponent() {
        return ComponentMapper.getFor(ActorComponent.class);
    }

    @Provides
    @ScreenScoped
    public ComponentMapper<BodyComponent> bodyComponent() {
        return ComponentMapper.getFor(BodyComponent.class);
    }

    @Provides
    @ScreenScoped
    public ComponentMapper<BodyBuilderComponent> bodyBuilderComponent() {
        return ComponentMapper.getFor(BodyBuilderComponent.class);
    }

    @Provides
    @ScreenScoped
    public ComponentMapper<PlayerComponent> playerComponent() {
        return ComponentMapper.getFor(PlayerComponent.class);
    }

    @Provides
    @ScreenScoped
    public ComponentMapper<ChunkRemovalComponent> chunkRemovalComponent() {
        return ComponentMapper.getFor(ChunkRemovalComponent.class);
    }

    @Provides
    @ScreenScoped
    public ComponentMapper<MovingComponent> movingComponent() {
        return ComponentMapper.getFor(MovingComponent.class);
    }

    @Provides
    @ScreenScoped
    public ComponentMapper<SoundComponent> soundComponent() {
        return ComponentMapper.getFor(SoundComponent.class);
    }
}
