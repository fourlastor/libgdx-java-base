package io.github.fourlastor.game.di.modules;

import dagger.Binds;
import dagger.Module;
import io.github.fourlastor.ldtk.LdtkParser;
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
    LdtkParser<LdtkDefinitions> bindLdtkDefinitions(LdtkDefinitions.Parser parser);

    @Binds
    LdtkParser<LdtkEntityInstance> bindLdtkEntityInstance(LdtkEntityInstance.Parser parser);

    @Binds
    LdtkParser<LdtkEnumDefinition> bindLdtkEnumDefinition(LdtkEnumDefinition.Parser parser);

    @Binds
    LdtkParser<LdtkEnumValueDefinition> bindLdtkEnumValueDefinition(LdtkEnumValueDefinition.Parser parser);

    @Binds
    LdtkParser<LdtkFieldInstance> bindLdtkFieldInstance(LdtkFieldInstance.Parser parser);

    @Binds
    LdtkParser<LdtkLayerInstance> bindLdtkLayerInstance(LdtkLayerInstance.Parser parser);

    @Binds
    LdtkParser<LdtkLevelBackgroundPositionData> bindLdtkLevelBackgroundPositionData(
            LdtkLevelBackgroundPositionData.Parser parser);

    @Binds
    LdtkParser<LdtkLevelDefinition> bindLdtkLevelDefinition(LdtkLevelDefinition.Parser parser);

    @Binds
    LdtkParser<LdtkMapData> bindLdtkMapData(LdtkMapData.Parser parser);

    @Binds
    LdtkParser<LdtkNeighbourLevelData> bindLdtkNeighbourLevelData(LdtkNeighbourLevelData.Parser parser);

    @Binds
    LdtkParser<LdtkTileInstance> bindLdtkTileInstance(LdtkTileInstance.Parser parser);

    @Binds
    LdtkParser<LdtkTileRect> bindLdtkTileRect(LdtkTileRect.Parser parser);

    @Binds
    LdtkParser<LdtkTilesetCustomData> bindLdtkTilesetCustomData(LdtkTilesetCustomData.Parser parser);

    @Binds
    LdtkParser<LdtkTilesetDefinition> bindLdtkTilesetDefinition(LdtkTilesetDefinition.Parser parser);

    @Binds
    LdtkParser<MultiAssociatedValue> bindMultiAssociatedValue(MultiAssociatedValue.Parser parser);
}
