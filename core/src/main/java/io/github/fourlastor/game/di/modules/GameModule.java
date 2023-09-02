package io.github.fourlastor.game.di.modules;

import dagger.Module;
import dagger.Provides;
import java.util.Random;

@Module
public class GameModule {

    @Provides
    public Random random() {
        return new Random();
    }
}
