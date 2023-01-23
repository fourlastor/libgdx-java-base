package io.github.fourlastor.harlequin.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.SnapshotArray;
import io.github.fourlastor.harlequin.animation.AnimationNode;

public class AnimationStateMachine extends Group implements StateActor {

    public AnimationStateMachine(AnimationNode.Group node) {
        setPosition(node.x, node.y);
        for (AnimationNode child : node.children) {
            if (child instanceof AnimationNode.Group) {
                addActor(new AnimationStateMachine(((AnimationNode.Group) child)));
            } else if (child instanceof AnimationNode.Image) {
                addActor(new AnimationImage(((AnimationNode.Image) child)));
            }
        }
    }

    @Override
    public void enter(String stateName) {
        SnapshotArray<Actor> children = getChildren();
        children.begin();
        for (Actor child : children) {
            if (child instanceof StateActor) {
                ((StateActor) child).enter(stateName);
            }
        }
        children.end();
    }

    private static class AnimationImage extends Group implements StateActor {

        private final AnimatedImage image;
        private final AnimationNode.Image node;

        public AnimationImage(AnimationNode.Image node) {
            image = new AnimatedImage(node.drawableAnimation);
            addActor(image);
            this.node = node;
        }

        @Override
        public void enter(String stateName) {
            image.setAnimation(node.animations.get(stateName));
        }
    }
}
