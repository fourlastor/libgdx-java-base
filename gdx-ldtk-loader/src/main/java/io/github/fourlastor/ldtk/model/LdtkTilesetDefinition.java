package io.github.fourlastor.ldtk.model;

import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Null;
import io.github.fourlastor.json.JsonParser;
import java.util.List;
import javax.inject.Inject;

/**
 * The `Tileset` definition is the most important part among project definitions. It contains some
 * extra info about each integrated tileset. If you only had to parse one definition section, that
 * would be the one.
 */
public class LdtkTilesetDefinition {
    /**
     * Unique String identifier
     */
    public final String identifier;

    /**
     * Distance in pixels from image borders
     */
    public final int padding;

    /**
     * Image height in pixels
     */
    public final int pxHei;

    /**
     * Image width in pixels
     */
    public final int pxWid;

    /**
     * Path to the source file, relative to the current project JSON file
     */
    public final String relPath;

    /**
     * Space in pixels between all tiles
     */
    public final int spacing;

    public final int tileGridSize;

    /**
     * Unique Int identifier
     */
    public final int uid;

    /**
     * If this value is set, then it means that this atlas uses an internal LDtk atlas image instead
     * of a loaded one.
     * Possible values: `null`, `LdtkIcons`
     */
    @Null
    public final String embedAtlas;

    public final List<LdtkTilesetCustomData> customData;

    public LdtkTilesetDefinition(
            String identifier,
            int padding,
            int pxHei,
            int pxWid,
            String relPath,
            int spacing,
            int tileGridSize,
            int uid,
            @Null String embedAtlas,
            List<LdtkTilesetCustomData> customData) {
        this.identifier = identifier;
        this.padding = padding;
        this.pxHei = pxHei;
        this.pxWid = pxWid;
        this.relPath = relPath;
        this.spacing = spacing;
        this.tileGridSize = tileGridSize;
        this.uid = uid;
        this.embedAtlas = embedAtlas;
        this.customData = customData;
    }

    public LdtkTilesetCustomData customData(int tileId) {
        for (LdtkTilesetCustomData data : customData) {
            if (data.tileId == tileId) {
                return data;
            }
        }
        throw new IndexOutOfBoundsException("No custom data for tileId " + tileId + " found");
    }

    public static class Parser extends JsonParser<LdtkTilesetDefinition> {

        private final JsonParser<LdtkTilesetCustomData> customDataParser;

        @Inject
        public Parser(JsonParser<LdtkTilesetCustomData> customDataParser) {
            this.customDataParser = customDataParser;
        }

        @Override
        public LdtkTilesetDefinition parse(JsonValue value) {
            return new LdtkTilesetDefinition(
                    value.getString("identifier"),
                    value.getInt("padding"),
                    value.getInt("pxHei"),
                    value.getInt("pxWid"),
                    value.getString("relPath"),
                    value.getInt("spacing"),
                    value.getInt("tileGridSize"),
                    value.getInt("uid"),
                    value.getString("embedAtlas", null),
                    getList(value.get("customData"), customDataParser::parse));
        }
    }
}
