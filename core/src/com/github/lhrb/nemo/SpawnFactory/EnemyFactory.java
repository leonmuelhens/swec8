package com.github.lhrb.nemo.SpawnFactory;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.github.lhrb.nemo.actors.EnemyActor;
import com.github.lhrb.nemo.actors.enemies.EnemyOne;
import com.github.lhrb.nemo.actors.enemies.EnemyThree;
import com.github.lhrb.nemo.actors.enemies.EnemyTwo;

import java.util.Random;

public class EnemyFactory {

    // alles friendly
    private float gameTime, timeLastSpawn;
    private Stage gameStage;
    private float spawnRate;
    private int randSpawnPossibilityStart = 1;
    private int randSpawnPossibility;
    
    private int level = 0;
    
    private byte spawnB[][] = {{50,80},
                               {40,75},
                               {30,70}};

    public EnemyFactory(Stage gameStage) {
        gameTime = 0;
        this.gameStage = gameStage;

        // first spawn is delayed + 1 second
        timeLastSpawn = 1;
    }

    private void spawnEnemy() {
        Random unitRand = new Random();
        int randomInt = unitRand.nextInt(100);

        EnemyActor newEnemy;

        if(randomInt <= spawnB[level][0]) {
            newEnemy = new EnemyOne(gameStage);
        } else if (randomInt <= spawnB[level][1]) {
            newEnemy = new EnemyTwo(gameStage);
        } else {
            newEnemy = new EnemyThree(gameStage);
        }

        Random xCoord = new Random();
        float x = xCoord.nextInt((int) gameStage.getWidth()- (int) newEnemy.getWidth() ) + 1;
        float y = gameStage.getHeight();

        newEnemy.setPosition(x,y);
        gameStage.addActor(newEnemy);
    }

    
    private void modifySpawnRate() {
        //-(atan(0.8x-3))+1.9
        // x = 9 equals 3 min of game
        // x = 0.05 equals 1 second

        //here we get the x value for atan function for time passed
        float x = (gameTime /2 / 10);

        // here we get the y value which will modify the spawn rate!
        // we can just modify this function to change the spawnrate
        double y = -Math.atan((0.8F * x) -3)+1.9;
        spawnRate = (float)y;

        // SpawnPossibility is calculated with gametime. As x = 9 is 3 min, ever min the
        // random spawnPossibility is incremented. As we do this with int, this is not exactly
        // every min, but more or less if we find a better function, go ahead
        randSpawnPossibility = ((int) x/3) +randSpawnPossibilityStart;
    }
    

    private void spawnAdditionalRandom() {
        // Random Spawn minimum: every 0.5 seconds
        // Random Calculated: every 0.5 seconds
        // Possibility to spawn if both values fit: Possibility:1000
        // Possibility starts at value X and is calculated in modifySpawnRate on Levelbase
        if ( gameTime % 1 >= 0.5 && 0.5 - (gameTime - timeLastSpawn) < 0) {
            Random rand = new Random();
            int randomInt = rand.nextInt(1000);

            if(randomInt <= randSpawnPossibility) {
                spawn(true);
                //System.out.println("Spawned a random Unit");
            }
        }
    }

    private void spawn(boolean randomUnit) {
        // Spawns if time since last spwan is higher than the actual spawnrate
        // Or if the spawn is called due to random Unit Spawn
        if (spawnRate - (gameTime - timeLastSpawn) < 0 || randomUnit) {
            spawnEnemy();

            if (!randomUnit) {
                timeLastSpawn = gameTime;
                //System.out.println("Spawned a unit");
            }
        }
    }

    // This is the method called by level screens to spawn enemies
    public void continueManufacture(float delta) {
        spawn(false);
        gameTime +=delta;
        modifySpawnRate();
        spawnAdditionalRandom();
    }
    
    public void setLevel(int lvl) {
        if(lvl > 2) return;
        this.level = lvl;
        //code to set randSpawnPossibility -/Start
    }
    
    public void setStage(Stage stage) {
        this.gameStage = stage;
    }
    
}