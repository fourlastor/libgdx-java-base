package io.github.fourlastor.game.level.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Affine2;

public class TransformComponent implements Component {

    public final Affine2 local = new Affine2();
    public final Affine2 global = new Affine2();
}
