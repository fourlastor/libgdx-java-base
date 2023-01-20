package io.github.fourlastor.gdx.ldtk.model;

import com.badlogic.gdx.utils.JsonValue;
import io.github.fourlastor.gdx.ldtk.LdtkParser;
import javax.inject.Inject;

public class LdtkTilesetCustomData {
    public final int tileId;
    public final String data;

    public LdtkTilesetCustomData(int tileId, String data) {
        this.tileId = tileId;
        this.data = data;
    }

    public static class Parser extends LdtkParser<LdtkTilesetCustomData> {
        @Inject
        public Parser() {}

        @Override
        public LdtkTilesetCustomData parse(JsonValue value) {
            return new LdtkTilesetCustomData(value.getInt("tileId"), value.getString("data"));
        }
    }
}
