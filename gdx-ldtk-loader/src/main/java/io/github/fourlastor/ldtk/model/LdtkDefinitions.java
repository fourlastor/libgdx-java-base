package io.github.fourlastor.ldtk.model;

import com.badlogic.gdx.utils.JsonValue;
import io.github.fourlastor.json.JsonParser;
import java.util.List;
import javax.inject.Inject;

/**
 * A structure containing all the definitions of this project
 * If you're writing your own LDtk importer, you should probably just ignore *most* stuff in the
 * `defs` section, as it contains data that are mostly important to the editor. To keep you away
 * from the `defs` section and avoid some unnecessary JSON parsing, important data from definitions
 * is often duplicated in fields prefixed with a double underscore (e.g. `__identifier` or `__type`).
 * The 2 only definition types you might need here are **Tilesets** and **Enums**.
 */
public class LdtkDefinitions {
    public final List<LdtkEnumDefinition> enums;
    public final List<LdtkTilesetDefinition> tilesets;

    public LdtkDefinitions(List<LdtkEnumDefinition> enums, List<LdtkTilesetDefinition> tilesets) {
        this.enums = enums;
        this.tilesets = tilesets;
    }

    public LdtkTilesetDefinition tileset(int uid) {
        for (LdtkTilesetDefinition tileset : tilesets) {
            if (uid == tileset.uid) {
                return tileset;
            }
        }
        throw new IndexOutOfBoundsException("No tileset with uid " + uid + "found");
    }

    public static class Parser extends JsonParser<LdtkDefinitions> {

        private final JsonParser<LdtkEnumDefinition> enumsParser;
        private final JsonParser<LdtkTilesetDefinition> tilesetsParser;

        @Inject
        public Parser(JsonParser<LdtkEnumDefinition> enumsParser, JsonParser<LdtkTilesetDefinition> tilesetsParser) {
            this.enumsParser = enumsParser;
            this.tilesetsParser = tilesetsParser;
        }

        @Override
        public LdtkDefinitions parse(JsonValue value) {
            return new LdtkDefinitions(
                    getList(value.get("enums"), enumsParser::parse),
                    getList(value.get("tilesets"), tilesetsParser::parse));
        }
    }
}
