package io.github.fourlastor.game.di.modules;

import dagger.Binds;
import dagger.Module;
import io.github.fourlastor.gdx.ldtk.LdtkParser;
import io.github.fourlastor.gdx.ldtk.model.LdtkDefinitions;
import io.github.fourlastor.gdx.ldtk.model.LdtkEntityInstance;
import io.github.fourlastor.gdx.ldtk.model.LdtkEnumDefinition;
import io.github.fourlastor.gdx.ldtk.model.LdtkEnumValueDefinition;
import io.github.fourlastor.gdx.ldtk.model.LdtkFieldInstance;
import io.github.fourlastor.gdx.ldtk.model.LdtkLayerInstance;
import io.github.fourlastor.gdx.ldtk.model.LdtkLevelBackgroundPositionData;
import io.github.fourlastor.gdx.ldtk.model.LdtkLevelDefinition;
import io.github.fourlastor.gdx.ldtk.model.LdtkMapData;
import io.github.fourlastor.gdx.ldtk.model.LdtkNeighbourLevelData;
import io.github.fourlastor.gdx.ldtk.model.LdtkTileInstance;
import io.github.fourlastor.gdx.ldtk.model.LdtkTileRect;
import io.github.fourlastor.gdx.ldtk.model.LdtkTilesetCustomData;
import io.github.fourlastor.gdx.ldtk.model.LdtkTilesetDefinition;
import io.github.fourlastor.gdx.ldtk.model.MultiAssociatedValue;

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
