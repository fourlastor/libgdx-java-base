package io.github.fourlastor.game.di.modules;

import dagger.Binds;
import dagger.Module;
import io.github.fourlastor.harlequin.loader.dragonbones.model.DragonBonesAabb;
import io.github.fourlastor.harlequin.loader.dragonbones.model.DragonBonesAnimation;
import io.github.fourlastor.harlequin.loader.dragonbones.model.DragonBonesAnimationSlot;
import io.github.fourlastor.harlequin.loader.dragonbones.model.DragonBonesArmature;
import io.github.fourlastor.harlequin.loader.dragonbones.model.DragonBonesArmatureSlot;
import io.github.fourlastor.harlequin.loader.dragonbones.model.DragonBonesBone;
import io.github.fourlastor.harlequin.loader.dragonbones.model.DragonBonesCanvas;
import io.github.fourlastor.harlequin.loader.dragonbones.model.DragonBonesDisplay;
import io.github.fourlastor.harlequin.loader.dragonbones.model.DragonBonesDisplayFrame;
import io.github.fourlastor.harlequin.loader.dragonbones.model.DragonBonesEntity;
import io.github.fourlastor.harlequin.loader.dragonbones.model.DragonBonesSkin;
import io.github.fourlastor.harlequin.loader.dragonbones.model.DragonBonesSkinSlot;
import io.github.fourlastor.json.JsonParser;

@Module
public interface DragonBonesModule {

    @Binds
    JsonParser<DragonBonesAabb> bindAabb(DragonBonesAabb.Parser parser);

    @Binds
    JsonParser<DragonBonesAnimation> bindAnimation(DragonBonesAnimation.Parser parser);

    @Binds
    JsonParser<DragonBonesAnimationSlot> bindAnimationSlot(DragonBonesAnimationSlot.Parser parser);

    @Binds
    JsonParser<DragonBonesArmature> bindArmature(DragonBonesArmature.Parser parser);

    @Binds
    JsonParser<DragonBonesArmatureSlot> bindArmatureSlot(DragonBonesArmatureSlot.Parser parser);

    @Binds
    JsonParser<DragonBonesBone> bindBone(DragonBonesBone.Parser parser);

    @Binds
    JsonParser<DragonBonesCanvas> bindCanvas(DragonBonesCanvas.Parser parser);

    @Binds
    JsonParser<DragonBonesDisplay> bindDisplay(DragonBonesDisplay.Parser parser);

    @Binds
    JsonParser<DragonBonesDisplayFrame> bindDisplayFrame(DragonBonesDisplayFrame.Parser parser);

    @Binds
    JsonParser<DragonBonesEntity> bindEntity(DragonBonesEntity.Parser parser);

    @Binds
    JsonParser<DragonBonesSkin> bindSkin(DragonBonesSkin.Parser parser);

    @Binds
    JsonParser<DragonBonesSkinSlot> bindSkinSlot(DragonBonesSkinSlot.Parser parser);
}
