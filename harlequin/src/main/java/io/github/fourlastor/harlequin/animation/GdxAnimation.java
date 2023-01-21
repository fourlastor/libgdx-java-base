package io.github.fourlastor.harlequin.animation;

import com.badlogic.gdx.utils.Array;

public class GdxAnimation<T> implements Animation<T> {

    private final com.badlogic.gdx.graphics.g2d.Animation<T> animation;

    public GdxAnimation(float frameDuration, Array<? extends T> keyFrames) {
        this(frameDuration, keyFrames, PlayMode.NORMAL);
    }

    public GdxAnimation(float frameDuration, Array<? extends T> keyFrames, PlayMode playMode) {
        this.animation = new com.badlogic.gdx.graphics.g2d.Animation<>(frameDuration, keyFrames, toGdx(playMode));
    }

    @Override
    public T getKeyFrame(float stateTime) {
        return animation.getKeyFrame(stateTime);
    }

    @Override
    public int getKeyFrameIndex(float stateTime) {
        return animation.getKeyFrameIndex(stateTime);
    }

    @Override
    public T[] getKeyFrames() {
        return animation.getKeyFrames();
    }

    @Override
    public PlayMode getPlayMode() {
        return fromGdx(animation.getPlayMode());
    }

    private PlayMode fromGdx(com.badlogic.gdx.graphics.g2d.Animation.PlayMode playMode) {
        switch (playMode) {
            case NORMAL:
                return PlayMode.NORMAL;
            case REVERSED:
                return PlayMode.REVERSED;
            case LOOP:
                return PlayMode.LOOP;
            case LOOP_REVERSED:
                return PlayMode.LOOP_REVERSED;
            case LOOP_PINGPONG:
                return PlayMode.LOOP_PINGPONG;
            case LOOP_RANDOM:
                return PlayMode.LOOP_RANDOM;
        }
        throw new IllegalArgumentException("Play mode not supported: " + playMode);
    }

    @Override
    public void setPlayMode(PlayMode playMode) {
        animation.setPlayMode(toGdx(playMode));
    }

    private com.badlogic.gdx.graphics.g2d.Animation.PlayMode toGdx(PlayMode playMode) {
        switch (playMode) {
            case NORMAL:
                return com.badlogic.gdx.graphics.g2d.Animation.PlayMode.NORMAL;
            case REVERSED:
                return com.badlogic.gdx.graphics.g2d.Animation.PlayMode.REVERSED;
            case LOOP:
                return com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP;
            case LOOP_REVERSED:
                return com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP_REVERSED;
            case LOOP_PINGPONG:
                return com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP_PINGPONG;
            case LOOP_RANDOM:
                return com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP_RANDOM;
        }
        throw new IllegalArgumentException("Play mode not supported: " + playMode);
    }

    @Override
    public boolean isAnimationFinished(float stateTime) {
        return animation.isAnimationFinished(stateTime);
    }

    @Override
    public float getAnimationDuration() {
        return animation.getAnimationDuration();
    }
}
