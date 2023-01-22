package io.github.fourlastor.game.di.modules;

import dagger.Binds;
import dagger.Module;
import io.github.fourlastor.json.JsonParser;
import io.github.fourlastor.ldtk.model.LdtkDefinitions;
import io.github.fourlastor.ldtk.model.LdtkEntityInstance;
import io.github.fourlastor.ldtk.model.LdtkEnumDefinition;
import io.github.fourlastor.ldtk.model.LdtkEnumValueDefinition;
import io.github.fourlastor.ldtk.model.LdtkFieldInstance;
import io.github.fourlastor.ldtk.model.LdtkLayerInstance;
import io.github.fourlastor.ldtk.model.LdtkLevelBackgroundPositionData;
import io.github.fourlastor.ldtk.model.LdtkLevelDefinition;
import io.github.fourlastor.ldtk.model.LdtkMapData;
import io.github.fourlastor.ldtk.model.LdtkNeighbourLevelData;
import io.github.fourlastor.ldtk.model.LdtkTileInstance;
import io.github.fourlastor.ldtk.model.LdtkTileRect;
import io.github.fourlastor.ldtk.model.LdtkTilesetCustomData;
import io.github.fourlastor.ldtk.model.LdtkTilesetDefinition;
import io.github.fourlastor.ldtk.model.MultiAssociatedValue;

@Module
public interface LdtkModule {

    @Binds
    JsonParser<LdtkDefinitions> bindLdtkDefinitions(LdtkDefinitions.Parser parser);

    @Binds
    JsonParser<LdtkEntityInstance> bindLdtkEntityInstance(LdtkEntityInstance.Parser parser);

    @Binds
    JsonParser<LdtkEnumDefinition> bindLdtkEnumDefinition(LdtkEnumDefinition.Parser parser);

    @Binds
    JsonParser<LdtkEnumValueDefinition> bindLdtkEnumValueDefinition(LdtkEnumValueDefinition.Parser parser);

    @Binds
    JsonParser<LdtkFieldInstance> bindLdtkFieldInstance(LdtkFieldInstance.Parser parser);

    @Binds
    JsonParser<LdtkLayerInstance> bindLdtkLayerInstance(LdtkLayerInstance.Parser parser);

    @Binds
    JsonParser<LdtkLevelBackgroundPositionData> bindLdtkLevelBackgroundPositionData(
            LdtkLevelBackgroundPositionData.Parser parser);

    @Binds
    JsonParser<LdtkLevelDefinition> bindLdtkLevelDefinition(LdtkLevelDefinition.Parser parser);

    @Binds
    JsonParser<LdtkMapData> bindLdtkMapData(LdtkMapData.Parser parser);

    @Binds
    JsonParser<LdtkNeighbourLevelData> bindLdtkNeighbourLevelData(LdtkNeighbourLevelData.Parser parser);

    @Binds
    JsonParser<LdtkTileInstance> bindLdtkTileInstance(LdtkTileInstance.Parser parser);

    @Binds
    JsonParser<LdtkTileRect> bindLdtkTileRect(LdtkTileRect.Parser parser);

    @Binds
    JsonParser<LdtkTilesetCustomData> bindLdtkTilesetCustomData(LdtkTilesetCustomData.Parser parser);

    @Binds
    JsonParser<LdtkTilesetDefinition> bindLdtkTilesetDefinition(LdtkTilesetDefinition.Parser parser);

    @Binds
    JsonParser<MultiAssociatedValue> bindMultiAssociatedValue(MultiAssociatedValue.Parser parser);
}
