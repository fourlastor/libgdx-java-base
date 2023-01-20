package io.github.fourlastor.ldtk.model;

import com.badlogic.gdx.utils.JsonValue;
import io.github.fourlastor.ldtk.LdtkParser;
import java.util.List;
import javax.inject.Inject;

public class LdtkMapData {

    public final LdtkDefinitions defs;

    /**
     * All levels. The order of this array is only relevant in `LinearHorizontal` and
     * `linearVertical` world layouts (see `worldLayout` value). Otherwise, you should refer to the
     * `worldX`,`worldY` coordinates of each Level.
     */
    public final List<LdtkLevelDefinition> levels;

    public LdtkMapData(LdtkDefinitions defs, List<LdtkLevelDefinition> levels) {
        this.defs = defs;
        this.levels = levels;
    }

    public static class Parser extends LdtkParser<LdtkMapData> {

        private final LdtkParser<LdtkDefinitions> definitionsParser;
        private final LdtkParser<LdtkLevelDefinition> levelsParser;

        @Inject
        public Parser(LdtkParser<LdtkDefinitions> definitionsParser, LdtkParser<LdtkLevelDefinition> levelsParser) {
            this.definitionsParser = definitionsParser;
            this.levelsParser = levelsParser;
        }

        @Override
        public LdtkMapData parse(JsonValue value) {
            return new LdtkMapData(
                    definitionsParser.parse(value.get("defs")), getList(value.get("levels"), levelsParser::parse));
        }
    }
}
