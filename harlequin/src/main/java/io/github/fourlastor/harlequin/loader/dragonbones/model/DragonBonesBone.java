package io.github.fourlastor.harlequin.loader.dragonbones.model;

import com.badlogic.gdx.utils.JsonValue;
import io.github.fourlastor.json.JsonParser;
import javax.inject.Inject;

public class DragonBonesBone {

    public final String name;
    public final DragonBonesTransform transform;

    public DragonBonesBone(String name, DragonBonesTransform transform) {
        this.name = name;
        this.transform = transform;
    }

    public static class Parser extends JsonParser<DragonBonesBone> {

        private final JsonParser<DragonBonesTransform> transformParser;

        @Inject
        public Parser(JsonParser<DragonBonesTransform> transformParser) {
            this.transformParser = transformParser;
        }

        @Override
        public DragonBonesBone parse(JsonValue value) {
            return new DragonBonesBone(value.getString("name"), transformParser.parse(value.get("transform")));
        }
    }
}
