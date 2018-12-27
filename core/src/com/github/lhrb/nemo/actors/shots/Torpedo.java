package com.github.lhrb.nemo.actors.shots;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.github.lhrb.nemo.GameManager;
import com.github.lhrb.nemo.util.AnimationLoader;

public class Torpedo extends Shots {

    private float initialAngle;

    public Torpedo(float x, float y, float angle, Stage stage) {
        super(x, y, angle, stage);

        setAnimation(AnimationLoader.get().texture("shots/SchussStandart.png"));
        setSpeedMax(100);
        setAcceleration(30000);
        setShapePolygon(8);

        initialAngle = angle;
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        
                if (GameManager.get().getPlayerX() > getX()) {
                    accelerationAtAngle(initialAngle+45);
                }
                else{
                    accelerationAtAngle(initialAngle-45);
                }

    }

    /* (non-Javadoc)
     * @see com.github.lhrb.nemo.actors.shots.Shots#perish()
     */
    @Override
    public void perish() {

        //remove actor after 1 sec
        addAction(Actions.delay(5));
        addAction(Actions.after(Actions.removeActor()));
    }
}
