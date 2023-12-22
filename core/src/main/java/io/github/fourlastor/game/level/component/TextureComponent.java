package io.github.fourlastor.game.level.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureComponent implements Component {

    public final TextureRegion region;
    public final Color color;

    public TextureComponent(TextureRegion region, Color color) {
        this.region = region;
        this.color = color;
    }
}
