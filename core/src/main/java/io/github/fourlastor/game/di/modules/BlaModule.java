package io.github.fourlastor.game.di.modules;

import dagger.Module;
import dagger.Provides;

@Module
public class BlaModule {
    private final Runnable theRun;

    public BlaModule(Runnable theRun) {
        this.theRun = theRun;
    }

    @Provides
    public Runnable theRun() {
        return theRun;
    }
}
