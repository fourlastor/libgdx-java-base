package io.github.fourlastor.game.level.component;

import com.badlogic.ashley.core.Component;
import io.github.fourlastor.game.level.input.InputStateMachine;

/**
 * Bag containing the player state machine, and the possible states it can get into.
 */
public class PlayerComponent implements Component {
    public final InputStateMachine stateMachine;

    public PlayerComponent(InputStateMachine stateMachine) {
        this.stateMachine = stateMachine;
    }
}
