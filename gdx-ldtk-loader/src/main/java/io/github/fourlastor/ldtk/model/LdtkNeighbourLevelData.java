package io.github.fourlastor.ldtk.model;

import com.badlogic.gdx.utils.JsonValue;
import io.github.fourlastor.json.JsonParser;
import javax.inject.Inject;

/**
 * Nearby level info
 */
public class LdtkNeighbourLevelData {
    /**
     * A single lowercase character tipping on the level location (`n`orth, `s`outh, `w`est,
     * `e`ast).
     */
    public final String dir;

    /**
     * Neighbor instance identifier
     */
    public final String levelIid;

    LdtkNeighbourLevelData(String dir, String levelIid) {
        this.dir = dir;
        this.levelIid = levelIid;
    }

    public static class Parser extends JsonParser<LdtkNeighbourLevelData> {
        @Inject
        public Parser() {}

        @Override
        public LdtkNeighbourLevelData parse(JsonValue value) {
            return new LdtkNeighbourLevelData(value.getString("dir"), value.getString("levelIid"));
        }
    }
}
