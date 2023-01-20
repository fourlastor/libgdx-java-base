package io.github.fourlastor.gdx.ldtk.model;

import com.badlogic.gdx.utils.JsonValue;
import io.github.fourlastor.gdx.ldtk.LdtkParser;
import javax.inject.Inject;
import org.jetbrains.annotations.Nullable;

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
    public final String type;

    /**
     * Actual value of the field instance. The value type may vary, depending on `__type` (Integer,
     * Boolean, String etc.)<br/> It can also be an `Array` of those same types.
     * SerialName("__value")
     */
    @Nullable
    public final MultiAssociatedValue value;

    /**
     * Reference of the **Field definition** UID
     */
    public final int defUid;

    /**
     * Optional TilesetRect used to display this field (this can be the field own Tile, or some
     * other Tile guessed from the value, like an Enum)
     * SerialName("__tile")
     */
    @Nullable
    public final LdtkTileRect tile;

    public LdtkFieldInstance(
            String identifier,
            String type,
            @Nullable MultiAssociatedValue value,
            int defUid,
            @Nullable LdtkTileRect tile) {
        this.identifier = identifier;
        this.type = type;
        this.value = value;
        this.defUid = defUid;
        this.tile = tile;
    }

    public static class Parser extends LdtkParser<LdtkFieldInstance> {
        private final LdtkParser<MultiAssociatedValue> valueParser;
        private final LdtkParser<LdtkTileRect> tileParser;

        @Inject
        public Parser(LdtkParser<MultiAssociatedValue> valueParser, LdtkParser<LdtkTileRect> tileParser) {
            this.valueParser = valueParser;
            this.tileParser = tileParser;
        }

        @Override
        public LdtkFieldInstance parse(JsonValue value) {
            return new LdtkFieldInstance(
                    value.getString("identifier"),
                    value.getString("type"),
                    getOptional(value, "value", valueParser::parse),
                    value.getInt("defUid"),
                    getOptional(value, "tile", tileParser::parse));
        }
    }
}
