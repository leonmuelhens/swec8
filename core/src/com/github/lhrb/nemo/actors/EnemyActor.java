/**
 * 
 */
package com.github.lhrb.nemo.actors;

import java.util.Random;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.github.lhrb.nemo.GameManager;
import com.github.lhrb.nemo.SpawnFactory.PowerUPFactory;
import com.github.lhrb.nemo.actors.shots.Shots;
import com.github.lhrb.nemo.util.AnimationLoader;
import com.github.lhrb.nemo.util.SoundManager;


public class EnemyActor extends PhysicalActor implements Existence, Removable{
    
    private int hp;
    private int scoreValue;

    public EnemyActor() {
        super();
        setCharacteristics();
    }
        
    public EnemyActor(float x, float y, Stage stage) {
        super(x, y, stage);
        setCharacteristics();
    }
    
    
    protected void setCharacteristics() {}

    @Override
    public void perish() {        
        
        GameManager.get().addScore(scoreValue);
        SoundManager.getInstance().playSound("explosion");
        //code below is bad
        new ActorPrefab(getX(), getY(), getStage())
                .setAnimation(AnimationLoader.get().animation(
                        "explosion.png", 6, 6, 0.05f, false));
        //end
        Random rand = new Random();
        if(rand.nextInt(10) <= 1) { // 20% chance to drop
            PowerUPFactory.spawnPU(getX(), getY(), getStage());
        }
        remove();        
    }

    @Override
    public void collision(CollisionEvent col) {
       if(col == null) return;
       if(col.getSource() instanceof Shots) {
           hp -= 1;
           setColor(255, 0, 0, hp * 0.4f);
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
    }
    
    protected void decreaseHp() {
        hp--;
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
        // TODO Auto-generated method stub
        this.remove();
    }

}
