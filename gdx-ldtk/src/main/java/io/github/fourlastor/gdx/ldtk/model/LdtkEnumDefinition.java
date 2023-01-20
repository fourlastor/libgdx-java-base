package io.github.fourlastor.gdx.ldtk.model;

import com.badlogic.gdx.utils.JsonValue;
import io.github.fourlastor.gdx.ldtk.LdtkParser;
import java.util.List;
import javax.inject.Inject;
import org.jetbrains.annotations.Nullable;

public class LdtkEnumDefinition {

    @Nullable
    public final String externalFileChecksum;
    /**
     * Relative path to the external file providing this Enum
     */
    @Nullable
    public final String externalRelPath;

    /**
     * Tileset UID if provided
     */
    @Nullable
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
            @Nullable String externalFileChecksum,
            @Nullable String externalRelPath,
            @Nullable Integer iconTilesetUid,
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

    public static class Parser extends LdtkParser<LdtkEnumDefinition> {

        private final LdtkParser<LdtkEnumValueDefinition> valuesParser;

        @Inject
        public Parser(LdtkParser<LdtkEnumValueDefinition> valuesParser) {
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
