package io.github.fourlastor.game.level;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import dagger.Module;
import dagger.Provides;
import io.github.fourlastor.game.di.ScreenScoped;
import io.github.fourlastor.harlequin.animation.Animation;
import io.github.fourlastor.harlequin.animation.GdxAnimation;
import javax.inject.Named;

@Module
public class PlayerAnimationsFactory {

    public static final String ANIMATION_STANDING = "player/OnGround/OnGround";
    public static final String ANIMATION_FALLING = "player/Falling/Falling";
    public static final String ANIMATION_JUMPING = "player/Jumping/Jumping";
    public static final String ANIMATION_FISH = "enemies/wigglingFish/wigglingFish";
    public static final String ANIMATION_CHARGE_JUMP = "player/ChargeJump/ChargeJump";
    private static final float FRAME_DURATION = 0.1f;

    @Provides
    @ScreenScoped
    @Named(ANIMATION_FISH)
    public Animation<Drawable> fish(TextureAtlas textureAtlas) {
        return new GdxAnimation<>(0.1f, wrap(textureAtlas.findRegions(ANIMATION_FISH)), Animation.PlayMode.LOOP);
    }

    @Provides
    @ScreenScoped
    @Named(ANIMATION_STANDING)
    public Animation<Drawable> standing(TextureAtlas textureAtlas) {
        return new GdxAnimation<>(
                FRAME_DURATION, wrap(textureAtlas.findRegions(ANIMATION_STANDING)), Animation.PlayMode.LOOP);
    }

    @Provides
    @ScreenScoped
    @Named(ANIMATION_FALLING)
    public Animation<Drawable> falling(TextureAtlas textureAtlas) {
        return new GdxAnimation<>(
                FRAME_DURATION, wrap(textureAtlas.findRegions(ANIMATION_FALLING)), Animation.PlayMode.NORMAL);
    }

    @Provides
    @ScreenScoped
    @Named(ANIMATION_JUMPING)
    public Animation<Drawable> jumping(TextureAtlas textureAtlas) {
        return new GdxAnimation<>(
                FRAME_DURATION, wrap(textureAtlas.findRegions(ANIMATION_JUMPING)), Animation.PlayMode.NORMAL);
    }

    @Provides
    @ScreenScoped
    @Named(ANIMATION_CHARGE_JUMP)
    public Animation<Drawable> chargeJump(TextureAtlas textureAtlas) {
        return new GdxAnimation<>(
                FRAME_DURATION, wrap(textureAtlas.findRegions(ANIMATION_CHARGE_JUMP)), Animation.PlayMode.NORMAL);
    }

    private Array<Drawable> wrap(Array<? extends TextureRegion> regions) {
        Array<Drawable> frames = new Array<>(regions.size);
        for (int i = 0; i < regions.size; i++) {
            frames.add(new TextureRegionDrawable(regions.get(i)));
        }
        return frames;
    }
}
