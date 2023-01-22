package io.github.fourlastor.ldtk.model;

import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Null;
import io.github.fourlastor.json.JsonParser;
import java.util.List;
import javax.inject.Inject;

public class LdtkEnumDefinition {

    @Null
    public final String externalFileChecksum;
    /**
     * Relative path to the external file providing this Enum
     */
    @Null
    public final String externalRelPath;

    /**
     * Tileset UID if provided
     */
    @Null
    public final Integer iconTilesetUid;
    /**
     * Unique String identifier
     */
    public final String identifier;
    /**
     * Unique Int identifier
     */
    public final int uid;

    /**
     * A list of user-defined tags to organize the Enums
     */
    public final List<String> tags;
    /**
     * All possible enum values, with their optional Tile info.
     */
    public final List<LdtkEnumValueDefinition> values;

    public LdtkEnumDefinition(
            @Null String externalFileChecksum,
            @Null String externalRelPath,
            @Null Integer iconTilesetUid,
            String identifier,
            int uid,
            List<String> tags,
            List<LdtkEnumValueDefinition> values) {
        this.externalFileChecksum = externalFileChecksum;
        this.externalRelPath = externalRelPath;
        this.iconTilesetUid = iconTilesetUid;
        this.identifier = identifier;
        this.uid = uid;
        this.tags = tags;
        this.values = values;
    }

    public static class Parser extends JsonParser<LdtkEnumDefinition> {

        private final JsonParser<LdtkEnumValueDefinition> valuesParser;

        @Inject
        public Parser(JsonParser<LdtkEnumValueDefinition> valuesParser) {
            this.valuesParser = valuesParser;
        }

        @Override
        public LdtkEnumDefinition parse(JsonValue value) {
            return new LdtkEnumDefinition(
                    value.getString("externalFileChecksum", null),
                    value.getString("externalRelPath", null),
                    getOptionalInt(value, "iconTilesetUid"),
                    value.getString("identifier"),
                    value.getInt("uid"),
                    getStringList(value.get("tags")),
                    getList(value.get("values"), valuesParser::parse));
        }
    }
}
