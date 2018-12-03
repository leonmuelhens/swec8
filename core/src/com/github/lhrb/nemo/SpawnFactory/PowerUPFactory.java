package com.github.lhrb.nemo.SpawnFactory;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.github.lhrb.nemo.actors.ActorPrefab;
import com.github.lhrb.nemo.actors.enemies.Enemy;
import com.github.lhrb.nemo.actors.powerups.*;

import java.util.Random;

public class PowerUPFactory {

    private int level;
    private Stage gameStage;
    private Enemy enemy;

    public PowerUPFactory(int level, Enemy enemy, Stage gameStage) {
        this.level = level;
        this.enemy=enemy;
        this.gameStage = gameStage;
    }

    public void spawnPowerUP(ActorPrefab actor) {

            actor.setPosition(enemy.getX(),enemy.getY());
            gameStage.addActor(actor);
    }

    public void levelOneSpawner() {
        Random rand = new Random();
        //if ((rand.nextInt(10)+1) < 9) {
            switch (rand.nextInt(5) + 1) {
                case 1:
                    PowerUPBomb bomb = new PowerUPBomb(gameStage);
                    spawnPowerUP(bomb);
                    break;
                case 2:
                    PowerUPHeart heart = new PowerUPHeart(gameStage);
                    spawnPowerUP(heart);
                    break;
                case 3:
                    PowerUPMultiplicator multi = new PowerUPMultiplicator(gameStage);
                    spawnPowerUP(multi);
                    break;
                case 4:
                    PowerUPShield shield = new PowerUPShield(gameStage);
                    spawnPowerUP(shield);
                    break;
                case 5:
                    PowerUPStar star = new PowerUPStar(gameStage);
                    spawnPowerUP(star);
                    break;
                default:
                    break;
            }
        //}
    }
// Left in for if we decide to change powerup spawn rate per level
    public void levelTwoSpawner() {

    }
    public void levelThreeSpawner() {

    }

    public void spawnLevel() {
        switch(level) {
            case 1:
                levelOneSpawner();
                break;
            case 2:
                levelTwoSpawner();
                break;
            case 3:
                levelThreeSpawner();
                break;
            default:
                levelOneSpawner();
                break;
        }
    }

}