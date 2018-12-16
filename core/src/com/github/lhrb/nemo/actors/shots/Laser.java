package com.github.lhrb.nemo.actors.shots;


import com.github.lhrb.nemo.util.AnimationLoader;

public class Laser extends Shots {

    public Laser(float x, float y, float angle) {
        super(x, y, angle);

        setAnimation(AnimationLoader.get().texture("SchussLaser.png"));      
        setSpeedMax(1600);
        setAcceleration(30000);
        setShapePolygon(8);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

}
