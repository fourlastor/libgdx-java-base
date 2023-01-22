package io.github.fourlastor.ldtk.model;

import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Null;
import io.github.fourlastor.json.JsonParser;
import java.util.List;
import javax.inject.Inject;

/**
 * This section contains all the level data. It can be found in 2 distinct forms, depending on
 * Project current settings: - If "*Separate level files*" is **disabled** (default): full level
 * data is *embedded* inside the main Project JSON file, - If "*Separate level files*" is
 * **enabled**: level data is stored in *separate* standalone `.ldtkl` files (one per level). In
 * this case, the main Project JSON file will still contain most level data, except heavy sections,
 * like the `layerInstances` array (which will be null). The `externalRelPath` string points to the
 * `ldtkl` file. A `ldtkl` file is just a JSON file containing exactly what is described below.
 */
public class LdtkLevelDefinition {
    /**
     * Background color of the level (same as `bgColor`, except the default value is automatically
     * used here if its value is `null`)
     */
    public final String bgColor;

    /**
     * Position information of the background image, if there is one.
     */
    @Null
    public final LdtkLevelBackgroundPositionData bgPos;

    /**
     * An array listing all other levels touching this one on the world map. In "linear" world
     * layouts, this array is populated with previous/next levels in array, and `dir` depends on the
     * linear horizontal/vertical layout.
     */
    @Null
    public final List<LdtkNeighbourLevelData> neighbours;

    /**
     * Background image X pivot (0-1)
     */
    public final float bgPivotX;

    /**
     * Background image Y pivot (0-1)
     */
    public final float bgPivotY;

    /**
     * This value is not null if the project option "*Save levels separately*" is enabled. In this
     * case, this **relative** path points to the level Json file.
     */
    @Null
    public final String externalRelPath;

    /**
     *  An array containing this level custom field values.
     */
    public final List<LdtkFieldInstance> fieldInstances;

    /**
     * Unique String identifier
     */
    public final String identifier;

    /**
     * An array containing all Layer instances. **IMPORTANT**: if the project option "*Save levels
     * separately*" is enabled, this field will be `null`.<br/> This array is **sorted in display
     * order**: the 1st layer is the top-most and the last is behind.
     */
    @Null
    public final List<LdtkLayerInstance> layerInstances;

    /**
     * Height of the level in pixels
     */
    public final int pxHei;

    /**
     * Width of the level in pixels
     */
    public final int pxWid;

    /**
     * Unique Int identifier
     */
    public final int uid;

    /**
     * World X coordinate in pixels.
     * <p>
     * Only relevant for world layouts where level spatial positioning is manual (ie. GridVania,
     * Free). For Horizontal and Vertical layouts, the value is always -1 here.
     */
    public final int worldX;

    /**
     * World Y coordinate in pixels.
     * <p>
     * Only relevant for world layouts where level spatial positioning is manual (ie. GridVania,
     * Free). For Horizontal and Vertical layouts, the value is always -1 here.
     */
    public final int worldY;

    /**
     * Unique instance identifier
     */
    public final String iid;

    /**
     * Index that represents the "depth" of the level in the world. Default is 0, greater means
     * "above", lower means "below".
     * <p>
     * This value is mostly used for display only and is intended to make stacking of levels easier
     * to manage.
     */
    public final int worldDepth;

    public LdtkLevelDefinition(
            String bgColor,
            @Null LdtkLevelBackgroundPositionData bgPos,
            @Null List<LdtkNeighbourLevelData> neighbours,
            float bgPivotX,
            float bgPivotY,
            @Null String externalRelPath,
            List<LdtkFieldInstance> fieldInstances,
            String identifier,
            @Null List<LdtkLayerInstance> layerInstances,
            int pxHei,
            int pxWid,
            int uid,
            int worldX,
            int worldY,
            String iid,
            int worldDepth) {
        this.bgColor = bgColor;
        this.bgPos = bgPos;
        this.neighbours = neighbours;
        this.bgPivotX = bgPivotX;
        this.bgPivotY = bgPivotY;
        this.externalRelPath = externalRelPath;
        this.fieldInstances = fieldInstances;
        this.identifier = identifier;
        this.layerInstances = layerInstances;
        this.pxHei = pxHei;
        this.pxWid = pxWid;
        this.uid = uid;
        this.worldX = worldX;
        this.worldY = worldY;
        this.iid = iid;
        this.worldDepth = worldDepth;
    }

    public static class Parser extends JsonParser<LdtkLevelDefinition> {

        private final JsonParser<LdtkLevelBackgroundPositionData> bgPosParser;
        private final JsonParser<LdtkNeighbourLevelData> neighboursParser;
        private final JsonParser<LdtkFieldInstance> fieldInstancesParser;
        private final JsonParser<LdtkLayerInstance> layerInstancesParser;

        @Inject
        public Parser(
                JsonParser<LdtkLevelBackgroundPositionData> bgPosParser,
                JsonParser<LdtkNeighbourLevelData> neighboursParser,
                JsonParser<LdtkFieldInstance> fieldInstancesParser,
                JsonParser<LdtkLayerInstance> layerInstancesParser) {
            this.bgPosParser = bgPosParser;
            this.neighboursParser = neighboursParser;
            this.fieldInstancesParser = fieldInstancesParser;
            this.layerInstancesParser = layerInstancesParser;
        }

        @Override
        public LdtkLevelDefinition parse(JsonValue value) {
            return new LdtkLevelDefinition(
                    value.getString("__bgColor"),
                    getOptional(value, "__bgPos", bgPosParser::parse),
                    getOptional(value, "__neighbours", it -> getList(it, neighboursParser::parse)),
                    value.getFloat("bgPivotX"),
                    value.getFloat("bgPivotY"),
                    value.getString("externalRelPath", null),
                    getList(value.get("fieldInstances"), fieldInstancesParser::parse),
                    value.getString("identifier"),
                    getList(value.get("layerInstances"), layerInstancesParser::parse),
                    value.getInt("pxHei"),
                    value.getInt("pxWid"),
                    value.getInt("uid"),
                    value.getInt("worldX"),
                    value.getInt("worldY"),
                    value.getString("iid"),
                    value.getInt("worldDepth"));
        }
    }
}
