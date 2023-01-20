package io.github.fourlastor.gdx.ldtk.model;

import com.badlogic.gdx.utils.JsonValue;
import io.github.fourlastor.gdx.ldtk.LdtkParser;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.jetbrains.annotations.Nullable;

public class MultiAssociatedValue {
    @Nullable
    public final List<String> stringList;

    @Nullable
    public final List<Map<String, String>> stringMapList;

    @Nullable
    public final Map<String, String> stringMap;

    @Nullable
    public final String content;

    public MultiAssociatedValue(
            @Nullable List<String> stringList,
            @Nullable List<Map<String, String>> stringMapList,
            @Nullable Map<String, String> stringMap,
            @Nullable String content) {
        this.stringList = stringList;
        this.stringMapList = stringMapList;
        this.stringMap = stringMap;
        this.content = content;
    }

    public static class Parser extends LdtkParser<MultiAssociatedValue> {

        @Inject
        public Parser() {}

        @Override
        public MultiAssociatedValue parse(JsonValue value) {
            return new MultiAssociatedValue(
                    getOptional(value, "stringList", it -> getList(it, JsonValue::asString)),
                    getOptional(
                            value, "stringMapList", it -> getList(it, list -> getStringMap(list, JsonValue::asString))),
                    getOptional(value, "stringMap", it -> getStringMap(it, JsonValue::asString)),
                    getOptional(value, "content", JsonValue::asString));
        }
    }
}
