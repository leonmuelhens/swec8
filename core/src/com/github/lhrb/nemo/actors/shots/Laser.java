package com.github.lhrb.nemo.actors.shots;


import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.github.lhrb.nemo.actors.CollisionEvent;
import com.github.lhrb.nemo.util.AnimationLoader;

public class Laser extends Shots {

    public Laser(float x, float y, float angle, Stage stage) {
        super(x, y, angle, stage);

        setAnimation(AnimationLoader.get().texture("SchussLaser.png"));      
        setSpeedMax(1600);
        setAcceleration(30000);
        setShapePolygon(8);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void destroy() {

        addAction(new Action() {
            private int damage = 3;
            @Override
            public boolean act(float delta) {
                if (damage < 1) {
                    ((Shots)target).setPlayerShot(false);
                    return true;
                }
                else {
                    damage--;
                    return false;
                }
            }
        });
    }
}
