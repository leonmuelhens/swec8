/**
 * 
 */
package com.github.lhrb.nemo.actors;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.github.lhrb.nemo.GameManager;
import com.github.lhrb.nemo.SpawnFactory.CollectibleFactory;
import com.github.lhrb.nemo.actors.particles.Explosion;
import com.github.lhrb.nemo.actors.shots.Shots;
import com.github.lhrb.nemo.util.SoundManager;


public class EnemyActor extends PhysicalActor implements Existence, Removable{
    
    private int hp;
    private int initialHp;
    private int scoreValue;
    private Explosion explosion;
    
    public EnemyActor() {
        super();
    }

    public EnemyActor(Stage stage) {
        super();
        setStage(stage);
        setCharacteristics();
    }
        
    public EnemyActor(float x, float y, Stage stage) {
        super(x, y, stage);
        setCharacteristics();
    }
    
    
    protected void setCharacteristics() {}

    @Override
    public void perish() {
        addScoreValue();
        SoundManager.getInstance().playSound("explosion");

        perishExplosion();

        Random rand = new Random();
        if(rand.nextInt(10) <= 1) { // 20% chance to drop
            CollectibleFactory.spawnC(getX(), getY(), getStage());
        }
        addAction(Actions.removeActor());       
    }

    public void addScoreValue() {
        GameManager.get().addScore(scoreValue);
    }

    protected void perishExplosion() {
        explosion = new Explosion();
        explosion.centerAtActor(this);
        Stage s = getStage();
        if(s != null) {
            getStage().addActor(explosion);
        }
        explosion.start();
    }


    @Override
    public void collision(CollisionEvent col) {
       if(col == null) return;
       if(col.getSource() instanceof Shots) {
           hp -= 1;
           updateVisualDamage();
           if(hp <= 0) {
               perish();
           }
       }
    }

    /**
     * @return the hp
     */
    protected int getHp() {
        return hp;
    }

    /**
     * @param hp the hp to set
     */
    protected void setHp(int hp) {
        this.hp = hp;
        initialHp = hp;
    }
    
    protected void decreaseHp() {
        hp--;
    }

    public void updateVisualDamage() {
        if (hp > 1) {
            setColor(1,(float)hp/initialHp,(float)hp/initialHp,1);
        }
        else if (hp == 1){
            setColor(1,0,0,1);
        } else {
            setColor(Color.WHITE.cpy().lerp(Color.BLACK,0.8f));
        }
    }

    /**
     * @return the scoreValue
     */
    protected int getScoreValue() {
        return scoreValue;
    }

    /**
     * @param scoreValue the scoreValue to set
     */
    protected void setScoreValue(int scoreValue) {
        this.scoreValue = scoreValue;
    }

    @Override
    public void destroy() {
        addAction(Actions.removeActor());
    }

}
