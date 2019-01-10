package com.github.lhrb.nemo.actors;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.github.lhrb.nemo.util.AnimationLoader;

public class OverlayActor extends ActorPrefab {
    private float overlayTime;
    private float passedTime;
    private boolean infinite;

    public OverlayActor(String fileName, Stage stage, float overlayTime) {
        super(0,0,stage);
        setAnimation(AnimationLoader.get().texture(fileName));

        setSpeedMax(0);
        setAcceleration(0);
        if (overlayTime == 0) {
            infinite = true;
        } else {
            infinite = false;
        }
        this.overlayTime = overlayTime;
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if(!infinite) {
            passedTime += delta;

            if (passedTime <= overlayTime) {

                this.setColor(1.0f,1.0f,1.0f, (overlayTime - passedTime)/overlayTime);
            } else {
                addAction(Actions.removeActor());
            }
        }
    }
}
