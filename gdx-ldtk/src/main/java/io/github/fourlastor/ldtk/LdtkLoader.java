package io.github.fourlastor.ldtk;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonReader;
import io.github.fourlastor.ldtk.model.LdtkMapData;
import javax.inject.Inject;

public class LdtkLoader extends AsynchronousAssetLoader<LdtkMapData, AssetLoaderParameters<LdtkMapData>> {
    private LdtkMapData ldtkMapData;
    private final JsonReader json;
    private final LdtkParser<LdtkMapData> parser;

    @Inject
    public LdtkLoader(JsonReader json, LdtkParser<LdtkMapData> parser) {
        super(new InternalFileHandleResolver());
        this.json = json;
        this.parser = parser;
    }

    @Override
    public void loadAsync(
            AssetManager manager, String fileName, FileHandle file, AssetLoaderParameters<LdtkMapData> parameter) {
        ldtkMapData = parser.parse(json.parse(file));
    }

    @Override
    public LdtkMapData loadSync(
            AssetManager manager, String fileName, FileHandle file, AssetLoaderParameters<LdtkMapData> parameter) {
        return ldtkMapData;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Array<AssetDescriptor> getDependencies(
            String fileName, FileHandle file, AssetLoaderParameters<LdtkMapData> parameter) {
        return null;
    }
}
