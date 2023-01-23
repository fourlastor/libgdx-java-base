package io.github.fourlastor.harlequin.loader.dragonbones;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonReader;
import io.github.fourlastor.harlequin.animation.AnimationNode;
import io.github.fourlastor.harlequin.loader.dragonbones.model.DragonBonesEntity;
import io.github.fourlastor.json.JsonParser;

public class DragonBonesLoader
        extends AsynchronousAssetLoader<AnimationNode.Group, AssetLoaderParameters<AnimationNode.Group>> {
    private final JsonReader json;
    private final JsonParser<DragonBonesEntity> jsonParser;

    private final String atlasPath;
    private final String basePath;
    private DragonBonesEntity data;

    public DragonBonesLoader(Options options, JsonReader json, JsonParser<DragonBonesEntity> jsonParser) {
        super(new InternalFileHandleResolver());
        this.json = json;
        this.jsonParser = jsonParser;
        this.atlasPath = options.atlasPath;
        this.basePath = options.basePath;
    }

    @Override
    public void loadAsync(
            AssetManager manager,
            String fileName,
            FileHandle file,
            AssetLoaderParameters<AnimationNode.Group> parameter) {
        data = jsonParser.parse(json.parse(file));
    }

    @Override
    public AnimationNode.Group loadSync(
            AssetManager manager,
            String fileName,
            FileHandle file,
            AssetLoaderParameters<AnimationNode.Group> parameter) {
        TextureAtlas atlas = manager.get(atlasPath, TextureAtlas.class);
        String texture = file.parent().child("texture").path();
        String base = texture.substring(basePath.length() + 1);
        try {
            return new DragonBonesAnimationsParser(atlas, base).parse(data);
        } finally {
            data = null;
        }
    }

    @Override
    public Array<AssetDescriptor> getDependencies(
            String fileName, FileHandle file, AssetLoaderParameters<AnimationNode.Group> parameter) {
        return Array.with(new AssetDescriptor<>(atlasPath, TextureAtlas.class));
    }

    public static class Options {
        public final String atlasPath;
        public final String basePath;

        public Options(String atlasPath, String basePath) {
            this.atlasPath = atlasPath;
            this.basePath = basePath;
        }
    }
}
