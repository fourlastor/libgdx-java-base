package io.github.fourlastor.ldtk.model;

import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.JsonValue;
import io.github.fourlastor.json.JsonParser;
import javax.inject.Inject;

public class LdtkTileInstance {

    private static final int FLIP_X = 1;
    private static final int FLIP_Y = 2;

    /**
     * "Flip bits", a 2-bits integer to represent the mirror transformations of the tile.<br/>
     * - Bit 0 = X flip<br/> - Bit 1 = Y flip<br/>
     * <p>
     * Examples: f=0 (no flip), f=1 (X flip only), f=2 (Y flip only), f=3 (both flips)
     */
    public final int f;

    public boolean flipX() {
        return (f & FLIP_X) == FLIP_X;
    }

    public boolean flipY() {
        return (f & FLIP_Y) == FLIP_Y;
    }

    /**
     * Pixel coordinates of the tile in the **layer** (`[x,y]` format). Don't forget optional layer
     * offsets, if they exist!
     */
    public final IntArray px;

    public int x() {
        return px.get(0);
    }

    public int y(int levelHeight, int gridSize) {
        return (levelHeight - 1) * gridSize - px.get(1);
    }

    /**
     * Pixel coordinates of the tile in the **tileset** (`[x,y]` format)
     */
    public final IntArray src;

    /**
     * The *Tile ID* in the corresponding tileset.
     */
    public final int t;

    public LdtkTileInstance(int f, IntArray px, IntArray src, int t) {
        this.f = f;
        this.px = px;
        this.src = src;
        this.t = t;
    }

    public static class Parser extends JsonParser<LdtkTileInstance> {
        @Inject
        public Parser() {}

        @Override
        public LdtkTileInstance parse(JsonValue value) {
            return new LdtkTileInstance(
                    value.getInt("f"), getIntArray(value.get("px")), getIntArray(value.get("src")), value.getInt("t"));
        }
    }
}
