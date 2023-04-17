package io.github.fourlastor.game.level;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import io.github.fourlastor.game.di.ScreenScoped;
import javax.inject.Inject;

/**
 * Factory to create various entities: player, buildings, enemies..
 */
@ScreenScoped
public class EntitiesFactory {

    private static final float SCALE_XY = 1f / 32f;
    private final TextureAtlas textureAtlas;

    @Inject
    public EntitiesFactory(TextureAtlas textureAtlas) {
        this.textureAtlas = textureAtlas;
    }
}
