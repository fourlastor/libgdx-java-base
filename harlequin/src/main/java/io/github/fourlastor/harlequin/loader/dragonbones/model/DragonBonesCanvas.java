package io.github.fourlastor.harlequin.loader.dragonbones.model;

import com.badlogic.gdx.utils.JsonValue;
import io.github.fourlastor.json.JsonParser;
import javax.inject.Inject;

public class DragonBonesCanvas {

    public final int width;
    public final int height;

    public DragonBonesCanvas(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public static class Parser extends JsonParser<DragonBonesCanvas> {
        @Inject
        public Parser() {}

        @Override
        public DragonBonesCanvas parse(JsonValue value) {
            return new DragonBonesCanvas(value.getInt("width"), value.getInt("height"));
        }
    }
}
