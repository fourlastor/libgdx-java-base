package io.github.fourlastor.ldtk.model;

import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Null;
import io.github.fourlastor.ldtk.LdtkParser;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

public class MultiAssociatedValue {
    @Null
    public final List<String> stringList;

    @Null
    public final List<Map<String, String>> stringMapList;

    @Null
    public final Map<String, String> stringMap;

    @Null
    public final String content;

    public MultiAssociatedValue(
            @Null List<String> stringList,
            @Null List<Map<String, String>> stringMapList,
            @Null Map<String, String> stringMap,
            @Null String content) {
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