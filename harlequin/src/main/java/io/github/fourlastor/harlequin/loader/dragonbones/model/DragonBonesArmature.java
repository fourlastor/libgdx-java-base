package io.github.fourlastor.harlequin.loader.dragonbones.model;

import com.badlogic.gdx.utils.JsonValue;
import io.github.fourlastor.json.JsonParser;
import java.util.List;
import javax.inject.Inject;

public class DragonBonesArmature {

    public final int frameRate;
    public final String name;
    public final DragonBonesAabb aabb;
    public final List<DragonBonesBone> bones;
    public final List<DragonBonesArmatureSlot> slots;
    public final List<DragonBonesSkin> skins;
    public final List<DragonBonesAnimation> animations;
    public final DragonBonesCanvas canvas;

    public DragonBonesArmature(
            int frameRate,
            String name,
            DragonBonesAabb aabb,
            List<DragonBonesBone> bones,
            List<DragonBonesArmatureSlot> slots,
            List<DragonBonesSkin> skins,
            List<DragonBonesAnimation> animations,
            DragonBonesCanvas canvas) {
        this.frameRate = frameRate;
        this.name = name;
        this.aabb = aabb;
        this.bones = bones;
        this.slots = slots;
        this.skins = skins;
        this.animations = animations;
        this.canvas = canvas;
    }

    public static class Parser extends JsonParser<DragonBonesArmature> {

        private final JsonParser<DragonBonesAabb> aabbParser;
        private final JsonParser<DragonBonesBone> boneParser;
        private final JsonParser<DragonBonesArmatureSlot> armatureSlotParser;
        private final JsonParser<DragonBonesSkin> skinParser;
        private final JsonParser<DragonBonesAnimation> animationParser;
        private final JsonParser<DragonBonesCanvas> canvasParser;

        @Inject
        public Parser(
                JsonParser<DragonBonesAabb> aabbParser,
                JsonParser<DragonBonesBone> boneParser,
                JsonParser<DragonBonesArmatureSlot> armatureSlotParser,
                JsonParser<DragonBonesSkin> skinParser,
                JsonParser<DragonBonesAnimation> animationParser,
                JsonParser<DragonBonesCanvas> canvasParser) {
            this.aabbParser = aabbParser;
            this.boneParser = boneParser;
            this.armatureSlotParser = armatureSlotParser;
            this.skinParser = skinParser;
            this.animationParser = animationParser;
            this.canvasParser = canvasParser;
        }

        @Override
        public DragonBonesArmature parse(JsonValue value) {
            return new DragonBonesArmature(
                    value.getInt("frameRate"),
                    value.getString("name"),
                    aabbParser.parse(value.get("aabb")),
                    getList(value.get("bone"), boneParser::parse),
                    getList(value.get("slot"), armatureSlotParser::parse),
                    getList(value.get("skin"), skinParser::parse),
                    getList(value.get("animation"), animationParser::parse),
                    canvasParser.parse(value.get("canvas")));
        }
    }
}
