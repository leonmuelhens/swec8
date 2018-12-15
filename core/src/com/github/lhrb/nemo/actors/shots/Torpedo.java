package com.github.lhrb.nemo.actors.shots;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.github.lhrb.nemo.actors.Player;
import com.github.lhrb.nemo.util.AnimationLoader;

public class Torpedo extends Shots {

    private float initialAngle;

    public Torpedo(float x, float y, float angle) {
        super(x, y, angle);

        setAnimation(AnimationLoader.get().texture("SchussStandart.png"));
        setSpeedMax(100);
        setAcceleration(30000);
        setShapePolygon(8);

        initialAngle = angle;
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        for (Actor a:
                getStage().getActors()) {
            if (a instanceof Player) {
                if (a.getX() > getX()) {
                    accelerationAtAngle(initialAngle+45);
                }
                else if (a.getX() < getX()){
                    accelerationAtAngle(initialAngle-45);
                }
            }
        }
    }
}
