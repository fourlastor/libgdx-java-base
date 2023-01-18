package com.badlogic.gdx.ai;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.AbsoluteFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ClasspathFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ExternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.LocalFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import java.io.File;

/**
 * Courtesy of <a href="https://github.com/tommyettinger/Arise_Deeper/blob/f04e63adbc54844d6c7ea68aa0bbde13dc0153d4/core/src/emu/com/badlogic/gdx/ai/StandaloneFileSystem.java">Arise Deeper</a>
 * Used to enable recompilation in GWT with super dev
 * @author davebaol
 */
@SuppressWarnings("ALL") // this is used in GWT only
public class StandaloneFileSystem implements FileSystem {

    public StandaloneFileSystem() {}

    @Override
    public FileHandleResolver newResolver(FileType fileType) {
        switch (fileType) {
            case Absolute:
                return new AbsoluteFileHandleResolver();
            case Classpath:
                return new ClasspathFileHandleResolver();
            case External:
                return new ExternalFileHandleResolver();
            case Internal:
                return new InternalFileHandleResolver();
            case Local:
                return new LocalFileHandleResolver();
        }
        return null; // Should never happen
    }

    @Override
    public FileHandle newFileHandle(String fileName) {
        return Gdx.files.absolute(fileName);
    }

    @Override
    public FileHandle newFileHandle(File file) {
        return Gdx.files.absolute(file.getAbsolutePath());
    }

    @Override
    public FileHandle newFileHandle(String fileName, FileType type) {
        return Gdx.files.getFileHandle(fileName, type);
    }

    @Override
    public FileHandle newFileHandle(File file, FileType type) {
        return Gdx.files.getFileHandle(file.getPath(), type);
    }
}
