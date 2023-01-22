package io.github.fourlastor.harlequin.loader.dragonbones.model;

import com.badlogic.gdx.utils.JsonValue;
import io.github.fourlastor.json.JsonParser;
import javax.inject.Inject;

public class DragonBonesBone {

    public final String name;

    public DragonBonesBone(String name) {
        this.name = name;
    }

    public static class Parser extends JsonParser<DragonBonesBone> {
        @Inject
        public Parser() {}

        @Override
        public DragonBonesBone parse(JsonValue value) {
            return new DragonBonesBone(value.getString("name"));
        }
    }
}
