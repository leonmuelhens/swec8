/**
 * 
 */
package com.github.lhrb.nemo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.github.lhrb.nemo.actors.Player;

/**
 * @author exa
 *
 *just add new LwjglApplication(new TestStage(), "Killing Nemo", 800, 600);
 * @com.github.lhrb.nemo.desktop.DesktopLauncher 
 */
public class TestStage extends Game {
    

    private Player player;
    private Stage stage;
    SpriteBatch batch;
    Texture img;
    TextureRegion test;

    /* (non-Javadoc)
     * @see com.badlogic.gdx.ApplicationListener#create()
     */
    @Override
    public void create() {
       stage = new Stage(new FitViewport(1200, 600));
       Gdx.input.setInputProcessor(stage);
       init();

    }
    
    private void init() {
        batch = new SpriteBatch();
        player = new Player( );
        img = new Texture( Gdx.files.internal("player_laser.png") );
        test = new TextureRegion(img);
        player.setTexture(img);
        //player = new Player( new Texture("player_standart.png") );
        player.setPosition(100, 100); 
        stage.addActor(player);
       
    }
    
    /* (non-Javadoc)
     * @see com.badlogic.gdx.Game#render()
     */
    @Override
    public void render() {
        
        float dt = Gdx.graphics.getDeltaTime();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
       
        
        stage.act(dt);
        stage.draw();
        
    }
    
    /* (non-Javadoc)
     * @see com.badlogic.gdx.Game#resize(int, int)
     */
    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub
        stage.getViewport().update(width, height, true);
    }
    
    public void update(float dt) {
        
    }
    
    @Override
    public void dispose() {
        stage.dispose();
    }
    

}
