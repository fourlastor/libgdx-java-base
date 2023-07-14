package io.github.fourlastor.game.level.di;

import dagger.BindsInstance;
import dagger.Subcomponent;
import io.github.fourlastor.game.di.ScreenScoped;
import io.github.fourlastor.game.level.LevelScreen;
import io.github.fourlastor.game.route.Router;

@ScreenScoped
@Subcomponent(modules = {LevelModule.class, EcsModule.class})
public interface LevelComponent {

    @ScreenScoped
    LevelScreen screen();

    @Subcomponent.Builder
    interface Builder {

        Builder router(@BindsInstance Router router);

        LevelComponent build();
    }
}
