package io.github.fourlastor.game.di;

import dagger.Component;
import io.github.fourlastor.game.GdxGame;
import io.github.fourlastor.game.di.modules.EcsModule;
import io.github.fourlastor.game.di.modules.GameModule;
import io.github.fourlastor.game.di.modules.ScreensModule;
import javax.inject.Singleton;

@Singleton
@Component(
        modules = {
            GameModule.class,
            EcsModule.class,
            ScreensModule.class,
        })
public interface GameComponent {
    GdxGame game();

    static GameComponent component() {
        return DaggerGameComponent.create();
    }
}
