/**
 * 
 */
package com.github.lhrb.nemo.actors.enemies;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.github.lhrb.nemo.KillingNemo;
import com.github.lhrb.nemo.actors.MultiPartActor;
import com.github.lhrb.nemo.actors.Section;
import com.github.lhrb.nemo.actors.shots.Shots;
import com.github.lhrb.nemo.actors.weapons.Weapon;
import com.github.lhrb.nemo.screen.LevelDoneScreen;
import com.github.lhrb.nemo.util.AnimationLoader;

/**
 * @author exa
 *
 */
public class Kraken extends MultiPartActor {
    
    private float moveAngle;
    private ArrayList<Weapon> weapons;
    private Vector3 moveArea;
    
    public Kraken(Stage stage) {
        super(stage);
        init();
    }
    
    public Kraken(float x, float y, Stage stage) {
        super(x,y,stage);
        init();
    }
    
    private void init() {
        addPart(new Section(0, this, 75,50, 15, 500, 
                    AnimationLoader.get().texture("krake_kopf.png")) );
        addPart(new Section(1, this, 30,100, 5, 50, 
                AnimationLoader.get().texture("krake_flosse1.png")) );
        addPart(new Section(2, this, 0,50, 5, 50, 
                AnimationLoader.get().texture("krake_flosse2.png")) );
        addPart(new Section(3, this, 60,0, 5, 50, 
                AnimationLoader.get().texture("krake_flosse3.png")) );
        addPart(new Section(4, this, 140,0, 5, 50, 
                AnimationLoader.get().texture("krake_flosse4.png")) );
        addPart(new Section(5, this, 170,50, 5, 50, 
                AnimationLoader.get().texture("krake_flosse5.png")) );
        addPart(new Section(6, this, 170,100, 5, 50, 
                AnimationLoader.get().texture("krake_flosse6.png")) );
        
        setRotation(0);
        setAcceleration(1000);
        setSpeedMax(100);
        setDeceleration(0);
        
        weapons = new ArrayList<Weapon>();
        weapons.add(new KrakenAttack(getStage()));
        moveArea = new Vector3(50f, 550f, 425f);
    }

    /* (non-Javadoc)
     * @see com.github.lhrb.nemo.actors.MultiPartActor#handleCollision(com.github.lhrb.nemo.actors.Section)
     */
    @Override
    protected void handleCollision(Section section) {
        if(getPartSize() > 1 && section.getID() != 0) {
            if(section.getDmg()) {
                removePart(section);
                section.perish();
            }
        }else if(getPartSize() == 1) {
            if(section.getDmg()) {
                removePart(section);
                section.perish();
                addAction(Actions.removeActor());
                //level over
                KillingNemo.setActiveScreen(new LevelDoneScreen());
            }
        }
    }
    
    @Override
    public void act(float delta) {
        super.act(delta);

        if (getY() > moveArea.z) {
            moveAngle = 270;
        }
        else if (moveAngle == 270) {
            moveAngle = 0;
        }
        else if (moveAngle == 0 && getX() > moveArea.y) {
            moveAngle = 180;
        }
        else if (moveAngle == 180 && getX() < moveArea.x) {
            moveAngle = 0;
        }

        accelerationAtAngle(moveAngle);

        applyObjectPhysics(delta);

        if (getElapsedTime() > 2f) {
            for (Weapon w: weapons) {
                w.act(delta);
                w.fire(getX()+108,getY()-(getHeight()/2),270);
            }
        }
    }
    
    
    
    private class KrakenAttack extends Weapon{

        public KrakenAttack(Stage stage) {
            super(5f, stage);
            // TODO Auto-generated constructor stub
        }
        

        @Override
        public void fire(float x, float y, float angle) {
            if(isReady()) {
                new KrakenShotB(x,y,angle,stage);
                new KrakenShotS(x-125,y-30,angle,stage);
                new KrakenShotS(x-50,y,angle,stage);
                new KrakenShotS(x-25,y-50,angle,stage);
                new KrakenShotS(x+50,y-60,angle,stage);
                new KrakenShotS(x+125,y-10,angle,stage);
                resetCooldownTimer();
            }
            
        }
        
    }
    
    
    /**
     * Big projectile
     * @author exa
     *
     */
    private class KrakenShotB extends Shots {

        public KrakenShotB(float x, float y, float angle, Stage stage) {
            super(x, y, angle, stage);
            
            setAnimation(AnimationLoader.get().texture("krakenShotB.png"));      
            setSpeedMax(1200);
            setAcceleration(800);
            setShapePolygon(8);
            
        }
        
    }
    
    /**
     * Small projectiles
     * @author exa
     *
     */
    private class KrakenShotS extends Shots {

        public KrakenShotS(float x, float y, float angle, Stage stage) {
            super(x, y, angle, stage);
            
            setAnimation(AnimationLoader.get().texture("krakenShotS.png"));      
            setSpeedMax(1600);
            setAcceleration(1000);
            setShapePolygon(8);
            
        }
        
    }

}
