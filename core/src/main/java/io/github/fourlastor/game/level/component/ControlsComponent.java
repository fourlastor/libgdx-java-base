package io.github.fourlastor.game.level.component;

import com.badlogic.ashley.core.Component;

public class ControlsComponent implements Component {
    public final int xDown;
    public final int xUp;
    public final int rotDown;
    public final int rotUp;
    public final int scaleDown;
    public final int scaleUp;

    public ControlsComponent(int xDown, int xUp, int rotDown, int rotUp, int scaleDown, int scaleUp) {
        this.xUp = xUp;
        this.xDown = xDown;
        this.rotUp = rotUp;
        this.rotDown = rotDown;
        this.scaleUp = scaleUp;
        this.scaleDown = scaleDown;
    }
}
