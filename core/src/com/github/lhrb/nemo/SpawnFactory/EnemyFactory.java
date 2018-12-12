package com.github.lhrb.nemo.SpawnFactory;

import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.Random;

public abstract class EnemyFactory {

    // alles friendly
    float gameTime, timeLastSpawn;
    Stage gameStage;
    float spawnRate;
    int randSpawnPossibilityStart;
    int randSpawnPossibility;

    EnemyFactory(Stage gameStage) {
        gameTime = 0;
        this.gameStage = gameStage;

        // first spawn is delayed + 1 second
        timeLastSpawn = 1;
    }

    protected abstract void spawnEnemy();

    public abstract void modifySpawnRate();

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
                System.out.println("Spawned a random Unit");
            }
        }
    }

    public void spawn(boolean randomUnit) {
        // Spawns if time since last spwan is higher than the actual spawnrate
        // Or if the spawn is called due to random Unit Spawn
        if (spawnRate - (gameTime - timeLastSpawn) < 0 || randomUnit) {
            spawnEnemy();

            if (!randomUnit) {
                timeLastSpawn = gameTime;
                System.out.println("Spawned a unit");
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
}