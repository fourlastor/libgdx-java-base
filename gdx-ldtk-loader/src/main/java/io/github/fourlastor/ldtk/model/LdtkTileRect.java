package io.github.fourlastor.ldtk.model;

import com.badlogic.gdx.utils.JsonValue;
import io.github.fourlastor.json.JsonParser;
import javax.inject.Inject;

public class LdtkTileRect {

    /**
     * Tileset UID
     */
    public final int tilesetUid;

    /**
     * Height in pixels.
     */
    public final int h;

    /**
     * Width in pixels.
     */
    public final int w;

    /**
     * X pixel coordinate relative to top-left corner of the tileset image
     */
    public final int x;

    /**
     * Y pixel coordinate relative to top-left corner of the tileset image
     */
    public final int y;

    LdtkTileRect(int tilesetUid, int h, int w, int x, int y) {
        this.tilesetUid = tilesetUid;
        this.h = h;
        this.w = w;
        this.x = x;
        this.y = y;
    }

    public static class Parser extends JsonParser<LdtkTileRect> {
        @Inject
        public Parser() {}

        @Override
        public LdtkTileRect parse(JsonValue value) {
            return new LdtkTileRect(
                    value.getInt("tilesetUid"),
                    value.getInt("h"),
                    value.getInt("w"),
                    value.getInt("x"),
                    value.getInt("y"));
        }
    }
}
