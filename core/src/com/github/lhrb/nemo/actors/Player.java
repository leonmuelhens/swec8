/**
 * 
 */
package com.github.lhrb.nemo.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.github.lhrb.nemo.GameManager;
import com.github.lhrb.nemo.KillingNemo;
import com.github.lhrb.nemo.actors.powerups.CActor;
import com.github.lhrb.nemo.actors.powerups.CType;
import com.github.lhrb.nemo.actors.shots.Shots;
import com.github.lhrb.nemo.actors.weapons.Weapon;
import com.github.lhrb.nemo.actors.weapons.WeaponLaser;
import com.github.lhrb.nemo.actors.weapons.WeaponNormal;
import com.github.lhrb.nemo.actors.weapons.WeaponSpread;
import com.github.lhrb.nemo.screen.GameOverScreen;
import com.github.lhrb.nemo.util.AnimationLoader;
import com.github.lhrb.nemo.util.PropertyListener;
import com.github.lhrb.nemo.util.SoundManager;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import com.github.lhrb.nemo.screen.LevelScreen;


/**
 * Simple player Implementation
 * @author exa
 * 
 */
public class Player extends PhysicalActor implements PropertyListener, Existence{

    private PropertyChangeSupport changes = new PropertyChangeSupport(this);

    // Weapon Vars
    private Weapon weapon;

    // PowerUp Vars
    private float powerupTimer; // in seconds
    private CActor bomb;
    private CActor powerup;


    // Label Vars
    private int life;
    private int score;

    // Drawables
    private Stack stack;
    private Image weaponLayer;
    private Image powerupLayer;
    private Image bombLayer;

    // Other Vars
    private int multiplier = 1;
    private boolean gotHit;
    private boolean invincible;
    private float hitDelta;

    public Player(float x, float y, Stage stage) {
        super(x,y,stage);

        // Characteristics
        setAnimation(AnimationLoader.get().animation("player_animation_feuer.png", 1, 3, 0.1f, true));
        setAcceleration(100000);
        setSpeedMax(500);
        setDeceleration(100000);
        life = 3;
        score = 0;

        // Weapon Initialization
        weapon = new WeaponNormal(stage);

        // Powerup Initialization
        powerup = new CActor(CType.None);
        bomb = new CActor(CType.None);
        powerupTimer = 0;
        invincible = false;

        // Collision managers
        setShapePolygon(8);
        gotHit = false;

        // Drawables Initialization
        powerupLayer = new Image();
        weaponLayer = new Image();
        bombLayer = new Image();
        stack = new Stack(bombLayer, powerupLayer, weaponLayer);
        stack.setSize(78f, 93f);
        addActor(stack);

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
    
    private void setVisuals(CType type) {
        switch(type) {
        case Shield: 
            powerupLayer.setDrawable(AnimationLoader.get()
                     .drawable("active_powerup_shield.png"));
            break;
        case Star:
            powerupLayer.setDrawable(AnimationLoader.get()
                    .drawable("active_powerup_star.png"));
            break;
        case Bomb:
            bombLayer.setDrawable(AnimationLoader.get()
                    .drawable("active_powerup_bomb.png"));
            break;
        case Normal:
            weaponLayer.setDrawable(AnimationLoader.get()
                    .drawable("active_weapon_normal.png"));
            break;
        case Laser:
            weaponLayer.setDrawable(AnimationLoader.get()
                    .drawable("active_weapon_laser.png"));
            break;
        case Spread:
            weaponLayer.setDrawable(AnimationLoader.get()
                    .drawable("active_weapon_spread.png"));
            break;
        default: 
            powerupLayer.setDrawable(null);
            break;
        }
        
    }

    public boolean isInvincible() {
        return invincible;
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
    
    public void addScore(int enemyScore) {
        if ( powerup != null && powerup.getType() == CType.Multiplicator) {
            multiplier = 3;
        } else {
            multiplier = 1;
        }
        changes.firePropertyChange("score", score, (score += (multiplier * enemyScore)));
    }

    private void calculateTimer(float delta) {
        
               
    }

    private void manageInputs() {
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
            if (bomb != null && bomb.getType() == CType.Bomb) {
                // hier fehlt noch eine animation
                bombLayer.setDrawable(null);
                for (Actor a : getStage().getActors()) {
                    if (a instanceof EnemyActor) {
                        ((EnemyActor) a).perish();
                    }
                }
                changeBomb(CType.None);
            }
        }

        /* Zum Vereinfachen der Waffentests!
        if(Gdx.input.isKeyPressed(Keys.F1)) {
            weapon = new WeaponNormal(getStage());
            changes.firePropertyChange("wpn", null, CType.Normal);
        }
        if(Gdx.input.isKeyPressed(Keys.F2)) {
            weapon = new WeaponSpread(getStage());
            changes.firePropertyChange("wpn", null, CType.Spread);
        }
        if(Gdx.input.isKeyPressed(Keys.F3)) {
            weapon = new WeaponLaser(getStage());
            changes.firePropertyChange("wpn", null, CType.Laser);
        }*/
    }
 
    
    /**
     * input handling
     */
    @Override
    public void act(float delta) {
        super.act(delta);
        weapon.act(delta);
        
        changes.firePropertyChange( "shottimer", -1f, (1f-weapon.getCooldown()) );
        
        if (gotHit) hitAnimation(delta);

        calculateTimer(delta);

        if (powerup != null && powerup.getType() != CType.None
                && powerup.getType() != CType.Bomb) {
            powerupTimer -= delta;
            
            changes.firePropertyChange("poweruptimer", -1f, powerupTimer/20f );
            
            if (powerupTimer <= 0 ) {
                changePowerup(CType.None);
            }
        }

        manageInputs();

        applyPhysics(delta);
        setBoundToWorld();
    
    }

    
    @Override
    public void perish() {
        ((LevelScreen)KillingNemo.getActiveScreen()).switchScreen(new GameOverScreen());;        
    }

    @Override
    public void collision(CollisionEvent col) {
        if (getStage() == null) {
            System.out.println("stage null"); // debug flag
            return;
        }

        if (col.getDestiny() != null && col.getDestiny() instanceof CActor) {
            CType type = ((CActor)col.getDestiny()).getType();
            switch(type) {
                case Heart:
                    changes.firePropertyChange("health", life, ++life);
                    break;
                case Normal:
                    weapon = new WeaponNormal(getStage());
                    setVisuals(CType.Normal);
                    changes.firePropertyChange("wpn", null, CType.Normal);
                    break;
                case Spread:
                    weapon = new WeaponSpread(getStage());
                    setVisuals(CType.Spread);
                    changes.firePropertyChange("wpn", null, CType.Spread);
                    break;
                case Laser:
                    weapon = new WeaponLaser(getStage());
                    setVisuals(CType.Laser);
                    changes.firePropertyChange("wpn", null, CType.Laser);
                    break;
                case Bomb:
                    changeBomb(type);
                    break;
                default:
                    changePowerup(type);
                    break;                        
            }
            return;
        }

        if (col.getSource() instanceof Shots && powerup != null && powerup.getType() == CType.Shield) {
            return;
        }
        
        GameManager.get().removeEnemiesAndShots(getStage());

        if (!gotHit) {
            gotHit = true;
            hitDelta = 0;
            SoundManager.getInstance().playSound("hit");
            changes.firePropertyChange("health", life, --life);
            if (life <= 0) {
                perish();
            }
            changes.firePropertyChange("poweruptimer", powerupTimer, 0f );
            changePowerup(CType.None);
            bombLayer.setDrawable(null);
            changeBomb(CType.None);
        }
    }

    private void changeBomb(CType changeBomb) {
        if (changeBomb == null) return;
        changes.firePropertyChange("bomb",bomb.getType(),changeBomb);
        bomb.setType(changeBomb);
        setVisuals(bomb.getType());
    }

    private void changePowerup(CType changePu) {
        if(changePu == null) return;
        if(changePu == CType.Bomb) return;
        invincible = false;
        
        if(changePu != CType.None) {
            powerupTimer = 20;          
            
            if (changePu == CType.Star) {
                invincible = true;
            }
        }

        changes.firePropertyChange("powerup",powerup.getType(),changePu);
        powerup.setType(changePu);
        
        setVisuals(powerup.getType());
    }


    
    public void setChildStage(Stage stage) {
        weapon.setStage(stage);
    }

    //Getter Statements for Highscore
    public int getScore() {
        return score;
    }


}
