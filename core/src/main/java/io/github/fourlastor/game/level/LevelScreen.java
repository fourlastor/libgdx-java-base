package io.github.fourlastor.game.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.CpuSpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Affine2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import javax.inject.Inject;

public class LevelScreen extends ScreenAdapter {

    private final Viewport viewport;
    private final CpuSpriteBatch batch;
    private final Element parent;
    private final Element child;

    @Inject
    public LevelScreen(CpuSpriteBatch batch, TextureAtlas atlas) {
        this.viewport = new FitViewport(160, 90);
        this.batch = batch;
        TextureAtlas.AtlasRegion whitePixel = atlas.findRegion("whitePixel");
        parent = new Element(whitePixel, Color.WHITE);
        parent.local.setToTranslation(160f / 2, 90f / 2);
        parent.local.setToScaling(10f, 10f);
        child = new Element(whitePixel, Color.RED);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void render(float delta) {
        float parentX = 0f;
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            parentX -= 1f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            parentX += 1f;
        }
        float parentRotation = 0f;
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            parentRotation -= 1f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            parentRotation += 1f;
        }
        float parentScale = 1f;
        if (Gdx.input.isKeyPressed(Input.Keys.Z)) {
            parentScale -= 0.1f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.X)) {
            parentScale += 0.1f;
        }

        parent.local.scale(parentScale, parentScale);
        parent.local.rotate(parentRotation);
        parent.local.translate(parentX * delta, 0);
        float childX = 0f;
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            childX -= 1f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.F)) {
            childX += 1f;
        }

        float childRotation = 0f;
        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            childRotation -= 1f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.R)) {
            childRotation += 1f;
        }
        float childScale = 1f;
        if (Gdx.input.isKeyPressed(Input.Keys.C)) {
            childScale -= 0.1f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.V)) {
            childScale += 0.1f;
        }

        child.local.scale(childScale, childScale);
        child.local.rotate(childRotation);
        child.local.translate(childX * delta, 0);

        parent.followParent(null);
        child.followParent(parent.global);

        ScreenUtils.clear(Color.GRAY);

        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        parent.draw(batch);
        child.draw(batch);
        batch.end();
    }

    static class Element {

        private static final Color COLOR_TMP = new Color();

        final TextureRegion region;
        final Color color;
        final Affine2 local = new Affine2();
        final Affine2 global = new Affine2();

        Element(TextureRegion region, Color color) {
            this.region = region;
            this.color = color;
        }

        public void followParent(Affine2 parentGlobal) {
            if (parentGlobal == null) {
                global.set(local);
            } else {
                global.set(parentGlobal).mul(local);
            }
        }

        public void draw(CpuSpriteBatch batch) {
            COLOR_TMP.set(batch.getColor());
            batch.setColor(color);
            batch.setTransformMatrix(global);
            batch.draw(region, -region.getRegionWidth() / 2f, -region.getRegionHeight() / 2f);
            batch.setColor(COLOR_TMP);
        }
    }
}
