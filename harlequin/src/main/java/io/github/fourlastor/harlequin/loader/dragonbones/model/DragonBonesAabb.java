package io.github.fourlastor.harlequin.loader.dragonbones.model;

import com.badlogic.gdx.utils.JsonValue;
import io.github.fourlastor.json.JsonParser;
import javax.inject.Inject;

public class DragonBonesAabb {

    public final int x;
    public final int y;
    public final int width;
    public final int height;

    public DragonBonesAabb(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public static class Parser extends JsonParser<DragonBonesAabb> {

        @Inject
        public Parser() {}

        @Override
        public DragonBonesAabb parse(JsonValue value) {
            return new DragonBonesAabb(
                    value.getInt("x"), value.getInt("y"), value.getInt("width"), value.getInt("height"));
        }
    }
}
