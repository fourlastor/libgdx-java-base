package io.github.fourlastor.ldtk.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Null;
import io.github.fourlastor.json.JsonParser;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class LdtkFieldInstance {
    /**
     * Field definition identifier
     * SerialName("__identifier")
     */
    public final String identifier;

    /**
     * Type of the field, such as `Int`, `Float`, `Enum(my_enum_name)`, `Bool`, etc.
     * SerialName("__type")
     */
    public final Type type;

    public final float floatValue;
    public final boolean booleanValue;
    public final List<Vector2> vectorArrayValue;

    /**
     * Reference of the **Field definition** UID
     */
    public final int defUid;

    /**
     * Optional TilesetRect used to display this field (this can be the field own Tile, or some
     * other Tile guessed from the value, like an Enum)
     * SerialName("__tile")
     */
    @Null
    public final LdtkTileRect tile;

    public LdtkFieldInstance(
            String identifier,
            Type type,
            float floatValue,
            boolean booleanValue,
            List<Vector2> vectorArrayValue,
            int defUid,
            @Null LdtkTileRect tile) {
        this.identifier = identifier;
        this.type = type;
        this.floatValue = floatValue;
        this.booleanValue = booleanValue;
        this.vectorArrayValue = vectorArrayValue;
        this.defUid = defUid;
        this.tile = tile;
    }

    public static class Parser extends JsonParser<LdtkFieldInstance> {
        private final JsonParser<LdtkTileRect> tileParser;

        @Inject
        public Parser(JsonParser<LdtkTileRect> tileParser) {
            this.tileParser = tileParser;
        }

        @Override
        public LdtkFieldInstance parse(JsonValue value) {
            String stringType = value.getString("__type");
            boolean booleanVal = false;
            float floatVal = 0f;
            List<Vector2> vectorArray = null;
            Type type = Type.UNKNOWN;
            switch (stringType) {
                case "Bool":
                    booleanVal = value.get("__value").asBoolean();
                    type = Type.BOOLEAN;
                    break;
                case "Float":
                    floatVal = value.get("__value").asFloat();
                    type = Type.FLOAT;
                    break;
                case "Array<Point>":
                    vectorArray = points(value.get("__value"));
                    type = Type.VECTOR2_ARRAY;
                    break;
            }
            return new LdtkFieldInstance(
                    value.getString("__identifier"),
                    type,
                    floatVal,
                    booleanVal,
                    vectorArray,
                    value.getInt("defUid"),
                    getOptional(value, "__tile", tileParser::parse));
        }

        private List<Vector2> points(JsonValue value) {
            List<Vector2> points = new ArrayList<>(value.size);
            for (JsonValue point : value) {
                points.add(new Vector2(point.getFloat("cx"), point.getFloat("cy")));
            }
            return points;
        }
    }

    public enum Type {
        FLOAT,
        BOOLEAN,
        VECTOR2_ARRAY,
        UNKNOWN
    }
}
