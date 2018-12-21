/**
 * 
 */
package com.github.lhrb.nemo.actors.enemies;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.github.lhrb.nemo.GameManager;
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
        weapons.add(new TentacleAttack(getStage(), this));
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
    
    public void resetPosition() {
        for(Section s: getPartCollection()) {
            switch(s.getID()) {
            case 0:
                s.setPosition(75, 50);
                break;
            case 1:
                s.setPosition(30, 100);
                break;
            case 2:
                s.setPosition(0, 50);
                break;
            case 3:
                s.setPosition(60, 0);
                break;
            case 4:
                s.setPosition(140, 0);
                break;
            case 5:
                s.setPosition(170, 50);
                break;
            case 6:
                s.setPosition(170, 100);
                break;
            default:
                break;
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
                w.fire(getX()+108,getY()+50,270);
            }
        }
    }
    
    
    
    
    private class TentacleAttack extends Weapon{
        
        private Kraken parent;
        private float elapsedTime = 0f;
        
        public TentacleAttack(Stage stage, Kraken parent) {
            super(8f, stage);
            this.parent = parent;
            // TODO Auto-generated constructor stub
        }

        @Override
        public void fire(float x, float y, float angle) {
            // TODO Auto-generated method stub
        }
        
        @Override
        public void act(float delta){
            if(!isReady()) {
                cooldownTimer += delta;
            }else {
                elapsedTime += delta;
                if(elapsedTime > 2f) {
                    parent.resetPosition();
                    resetCooldownTimer();
                    elapsedTime = 0f;
                }
                else if(elapsedTime > 1f){
                    for(Section s : parent.getPartCollection()) {
                        if(s.getID() != 0) {
                            s.setVisible(true);
                        }
                    }
                }
                else if(elapsedTime > 0.7f) {
                    parent.getPartCollection().get(6).setVisible(true);
                    parent.getPartCollection().get(6)
                    .setPos(GameManager.get().getPlayerX(), 50f);
                }
                else if(elapsedTime > 0.6f) {
                    parent.getPartCollection().get(5).setVisible(true);
                    parent.getPartCollection().get(5)
                    .setPos(GameManager.get().getPlayerX(), 125f);
                }
                else if(elapsedTime > 0.5f) {
                    parent.getPartCollection().get(4).setVisible(true);
                    parent.getPartCollection().get(4)
                    .setPos(GameManager.get().getPlayerX(), 200f);
                }
                else if(elapsedTime > 0.4f) {
                    parent.getPartCollection().get(3).setVisible(true);
                    parent.getPartCollection().get(3)
                    .setPos(GameManager.get().getPlayerX(), 275f);
                }
                else if(elapsedTime > 0.3f) {
                    parent.getPartCollection().get(2).setVisible(true);
                    parent.getPartCollection().get(2)
                    .setPos(GameManager.get().getPlayerX(), 350f);
                }
                else if(elapsedTime > 0.2f) {
                    parent.getPartCollection().get(1).setVisible(true);
                    parent.getPartCollection().get(1)
                          .setPos(GameManager.get().getPlayerX(), 400f);
                    
                }
                else {
                    for(Section s : parent.getPartCollection()) {
                        if(s.getID() != 0) {
                            s.setVisible(false);
                        }
                    }
                }
                
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
