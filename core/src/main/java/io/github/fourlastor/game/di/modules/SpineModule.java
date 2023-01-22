package io.github.fourlastor.game.di.modules;

import dagger.Binds;
import dagger.Module;
import io.github.fourlastor.harlequin.loader.spine.model.SpineAnimation;
import io.github.fourlastor.harlequin.loader.spine.model.SpineAnimationSlot;
import io.github.fourlastor.harlequin.loader.spine.model.SpineBone;
import io.github.fourlastor.harlequin.loader.spine.model.SpineEntity;
import io.github.fourlastor.harlequin.loader.spine.model.SpineSkeleton;
import io.github.fourlastor.harlequin.loader.spine.model.SpineSkin;
import io.github.fourlastor.harlequin.loader.spine.model.SpineSkins;
import io.github.fourlastor.harlequin.loader.spine.model.SpineSlot;
import io.github.fourlastor.json.JsonParser;

@Module
public interface SpineModule {

    @Binds
    JsonParser<SpineAnimationSlot> bindSpineAnimatedSlot(SpineAnimationSlot.Parser parser);

    @Binds
    JsonParser<SpineAnimation> bindSpineAnimation(SpineAnimation.Parser parser);

    @Binds
    JsonParser<SpineBone> bindSpineBone(SpineBone.Parser parser);

    @Binds
    JsonParser<SpineEntity> bindSpineEntity(SpineEntity.Parser parser);

    @Binds
    JsonParser<SpineSkeleton> bindSpineSkeleton(SpineSkeleton.Parser parser);

    @Binds
    JsonParser<SpineSkin> bindSpineSkin(SpineSkin.Parser parser);

    @Binds
    JsonParser<SpineSkins> bindSpineSkins(SpineSkins.Parser parser);

    @Binds
    JsonParser<SpineSlot> bindSpineSlot(SpineSlot.Parser parser);
}
