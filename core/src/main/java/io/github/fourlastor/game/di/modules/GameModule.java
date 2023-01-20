package io.github.fourlastor.game.di.modules;

import com.badlogic.gdx.InputMultiplexer;
import dagger.Module;
import dagger.Provides;
import io.github.fourlastor.game.GdxGame;
import io.github.fourlastor.game.gameover.GameOverComponent;
import io.github.fourlastor.game.intro.IntroComponent;
import io.github.fourlastor.game.level.di.LevelComponent;
import java.util.Random;
import javax.inject.Singleton;

@Module
public class GameModule {

    @Provides
    @Singleton
    public GdxGame game(
            InputMultiplexer multiplexer,
            LevelComponent.Builder levelBuilder,
            IntroComponent.Builder introBuilder,
            GameOverComponent.Builder gameOverBuilder) {
        return new GdxGame(multiplexer, levelBuilder, introBuilder, gameOverBuilder);
    }

    @Provides
    public Random random() {
        return new Random();
    }
}
