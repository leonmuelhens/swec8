package com.github.lhrb.nemo.actors.shots;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.github.lhrb.nemo.actors.Existence;
import com.github.lhrb.nemo.actors.PhysicalActor;
import com.github.lhrb.nemo.actors.Removable;
import com.github.lhrb.nemo.util.CollisionEvent;

public abstract class Shots extends PhysicalActor implements Existence, Removable{

    private final float angle;
    private boolean isPlayerShot;

    public Shots(float x, float y, float angle, Stage stage) {
        super(x, y, stage);
        
        this.angle = angle;
        isPlayerShot = (angle < 180);
        rotateBy(angle-90);
        setX(getX()-(getWidth()/2));
        
        perish();

    }

    @Override
    public void act(float delta) {
        super.act(delta);

        accelerationAtAngle(angle);
        applyPhysics(delta);

    }

    /* (non-Javadoc)
     * @see com.github.lhrb.nemo.actors.PhysicalActor#collision()
     */
    @Override
    public void collision(CollisionEvent col) {
            destroy();
    }
    
    @Override
    public void perish() {
        //remove actor after 1 sec
        addAction(Actions.delay(1));
        addAction(Actions.after(Actions.removeActor()));
    }
    
    @Override
    public void destroy() {
        addAction(Actions.removeActor());
    }

    public boolean isPlayerShot() { return isPlayerShot; }

}
