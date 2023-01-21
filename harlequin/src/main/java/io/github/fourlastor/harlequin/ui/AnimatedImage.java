package io.github.fourlastor.harlequin.ui;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.IntMap;
import io.github.fourlastor.harlequin.animation.Animation;

public class AnimatedImage extends Image {

    private IntMap<TextureRegionDrawable> frameCache;
    private Animation<TextureRegion> animation;

    public boolean playing = true;
    public float playTime = 0f;

    public AnimatedImage(Animation<TextureRegion> animation) {
        super(animation.getKeyFrame(0));
        setAnimation(animation);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (!playing) {
            return;
        }
        playTime += delta;
        TextureRegionDrawable frame = frameAt(playTime);
        setDrawable(frame);
    }

    private TextureRegionDrawable frameAt(float position) {
        int index = animation.getKeyFrameIndex(position);
        TextureRegionDrawable cached = frameCache.get(index);
        if (cached == null) {
            cached = new TextureRegionDrawable(animation.getKeyFrame(position));
            frameCache.put(index, cached);
        }
        return cached;
    }

    public void setAnimation(Animation<TextureRegion> animation) {
        this.animation = animation;
        @SuppressWarnings("UnnecessaryLocalVariable") // for some reason it crashes if using the typed var
        Animation<?> untypedAnimation = animation;
        this.frameCache = new IntMap<>(untypedAnimation.getKeyFrames().length);
        reset();
    }

    public boolean animationFinished() {
        return animation.isAnimationFinished(playTime);
    }

    public void setPlayMode(Animation.PlayMode playMode) {
        animation.setPlayMode(playMode);
    }

    public void setProgress(float progress) {
        this.playTime = progress;
        setDrawable(frameAt(progress));
    }

    public void reset() {
        setProgress(0f);
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }
}
