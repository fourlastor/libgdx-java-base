package io.github.fourlastor.gdx.ldtk;

import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.JsonValue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import org.jetbrains.annotations.Nullable;

public abstract class LdtkParser<T> {
    public abstract T parse(JsonValue value);

    @Nullable
    protected final <V> V getOptional(JsonValue value, String name, Function<JsonValue, V> fn) {
        if (value.has(name) && !value.isNull()) {
            JsonValue jsonValue = value.get(name);
            if (jsonValue.isNull()) {
                return null;
            }
            return fn.apply(jsonValue);
        }
        return null;
    }

    @Nullable
    protected final Integer getOptionalInt(JsonValue value, String name) {
        return getOptional(value, name, JsonValue::asInt);
    }

    protected final IntArray getIntArray(JsonValue value) {
        IntArray values = new IntArray(false, value.size);
        for (JsonValue entry : value) {
            values.add(entry.asInt());
        }
        return values;
    }

    protected final FloatArray getFloatArray(JsonValue value) {
        FloatArray values = new FloatArray(false, value.size);
        for (JsonValue entry : value) {
            values.add(entry.asFloat());
        }
        return values;
    }

    protected final List<String> getStringList(JsonValue value) {
        return getList(value, JsonValue::asString);
    }

    protected final <V> List<V> getList(JsonValue value, Function<JsonValue, V> fn) {
        List<V> values = new ArrayList<>(value.size);
        for (JsonValue entry : value) {
            values.add(fn.apply(entry));
        }
        return values;
    }

    protected final <V> Map<String, V> getStringMap(JsonValue value, Function<JsonValue, V> fn) {
        HashMap<String, V> values = new HashMap<>();
        for (JsonValue entry : value) {
            values.put(entry.name, fn.apply(entry));
        }
        return values;
    }
}
