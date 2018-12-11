package com.github.lhrb.nemo.actors.shots;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.github.lhrb.nemo.actors.ActorPrefab;
import com.github.lhrb.nemo.actors.PhysicalActor;
import com.github.lhrb.nemo.actors.weapons.Weapon;
import com.github.lhrb.nemo.util.AnimationLoader;
import com.github.lhrb.nemo.util.SoundManager;

import java.util.Random;

public class Bomb extends Shots {

    private float explodeY;

    public Bomb(float x, float y, Stage stage, float angle) {
        super(x, y, stage, angle);

        setAnimation(AnimationLoader.get().texture("powerup_bombe.png"));
        setSpeedMax(100);
        setAcceleration(30000);
        setShapePolygon(8);

        explodeY = new Random().nextInt((int)(getStage().getHeight()*0.7));

        System.out.println("BOMB will explode at " + explodeY);
    }

    public Bomb(float x, float y, Stage stage, float angle, float explodeY) {
        super(x, y, stage, angle);

        setAnimation(AnimationLoader.get().texture("powerup_bombe.png"));
        setSpeedMax(100);
        setAcceleration(30000);
        setShapePolygon(8);

        this.explodeY = explodeY;
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        // Reliable explosion with margin of [-2;+2]
        if (getY() > explodeY-2 && getY() < explodeY+2) {
            System.out.println("BOOM!");
            SoundManager.getInstance().playSound("explosion");

            //code below is bad
            new PhysicalActor(getX(), getY(), getStage())
                    .setAnimation(AnimationLoader.get().animation(
                            "explosion.png", 6, 6, 0.05f, false));

            remove();
        }
    }

}
