package io.github.fourlastor.harlequin.spine.loader.model;

import com.badlogic.gdx.utils.JsonValue;
import io.github.fourlastor.harlequin.spine.loader.SpineParser;
import javax.inject.Inject;

public class SpineSlot {
    public final String name;
    public final String bone;
    public final String attachment;

    public SpineSlot(String name, String bone, String attachment) {
        this.name = name;
        this.bone = bone;
        this.attachment = attachment;
    }

    @Override
    public String toString() {
        return "Slot{" + "name='" + name + '\'' + ", bone='" + bone + '\'' + ", attachment='" + attachment + '\'' + '}';
    }

    public static class Parser extends SpineParser<SpineSlot> {

        @Inject
        public Parser() {}

        @Override
        public SpineSlot parse(JsonValue value) {
            return new SpineSlot(
                    value.getString("name"), value.getString("bone", null), value.getString("attachment", null));
        }
    }
}
