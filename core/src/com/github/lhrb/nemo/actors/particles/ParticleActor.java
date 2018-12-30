/**
 * 
 */
package com.github.lhrb.nemo.actors.particles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

/**
 * @author exa
 *
 */
public class ParticleActor extends Group {
    
    private ParticleEffect   effect;
    private ParticleRenderer renderer;
    
    private class ParticleRenderer extends Actor {
        
        private ParticleEffect effect;

        ParticleRenderer(ParticleEffect e) {  
            effect = e;  
        }

        public void draw(Batch batch, float parentAlpha) {  
            effect.draw(batch);  
        }
        
    }

    public ParticleActor(String file, String dir, Actor actor) {
        super();
        effect = new ParticleEffect();
        effect.load(Gdx.files.internal(file), Gdx.files.internal(dir));
        renderer = new ParticleRenderer(effect);
        this.addActor( renderer );
        
        centerAtActor(actor);
        actor.getStage().addActor(this);
        start();
    }

    public void start(){  
        effect.start(); 
    }

    public void stop(){  
        effect.allowCompletion();  
    }

    public boolean isRunning() {
        return !effect.isComplete();  
    }

    public void centerAtActor(Actor actor) {
        setPosition( actor.getX() + actor.getWidth()/2 , actor.getY() + actor.getHeight()/2 );
    }

    public void act(float dt) {
        super.act( dt );
        effect.update( dt );
        
        if ( effect.isComplete() && !effect.getEmitters().first().isContinuous() ) {
            effect.dispose();
            this.remove();
        }
    }

    public void draw(Batch batch, float parentAlpha) {   
        super.draw( batch, parentAlpha );
    }

}
