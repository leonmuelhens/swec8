package com.github.lhrb.nemo.SpawnFactory;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.github.lhrb.nemo.actors.ActorPrefab;
import com.github.lhrb.nemo.actors.powerups.*;

import java.util.Random;

public class PowerUPFactory {

    private int level;
    private float gameTime, timeLastSpawn;
    private Stage gameStage;

    private float spawnRate;

    public PowerUPFactory(int level, Stage gameStage) {
        this.level = level;
        gameTime = 0;
        this.gameStage = gameStage;

        // first spawn is delayed + 1 second
        timeLastSpawn = 1;
    }

    public void spawnPowerUP(ActorPrefab actor) {
        if (spawnRate - (gameTime - timeLastSpawn) < 0) {
            Random rand = new Random();
            float x = rand.nextInt((int) gameStage.getWidth()- (int) actor.getWidth() ) + 1;
            float y = gameStage.getHeight();

            actor.setPosition(x,y);
            gameStage.addActor(actor);

            timeLastSpawn = gameTime;
        }
    }

    public void levelOneSpawner() {
        Random rand = new Random();

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
    }

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

    private void updatePossibleEnemies() {

    }


    public void modifySpawnRate() {
        //-(atan(0.8x-3))+1.9
        // x = 9 equals 3 min of game
        // x = 0.05 equals 1 second

        //here we get the x value for atan function for time passed
        float x = (gameTime /2 / 10);
        //System.out.println("X: " + x);

        // here we get the y value which will modify the spawn rate!
        // we can just modify this function to change the spawnrate
        double y = -Math.atan((0.8F * x) -3)+1.9;
        //System.out.println("Y: " + y);
        spawnRate = (float)y;
        //System.out.println("Spawnrate:" + spawnRate);
        //System.out.println("spawnrate: " + y * spawnRate);
    }

    // This is the method called by level screens to spawn enemies
    public void continueManufacture(float delta) {
        spawnLevel();
        gameTime +=delta;
        modifySpawnRate();
        updatePossibleEnemies();
    }



}