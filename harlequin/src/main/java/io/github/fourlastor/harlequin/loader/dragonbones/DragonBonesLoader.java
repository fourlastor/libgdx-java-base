package io.github.fourlastor.harlequin.loader.dragonbones;

import com.badlogic.gdx.utils.JsonReader;
import io.github.fourlastor.harlequin.loader.dragonbones.model.DragonBonesEntity;
import io.github.fourlastor.json.JsonLoader;
import io.github.fourlastor.json.JsonParser;
import javax.inject.Inject;

public class DragonBonesLoader extends JsonLoader<DragonBonesEntity> {
    @Inject
    public DragonBonesLoader(JsonReader json, JsonParser<DragonBonesEntity> parser) {
        super(json, parser);
    }
}
