/**
 * 
 */
package com.github.lhrb.nemo.actors.enemies;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.github.lhrb.nemo.KillingNemo;
import com.github.lhrb.nemo.actors.CollisionEvent;
import com.github.lhrb.nemo.actors.MultiPartActor;
import com.github.lhrb.nemo.actors.OverlayActor;
import com.github.lhrb.nemo.actors.Section;
import com.github.lhrb.nemo.actors.shots.Shots;
import com.github.lhrb.nemo.actors.weapons.Weapon;
import com.github.lhrb.nemo.util.AnimationLoader;
import com.github.lhrb.nemo.screen.LevelScreen;

import java.util.ArrayList;
import java.util.List;

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
        addPart(new Section(0, this, 75,50, 5, 500,
                    AnimationLoader.get().texture("krake_kopf.png")) );
        addPart(new Section(1, this, 30,100, 1, 50,
                AnimationLoader.get().texture("krake_flosse1.png")) );
        addPart(new Section(2, this, 0,50, 1, 50,
                AnimationLoader.get().texture("krake_flosse2.png")) );
        addPart(new Section(3, this, 60,0, 1, 50,
                AnimationLoader.get().texture("krake_flosse3.png")) );
        addPart(new Section(4, this, 140,0, 1, 50,
                AnimationLoader.get().texture("krake_flosse4.png")) );
        addPart(new Section(5, this, 170,50, 1, 50,
                AnimationLoader.get().texture("krake_flosse5.png")) );
        addPart(new Section(6, this, 170,100, 1, 50,
                AnimationLoader.get().texture("krake_flosse6.png")) );
        
        setRotation(0);
        setAcceleration(1000);
        setSpeedMax(100);
        setDeceleration(0);
        
        weapons = new ArrayList<Weapon>();
        weapons.add(new KrakenAttack(getStage()));
        weapons.add(new TentacleAttack(getStage(), this));
        weapons.add(new InkAttack(getStage()));
        moveArea = new Vector3(50f, 550f, 425f);
    }

    /* (non-Javadoc)
     * @see com.github.lhrb.nemo.actors.MultiPartActor#handleCollision(com.github.lhrb.nemo.actors.Section)
     */
    /*@Override
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
                // bad practice just for testing
                try {
                    ((LevelScreen)KillingNemo.getActiveScreen()).removeScreen();
                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }*/
    
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
    public void removePart(Section e) {
        super.removePart(e);
        resetPosition();
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
        private boolean action = true;
        private float distance, time, y;
        
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
                if(elapsedTime > 1.4f) {
                    parent.resetPosition();
                    resetCooldownTimer();
                    elapsedTime = 0f;
                    action = true;
                }
                else if(elapsedTime > 1.2f){
                    for(Section s : parent.getPartCollection()) {
                        if(s.getID() != 0) {
                            s.setPosition(1000, 1000);
                        }
                    }
                }
                
                else if(action){                  
                    int size = parent.getPartSize();
                    if(size > 1) {
                        distance = (float)400/(size-1);
                        List<Section> sl = parent.getPartCollection().subList(1, size);
                        for(int i = 0; i < sl.size(); i++) {
                            Section s = sl.get(i);
                            s.setPosition(1000, 1000);
                            time = (float)(1f-(0.15*i));
                            y = 50f + (distance*i);
                            addAction(new TentacleAction(time, y, s));                            
                        }                 
                    action = false;
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

    private class InkAttack extends Weapon {

        public InkAttack(Stage stage) {
            super(9f, stage);
        }

        @Override
        public void fire(float x, float y, float angle) {
            // TODO Auto-generated method stub
            if (isReady() && getElapsedTime() > 6) {
                new InkBall(x,y,angle,stage);
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

    private class InkBall extends Shots {

        public InkBall(float x, float y, float angle, Stage stage) {
            super(x, y, angle, stage);

            this.setAnimation(AnimationLoader.get().texture("tinte_small.png"));
            setSpeedMax(1200);
            setAcceleration(800);
            setShapePolygon(8);
        }

        @Override
        public void act(float delta) {
            super.act(delta);
            setWidth(getWidth() * 1.02f);
            setHeight(getHeight() * 1.02f);
        }

        @Override
        public void collision(CollisionEvent col) {
            OverlayActor inked = new OverlayActor("tinte.png",getStage(),5f);
        }
    }

}
