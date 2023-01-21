package io.github.fourlastor.harlequin.spine.loader;

import com.badlogic.gdx.utils.JsonReader;
import io.github.fourlastor.harlequin.spine.loader.model.SpineEntity;
import io.github.fourlastor.json.JsonLoader;
import javax.inject.Inject;

public class SpineLoader extends JsonLoader<SpineEntity> {
    @Inject
    public SpineLoader(JsonReader json, SpineParser<SpineEntity> parser) {
        super(json, parser);
    }
}
