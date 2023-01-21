package io.github.fourlastor.harlequin.spine.loader.model;

import com.badlogic.gdx.utils.JsonValue;
import io.github.fourlastor.harlequin.spine.loader.SpineParser;
import java.util.Map;
import javax.inject.Inject;

public class SpineAnimation {
    public final String name;
    public final Map<String, SpineAnimationSlot> slots;

    public SpineAnimation(String name, Map<String, SpineAnimationSlot> slots) {
        this.name = name;
        this.slots = slots;
    }

    public static class Parser extends SpineParser<SpineAnimation> {

        private final SpineParser<SpineAnimationSlot> animatedSlotParser;

        @Inject
        public Parser(SpineParser<SpineAnimationSlot> animatedSlotParser) {
            this.animatedSlotParser = animatedSlotParser;
        }

        @Override
        public SpineAnimation parse(JsonValue value) {
            return new SpineAnimation(value.name, getMap(value.get("slots"), animatedSlotParser::parse));
        }
    }
}
