package io.github.fourlastor.harlequin.loader.dragonbones.model;

import com.badlogic.gdx.utils.JsonValue;
import io.github.fourlastor.json.JsonParser;
import java.util.List;
import javax.inject.Inject;

public class DragonBonesAnimation {

    public final int duration;
    public final String name;
    public final List<DragonBonesAnimationSlot> slots;

    public DragonBonesAnimation(int duration, String name, List<DragonBonesAnimationSlot> slots) {
        this.duration = duration;
        this.name = name;
        this.slots = slots;
    }

    public static class Parser extends JsonParser<DragonBonesAnimation> {

        private final JsonParser<DragonBonesAnimationSlot> animationSlotParser;

        @Inject
        public Parser(JsonParser<DragonBonesAnimationSlot> animationSlotParser) {
            this.animationSlotParser = animationSlotParser;
        }

        @Override
        public DragonBonesAnimation parse(JsonValue value) {
            return new DragonBonesAnimation(
                    value.getInt("duration"),
                    value.getString("name"),
                    getList(value.get("slot"), animationSlotParser::parse));
        }
    }
}
