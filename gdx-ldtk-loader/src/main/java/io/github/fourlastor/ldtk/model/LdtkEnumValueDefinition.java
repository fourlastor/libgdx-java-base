package io.github.fourlastor.ldtk.model;

import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.JsonValue;
import io.github.fourlastor.json.JsonParser;
import javax.inject.Inject;

public class LdtkEnumValueDefinition {
    /**
     * An array of 4 Int values that refers to the tile in the tileset image: `[ x, y, width, height
     * ]`
     */
    public final IntArray tileSrcRect;

    /**
     * Enum value
     */
    public final String id;

    /**
     * The optional ID of the tile
     */
    public final Integer tileId;

    /**
     * The color value of the enum value
     */
    public final int color;

    public LdtkEnumValueDefinition(IntArray tileSrcRect, String id, Integer tileId, int color) {
        this.tileSrcRect = tileSrcRect;
        this.id = id;
        this.tileId = tileId;
        this.color = color;
    }

    public static class Parser extends JsonParser<LdtkEnumValueDefinition> {
        @Inject
        public Parser() {}

        @Override
        public LdtkEnumValueDefinition parse(JsonValue value) {
            return new LdtkEnumValueDefinition(
                    getIntArray(value.get("__tileSrcRect")),
                    value.getString("id"),
                    value.getInt("tileId"),
                    value.getInt("color"));
        }
    }
}
