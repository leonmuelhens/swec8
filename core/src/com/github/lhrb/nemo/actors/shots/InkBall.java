package com.github.lhrb.nemo.actors.shots;


import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.github.lhrb.nemo.actors.CollisionEvent;
import com.github.lhrb.nemo.actors.OverlayActor;
import com.github.lhrb.nemo.util.AnimationLoader;
import com.github.lhrb.nemo.util.SoundManager;


public class InkBall extends Shots {

    public InkBall(float x, float y, float angle, Stage stage) {
        super(x, y, angle, stage);

        this.setAnimation(AnimationLoader.get().texture("tinte_small.png"));
        setSpeedMax(1200);
        setAcceleration(800);
        setShapePolygon(8);
    }

    @Override
    public void collision(CollisionEvent col) {
        OverlayActor inked = new OverlayActor("tinte.png",getStage(),5f);
    }
}