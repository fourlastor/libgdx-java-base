package io.github.fourlastor.harlequin.loader.dragonbones.model;

import com.badlogic.gdx.utils.JsonValue;
import io.github.fourlastor.json.JsonParser;
import java.util.List;
import javax.inject.Inject;

public class DragonBonesSkin {

    public final List<DragonBonesSkinSlot> slots;

    public DragonBonesSkin(List<DragonBonesSkinSlot> slots) {
        this.slots = slots;
    }

    public static class Parser extends JsonParser<DragonBonesSkin> {

        private final JsonParser<DragonBonesSkinSlot> skinSlotParser;

        @Inject
        public Parser(JsonParser<DragonBonesSkinSlot> skinSlotParser) {
            this.skinSlotParser = skinSlotParser;
        }

        @Override
        public DragonBonesSkin parse(JsonValue value) {
            return new DragonBonesSkin(getList(value.get("slot"), skinSlotParser::parse));
        }
    }
}
