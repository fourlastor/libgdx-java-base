package io.github.fourlastor.harlequin.loader.spine.model;

import com.badlogic.gdx.utils.JsonValue;
import io.github.fourlastor.json.JsonParser;
import javax.inject.Inject;

public class SpineSkeleton {
    public final int width;
    public final int height;

    public SpineSkeleton(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public static class Parser extends JsonParser<SpineSkeleton> {

        @Inject
        public Parser() {}

        @Override
        public SpineSkeleton parse(JsonValue value) {
            return new SpineSkeleton(value.getInt("width"), value.getInt("height"));
        }
    }
}
