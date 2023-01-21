package io.github.fourlastor.game.di.modules;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import dagger.Module;
import dagger.Provides;
import io.github.fourlastor.harlequin.spine.loader.SpineLoader;
import io.github.fourlastor.harlequin.spine.loader.model.SpineEntity;
import io.github.fourlastor.ldtk.LdtkLoader;
import io.github.fourlastor.ldtk.model.LdtkMapData;
import io.github.fourlastor.text.Text;
import io.github.fourlastor.text.TextLoader;
import javax.inject.Named;
import javax.inject.Singleton;

@Module
public class AssetsModule {

    private static final String PATH_TEXTURE_ATLAS = "images/included/packed/images.pack.atlas";
    private static final String PATH_LEVELS = "maps/levels.ldtk";
    private static final String PATH_DEFAULT_SHADER = "shaders/default.vs";
    private static final String PATH_WAVE_SHADER = "shaders/wave.fs";
    public static final String WHITE_PIXEL = "white-pixel";
    private static final String PATH_SPINE_JSON = "animations/animation.json";

    @Provides
    @Singleton
    public AssetManager assetManager(LdtkLoader ldtkLoader, TextLoader textLoader, SpineLoader spineLoader) {
        AssetManager assetManager = new AssetManager();
        assetManager.setLoader(LdtkMapData.class, ldtkLoader);
        assetManager.setLoader(Text.class, textLoader);
        assetManager.setLoader(SpineEntity.class, spineLoader);
        assetManager.load(PATH_TEXTURE_ATLAS, TextureAtlas.class);
        assetManager.load(PATH_LEVELS, LdtkMapData.class);
        assetManager.load(PATH_SPINE_JSON, SpineEntity.class);
        assetManager.load(PATH_DEFAULT_SHADER, Text.class);
        assetManager.load(PATH_WAVE_SHADER, Text.class);
        assetManager.finishLoading();
        return assetManager;
    }

    @Provides
    @Singleton
    public TextureAtlas textureAtlas(AssetManager assetManager) {
        return assetManager.get(PATH_TEXTURE_ATLAS, TextureAtlas.class);
    }

    @Provides
    @Singleton
    @Named(WHITE_PIXEL)
    public TextureRegion whitePixel(TextureAtlas atlas) {
        return atlas.findRegion("whitePixel");
    }
}
