/**
 * 
 */
package com.github.lhrb.nemo.actors;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.github.lhrb.nemo.AbstractGame;
import com.github.lhrb.nemo.GameManager;
import com.github.lhrb.nemo.KillingNemo;
import com.github.lhrb.nemo.actors.enemies.Enemy;
import com.github.lhrb.nemo.actors.powerups.*;
import com.github.lhrb.nemo.actors.shots.Shots;
import com.github.lhrb.nemo.actors.weapons.*;
import com.github.lhrb.nemo.screen.FirstLevelScreen;
import com.github.lhrb.nemo.screen.GameOverScreen;
import com.github.lhrb.nemo.util.AnimationLoader;
import com.github.lhrb.nemo.util.PropertyListener;

/**
 * Simple player Implementation
 * @author exa
 * 
 */
public class Player extends PhysicalActor implements PropertyListener{

    private PropertyChangeSupport changes = new PropertyChangeSupport(this);
    
    private Weapon weapon;
    private PowerUP powerup;
    private float powerupTimer;
    private int life;
    private int score;
    private int multiplier = 1;
    private boolean gotHit;
    private float hitDelta;
    public Player(float x, float y, Stage stage) {
        super(x,y,stage);

        setAnimation(AnimationLoader.get().animation("player_animation_feuer.png", 1, 3, 0.1f, true));

        setAcceleration(100000);
        setSpeedMax(500);
        setDeceleration(100000);
        life = 3;
        score = 0;

        weapon = new WeaponNormal(getStage());
        powerup = null;
        powerupTimer = 0;

        setShapePolygon(8);
        gotHit = false;
        
        /**
         * ATTENTION
         * this method does not provide any security mechanism
         */
        setWorldDimension(stage.getWidth(), stage.getHeight());
        GameManager.get().registerPlayer(this);
        
       
    }
    
    @Override
    public void addPropertyChangeListener(PropertyChangeListener l) {
        changes.addPropertyChangeListener(l);
    }
    
    @Override
    public void removePropertyChangeListener(PropertyChangeListener l) {
        changes.removePropertyChangeListener(l);
    }

    public PowerUP getPowerup() {
        return powerup;
    }

    private void hitAnimation(float delta) {
        if (hitDelta < 1.5f) {
            hitDelta += delta;

            if ((int) (hitDelta* 10) % 5 == 0 ) {
                if (this.getColor().a == 0) this.setOpacity(1);
                else this.setOpacity(0);
            }
        } else {
            gotHit = false;
            this.setOpacity(1);
        }
    }
    
    public void addScore(int p) {
        int factor = 1;
        if ( powerup.getType() == CType.Multiplicator) {
            factor = 3;
        }
        changes.firePropertyChange("score", score, (score += (factor * multiplier)));
    }
 
    
    /**
     * input handling
     */
    @Override
    public void act(float delta) {
        if (gotHit) hitAnimation(delta);
        if (powerup != null && powerup.getType() != CType.Bomb) {
            powerupTimer -= delta;
            System.out.println(powerupTimer);
            if (powerupTimer <= 0 ) {
                powerup.remove();
                powerup = null;
            }
        }


        super.act(delta);
        
        if(Gdx.input.isKeyPressed(Keys.LEFT)) {
            accelerationAtAngle(180);
        }
        if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
            accelerationAtAngle(0);
        }
        if(Gdx.input.isKeyPressed(Keys.UP)) {
            accelerationAtAngle(90);
        }
        if(Gdx.input.isKeyPressed(Keys.DOWN)) {
            accelerationAtAngle(270);
        }

        if(Gdx.input.isKeyPressed(Keys.SPACE)) {
            weapon.fire(getX()+(getWidth()/2), getY()+(getHeight()), 90);
        }

        if (Gdx.input.isKeyPressed(Keys.B)) {
            if (powerup != null && powerup.getType() == CType.Bomb) {
                // hier fehlt noch eine animation
                for (Actor a : AbstractGame.getGameStage().getActors()) {
                    if (a instanceof Enemy) {
                        ((Enemy) a).enemyDied(null,true);
                    }
                }
            }
        }

        // Zum Testen der Waffen! Sollte später über das gleiche System wie Power-Ups geregelt werden können
        if(Gdx.input.isKeyPressed(Keys.F1)) {
            weapon.remove();
            weapon = new WeaponNormal(getStage());
            changes.firePropertyChange("wpn", null, CType.Normal);
        }
        if(Gdx.input.isKeyPressed(Keys.F2)) {
            weapon.remove();
            weapon = new WeaponSpread(getStage());
            changes.firePropertyChange("wpn", null, CType.Spread);
        }
        if(Gdx.input.isKeyPressed(Keys.F3)) {
            weapon.remove();
            weapon = new WeaponLaser(getStage());
            changes.firePropertyChange("wpn", null, CType.Laser);
        }
        
        applyPhysics(delta);
        setBoundToWorld();
    
    }


    public void playerDied() {
        KillingNemo.setActiveScreen(new GameOverScreen());
    }

    /* (non-Javadoc)
     * @see com.github.lhrb.nemo.actors.PhysicalActor#collision()
     */
    @Override
    public void collision(CollisionEvent col) {
        if (getStage() == null) System.out.println("stage null");

        GameManager.get().removeEnemiesAndShots();

        if (!gotHit) {
            gotHit = true;
            hitDelta = 0;
            //life -= 1;
            changes.firePropertyChange("health", life, --life);
            if (life <= 0) {
                playerDied();
            }
        }
    }

    public void changePowerup(PowerUP pu) {
        if (pu.getType() != CType.Bomb){
            powerupTimer = 20;
        }
        if (powerup == null) {
            changes.firePropertyChange("powerup",null,pu.getType());

        } else {
            changes.firePropertyChange("powerup",powerup.getType(),pu.getType());
        }

        if (powerup != null)
            powerup.remove();
        powerup = pu;
    }

    public void collision(PowerUP pu){
        if (getStage() == null) System.out.println("stage null");
        if (pu.getType() == CType.Bomb){
            changePowerup(pu);
        }  else if (pu.getType() == CType.Heart){
            changes.firePropertyChange("health", life, ++life);
        }
        else {
            changePowerup(pu);
        }

    }

    public boolean multi() {
        if (powerup != null)
            return (powerup.getType() == CType.Multiplicator);
        return false;
    }
}
