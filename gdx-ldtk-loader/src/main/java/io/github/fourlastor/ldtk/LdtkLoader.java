package io.github.fourlastor.ldtk;

import com.badlogic.gdx.utils.JsonReader;
import io.github.fourlastor.json.JsonLoader;
import io.github.fourlastor.json.JsonParser;
import io.github.fourlastor.ldtk.model.LdtkMapData;
import javax.inject.Inject;

public class LdtkLoader extends JsonLoader<LdtkMapData> {

    @Inject
    public LdtkLoader(JsonReader json, JsonParser<LdtkMapData> parser) {
        super(json, parser);
    }
}
