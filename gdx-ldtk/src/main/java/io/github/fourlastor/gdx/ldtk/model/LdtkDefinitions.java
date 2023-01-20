package io.github.fourlastor.gdx.ldtk.model;

import com.badlogic.gdx.utils.JsonValue;
import io.github.fourlastor.gdx.ldtk.LdtkParser;
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

    public static class Parser extends LdtkParser<LdtkDefinitions> {

        private final LdtkParser<LdtkEnumDefinition> enumsParser;
        private final LdtkParser<LdtkTilesetDefinition> tilesetsParser;

        @Inject
        public Parser(LdtkParser<LdtkEnumDefinition> enumsParser, LdtkParser<LdtkTilesetDefinition> tilesetsParser) {
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
