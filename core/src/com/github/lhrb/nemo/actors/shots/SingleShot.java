package com.github.lhrb.nemo.actors.shots;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.github.lhrb.nemo.util.AnimationLoader;

public class SingleShot extends Shots {

    //private final float angle;

    public SingleShot(float x, float y, float angle, Stage stage) {
        super(x, y, angle, stage);

        setAnimation(AnimationLoader.get().texture("shots/SchussStandart.png"));
        setSpeedMax(800);
        setAcceleration(30000);
        setShapePolygon(8);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
