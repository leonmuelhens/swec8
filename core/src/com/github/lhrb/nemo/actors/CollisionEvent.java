package com.github.lhrb.nemo.actors;

import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.EventObject;

public class CollisionEvent extends EventObject {

    private Actor source;
    private Actor destiny;

    public CollisionEvent(Actor source, Actor destiny) {
        super(source);
        this.source = source;
        this.destiny = destiny;
    }

    @Override
    public Actor getSource() {
        return source;
    }

    public Actor getDestiny() {
        return destiny;
    }
}
