package io.github.fourlastor.game.level.component;

import com.badlogic.ashley.core.Component;
import io.github.fourlastor.harlequin.ui.AnimatedImage;

public class AnimatedImageComponent implements Component {

    public final AnimatedImage image;

    public AnimatedImageComponent(AnimatedImage image) {
        this.image = image;
    }
}
