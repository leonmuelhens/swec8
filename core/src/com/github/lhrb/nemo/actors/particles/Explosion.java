/**
 * 
 */
package com.github.lhrb.nemo.actors.particles;

import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * @author exa
 *
 */
public class Explosion extends ParticleActor {
    public Explosion(Actor actor) {
        super("particle/explosion.p", "particle/", actor);
    }
}
