package com.github.lhrb.nemo.SpawnFactory;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.github.lhrb.nemo.actors.EnemyOne;

import java.util.Date;
import java.util.Random;

public class EnemyFactory {

    private int level;
    private boolean enemyOne, enemyTwo, enemyThree;
    private final Date dateTimeStarted = new java.util.Date();
    private Date dateLastSpawn = new java.util.Date();
    private float timePassed, timeSpawnPassed;
    private Stage gameStage;

    private float spawnRate;
    private final int startSpawnRate;




    public EnemyFactory(int level, Stage gameStage) {
        this.level = level;
        timePassed = 0;
        enemyOne = true;
        enemyTwo = false;
        enemyThree = false;
        this.gameStage = gameStage;
        timeSpawnPassed = 2000;
        startSpawnRate = 1000;
    }

    public void spawnEnemies() {
        if (spawnRate - timeSpawnPassed > 1) {
            Random rand = new Random();
            EnemyOne enemy = new EnemyOne(rand.nextInt((int) gameStage.getWidth()) + 1,gameStage.getHeight(),gameStage);
            dateLastSpawn = new java.util.Date();
        }
    }

    public void calculateTimePassed() {
        java.util.Date now = new java.util.Date();
        timePassed = (float)((now.getTime() - this.dateTimeStarted.getTime()) );
        timeSpawnPassed = (float) ((now.getTime() - this.dateLastSpawn.getTime()));
    }

    public void modifySpawnRate() {
        //atan(0.8x-5)+2
        // x = 9 equals 3 min of game
        // x = 0.05 equals 1 second
        double pi = Math.PI;

        calculateTimePassed();
        float timePassedInSeconds = timePassed/1000F;

        //here we get the x value for atan function for time passed
        float x = (timePassedInSeconds /2 /10);
        //System.out.println("X: " + x);

        // here we get the y value which will modify the spawn rate!
        // we can just modify this function to change the spawnrate
        double y = Math.atan((0.8F * x) -3)+2;
        //System.out.println("Y: " + y);
        spawnRate = (float)y * startSpawnRate;
        System.out.println("Spawnrate:" + spawnRate);
        //System.out.println("spawnrate: " + y * spawnRate);
    }

    // This is the method called by level screens to spawn enemies
    public void continueManufacture() {
        calculateTimePassed();
        spawnEnemies();
        modifySpawnRate();
    }




}
