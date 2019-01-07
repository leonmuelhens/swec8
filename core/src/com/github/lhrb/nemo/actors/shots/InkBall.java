package com.github.lhrb.nemo.actors.shots;


import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.github.lhrb.nemo.util.AnimationLoader;
import com.github.lhrb.nemo.util.SoundManager;


public class InkBall extends Shots {

    public InkBall(float x, float y, float angle, Stage stage) {
        super(x, y, angle, stage);

        setAnimation(AnimationLoader.get().texture("krakenShotI.png"));
        setSpeedMax(1200);
        setAcceleration(800);
        setShapePolygon(8);

    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }


}