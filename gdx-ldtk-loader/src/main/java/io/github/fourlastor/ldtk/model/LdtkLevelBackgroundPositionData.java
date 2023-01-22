package io.github.fourlastor.ldtk.model;

import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.JsonValue;
import io.github.fourlastor.json.JsonParser;
import javax.inject.Inject;

/**
 * Level background image position info
 */
public class LdtkLevelBackgroundPositionData {
    /**
     * An array of 4 float values describing the cropped sub-rectangle of the displayed background
     * image. This cropping happens when original is larger than the level bounds. Array format: `[
     * cropX, cropY, cropWidth, cropHeight ]`
     */
    public final FloatArray cropRect;

    /**
     * An array containing the `[scaleX,scaleY]` values of the **cropped** background image,
     * depending on `bgPos` option.
     */
    public final FloatArray scale;

    /**
     * An array containing the `[x,y]` pixel coordinates of the top-left corner of the **cropped**
     * background image, depending on `bgPos` option.
     */
    public final IntArray topLeftPx;

    public LdtkLevelBackgroundPositionData(FloatArray cropRect, FloatArray scale, IntArray topLeftPx) {
        this.cropRect = cropRect;
        this.scale = scale;
        this.topLeftPx = topLeftPx;
    }

    public static class Parser extends JsonParser<LdtkLevelBackgroundPositionData> {

        @Inject
        public Parser() {}

        @Override
        public LdtkLevelBackgroundPositionData parse(JsonValue value) {
            return new LdtkLevelBackgroundPositionData(
                    getFloatArray(value.get("cropRect")),
                    getFloatArray(value.get("scale")),
                    getIntArray(value.get("topLeftPx")));
        }
    }
}
