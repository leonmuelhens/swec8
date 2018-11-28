package com.github.lhrb.nemo.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.github.lhrb.nemo.util.AnimationLoader;

public class Background extends ActorPrefab {
    int yDelta;
    static float initialPosition;

    public Background(float x, float y, Stage stage, int level) {
        super(x,y,stage);
        String fileName;
        initialPosition = y;
        yDelta = 1200;

        switch (level) {
            case 1:
                fileName = "bg1.png";
                break;
            case 2:
                fileName = "bg2.png";
                break;
            case 3:
                fileName = "bg3.png";
                break;
            default:
                fileName = "bg1.png";
                break;
        }
        setAnimation(AnimationLoader.loadTexture(fileName));
        setAcceleration(200);
        setSpeedMax(200);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        System.out.println(getY());

        if (getY() <=  -yDelta) {
            setY(initialPosition);
        }

        accelerationAtAngle(270);
        applyPhysics(delta);
    }



}
