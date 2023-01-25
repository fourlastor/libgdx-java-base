package io.github.fourlastor.game.level.input.state;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import io.github.fourlastor.game.level.PlayerAnimationsFactory;
import io.github.fourlastor.game.level.component.AnimatedImageComponent;
import io.github.fourlastor.game.level.component.BodyComponent;
import io.github.fourlastor.game.level.component.PlayerComponent;
import io.github.fourlastor.harlequin.animation.Animation;
import javax.inject.Inject;
import javax.inject.Named;

public class OnGround extends InputState {

    private final Animation<Drawable> animation;
    private AssetManager assetManager;

    @Inject
    public OnGround(
            ComponentMapper<PlayerComponent> players,
            ComponentMapper<BodyComponent> bodies,
            ComponentMapper<AnimatedImageComponent> images,
            @Named(PlayerAnimationsFactory.ANIMATION_STANDING) Animation<Drawable> animation,
            AssetManager assetManager) {
        super(players, bodies, images);
        this.animation = animation;
        this.assetManager = assetManager;
    }

    @Override
    protected Animation<Drawable> animation() {
        return animation;
    }

    @Override
    public void enter(Entity entity) {
        super.enter(entity);
        playRandomSounds(assetManager);
    }

    @Override
    public boolean keyDown(Entity entity, int keycode) {
        if (keycode == Input.Keys.SPACE) {
            PlayerComponent player = players.get(entity);
            player.stateMachine.changeState(player.chargeJump);
            return true;
        }
        return super.keyDown(entity, keycode);
    }

    @Override
    public boolean touchDown(Entity entity, int screenX, int screenY, int pointer, int button) {
        PlayerComponent player = players.get(entity);
        player.stateMachine.changeState(player.chargeJump);
        return true;
    }

    private void playRandomSounds(AssetManager assetManager) {
        int random = MathUtils.random(0, 4);
        Sound playerSound = assetManager.get("audio/sounds/onGround/onGround_" + random + ".wav", Sound.class);
        playerSound.play();

        Sound grateSound = assetManager.get("audio/sounds/grateSound.wav", Sound.class);
        grateSound.play(1, MathUtils.random(.7f, 1.3f), 0);

        Sound splatSound = assetManager.get("audio/sounds/445109__breviceps__mud-splat.wav", Sound.class);
        splatSound.play(.3f, MathUtils.random(.7f, 1.3f), 0);
    }
}
