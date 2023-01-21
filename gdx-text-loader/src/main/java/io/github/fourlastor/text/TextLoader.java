package io.github.fourlastor.text;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import javax.inject.Inject;

/**
 * Loads a text file through asset manager in LibGDX
 * Useful for shaders.
 * Original author <a href="https://gamedev.stackexchange.com/questions/101326/load-a-simple-text-file-through-asset-manager-in-libgdx>RegisteredUser</a>
 */
public class TextLoader extends AsynchronousAssetLoader<Text, TextLoader.TextParameter> {

    @Inject
    public TextLoader() {
        super(new InternalFileHandleResolver());
    }

    Text text;

    @Override
    public void loadAsync(AssetManager manager, String fileName, FileHandle file, TextParameter parameter) {

        this.text = null;
        this.text = new Text(file.readString("UTF-8"));
    }

    @Override
    public Text loadSync(AssetManager manager, String fileName, FileHandle file, TextParameter parameter) {

        Text text = this.text;
        this.text = null;

        return text;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, TextParameter parameter) {

        return null;
    }

    public static class TextParameter extends AssetLoaderParameters<Text> {}
}
