package io.github.fourlastor.game.di.modules;

import dagger.Binds;
import dagger.Module;
import io.github.fourlastor.harlequin.spine.loader.SpineParser;
import io.github.fourlastor.harlequin.spine.loader.model.SpineAnimation;
import io.github.fourlastor.harlequin.spine.loader.model.SpineAnimationSlot;
import io.github.fourlastor.harlequin.spine.loader.model.SpineBone;
import io.github.fourlastor.harlequin.spine.loader.model.SpineEntity;
import io.github.fourlastor.harlequin.spine.loader.model.SpineSkeleton;
import io.github.fourlastor.harlequin.spine.loader.model.SpineSkin;
import io.github.fourlastor.harlequin.spine.loader.model.SpineSkins;
import io.github.fourlastor.harlequin.spine.loader.model.SpineSlot;

@Module
public interface SpineModule {

    @Binds
    SpineParser<SpineAnimationSlot> bindSpineAnimatedSlot(SpineAnimationSlot.Parser parser);

    @Binds
    SpineParser<SpineAnimation> bindSpineAnimation(SpineAnimation.Parser parser);

    @Binds
    SpineParser<SpineBone> bindSpineBone(SpineBone.Parser parser);

    @Binds
    SpineParser<SpineEntity> bindSpineEntity(SpineEntity.Parser parser);

    @Binds
    SpineParser<SpineSkeleton> bindSpineSkeleton(SpineSkeleton.Parser parser);

    @Binds
    SpineParser<SpineSkin> bindSpineSkin(SpineSkin.Parser parser);

    @Binds
    SpineParser<SpineSkins> bindSpineSkins(SpineSkins.Parser parser);

    @Binds
    SpineParser<SpineSlot> bindSpineSlot(SpineSlot.Parser parser);
}
