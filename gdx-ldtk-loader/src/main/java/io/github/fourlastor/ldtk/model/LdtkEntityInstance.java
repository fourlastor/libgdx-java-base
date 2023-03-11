package io.github.fourlastor.ldtk.model;

import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Null;
import io.github.fourlastor.json.JsonParser;
import java.util.List;
import javax.inject.Inject;

public class LdtkEntityInstance {

    /**
     * Grid-based coordinates (`[x,y]` format)
     */
    public final IntArray grid;

    /**
     * Entity definition identifier
     */
    public final String identifier;

    /**
     * Pivot coordinates (`[x,y]` format, values are from 0 to 1) of the Entity
     */
    public final FloatArray pivot;

    /**
     * Optional Tile used to display this entity (it could either be the default Entity tile, or
     * some tile provided by a field value, like an Enum).
     */
    @Null
    public final LdtkTileRect tile;

    /**
     * Reference of the **Entity definition** UID
     */
    public final int defUid;

    /**
     * An array of all custom fields and their values.
     */
    public final List<LdtkFieldInstance> fieldInstances;

    /**
     * Entity height in pixels. For non-resizable entities, it will be the same as Entity
     * definition.
     */
    public final int height;

    /**
     * Pixel coordinates (`[x,y]` format) in current level coordinate space. Don't forget optional
     * layer offsets, if they exist!
     */
    public final IntArray px;

    public int x() {
        return px.get(0);
    }

    public int y(int levelHeight, int gridSize) {
        return (levelHeight - 1) * gridSize - px.get(1);
    }

    public float halfWidth() {
        return width / 2f;
    }

    public float halfHeight() {
        return height / 2f;
    }

    public final LdtkFieldInstance field(String fieldName) {
        for (LdtkFieldInstance fieldInstance : fieldInstances) {
            if (fieldInstance.identifier.equals(fieldName)) {
                return fieldInstance;
            }
        }
        throw new IndexOutOfBoundsException("Field " + fieldName + " missing");
    }

    /**
     * Entity width in pixels. For non-resizable entities, it will be the same as Entity definition.
     */
    public final int width;

    /**
     * List of tags defined
     */
    public final List<String> tags;

    /**
     * Unique instance identifier
     */
    public final String iid;

    public LdtkEntityInstance(
            IntArray grid,
            String identifier,
            FloatArray pivot,
            @Null LdtkTileRect tile,
            int defUid,
            List<LdtkFieldInstance> fieldInstances,
            int height,
            IntArray px,
            int width,
            List<String> tags,
            String iid) {
        this.grid = grid;
        this.identifier = identifier;
        this.pivot = pivot;
        this.tile = tile;
        this.defUid = defUid;
        this.fieldInstances = fieldInstances;
        this.height = height;
        this.px = px;
        this.width = width;
        this.tags = tags;
        this.iid = iid;
    }

    public static class Parser extends JsonParser<LdtkEntityInstance> {
        private final JsonParser<LdtkTileRect> tileParser;
        private final JsonParser<LdtkFieldInstance> fieldInstancesParser;

        @Inject
        public Parser(JsonParser<LdtkTileRect> tileParser, JsonParser<LdtkFieldInstance> fieldInstancesParser) {
            this.tileParser = tileParser;
            this.fieldInstancesParser = fieldInstancesParser;
        }

        @Override
        public LdtkEntityInstance parse(JsonValue value) {
            return new LdtkEntityInstance(
                    getIntArray(value.get("__grid")),
                    value.getString("__identifier"),
                    getFloatArray(value.get("__pivot")),
                    getOptional(value, "__tile", tileParser::parse),
                    value.getInt("defUid"),
                    getList(value.get("fieldInstances"), fieldInstancesParser::parse),
                    value.getInt("height"),
                    getIntArray(value.get("px")),
                    value.getInt("width"),
                    getList(value.get("__tags"), JsonValue::asString),
                    value.getString("iid"));
        }
    }
}
