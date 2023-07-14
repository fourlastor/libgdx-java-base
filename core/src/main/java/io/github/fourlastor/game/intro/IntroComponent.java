package io.github.fourlastor.game.intro;

import dagger.BindsInstance;
import dagger.Subcomponent;
import io.github.fourlastor.game.di.ScreenScoped;
import io.github.fourlastor.game.route.Router;

@ScreenScoped
@Subcomponent
public interface IntroComponent {

    @ScreenScoped
    IntroScreen screen();

    @Subcomponent.Builder
    interface Builder {

        Builder router(@BindsInstance Router router);

        IntroComponent build();
    }
}
