package com.github.lhrb.nemo.actors.shots;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.github.lhrb.nemo.actors.PhysicalActor;

public abstract class Shots extends PhysicalActor {

    private final float angle;

    public Shots(float x, float y, Stage stage, float angle) {
        super(x, y, stage);

        this.angle = angle;
        setShapePolygon(6);
        setX(getX()-(getWidth()/2));
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        accelerationAtAngle(angle);

        if (getY() <= getStage().getHeight()) {
            applyPhysics(delta);
        }
        else {
            remove();
        }

        /**
         * ATTENTION
         * this method does not provide any security mechanism
         */
        //setWorldBounds();

    }

    /* (non-Javadoc)
     * @see com.github.lhrb.nemo.actors.PhysicalActor#collision()
     */
    @Override
    public void collision() {
        System.out.println("shot collision");
    }

}
