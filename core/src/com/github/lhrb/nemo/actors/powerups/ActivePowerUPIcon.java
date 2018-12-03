package com.github.lhrb.nemo.actors.powerups;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.github.lhrb.nemo.actors.ActorPrefab;
import com.github.lhrb.nemo.util.AnimationLoader;

public class ActivePowerUPIcon extends ActorPrefab {
    public ActivePowerUPIcon(String type, Stage stage){
        super(0,0,stage);
        switch (type) {
                case "bomb":
                    setAnimation(AnimationLoader.loadTexture("powerup_bompe.png"));
                    break;
                case "multiplicator":
                    setAnimation(AnimationLoader.loadTexture("powerup_multiplicator.png"));
                    break;
                case "shield":
                    setAnimation(AnimationLoader.loadTexture("powerup_shield.png"));
                    break;
                case "star":
                    setAnimation(AnimationLoader.loadTexture("powerup_star.png"));
                    break;
                default:
                    // no texture
            }
        }
    }
