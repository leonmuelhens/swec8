package com.github.lhrb.nemo.actors;

public class CollisionEvent {

    private PhysicalActor source, destiny;

    public CollisionEvent(PhysicalActor source, PhysicalActor destiny) {
        this.source = source;
        this.destiny = destiny;
    }


    public PhysicalActor getSource() {
        return source;
    }

    public PhysicalActor getDestiny() {
        return destiny;
    }
}
