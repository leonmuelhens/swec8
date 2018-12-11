/**
 * 
 */
package com.github.lhrb.nemo.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.github.lhrb.nemo.util.AnimationLoader;
import com.github.lhrb.nemo.util.GuiManager;

/**
 * @author exa
 *
 */
public class HUD {
    private Table hud;
    
    private String score, hp, gametime;
    private Label scoreLbl, hpLbl, timeLbl;
    private Image wpnIcon, hpIcon;
    
    public HUD(float width, float height) {
        
        score ="testscore";
        hp = "testHp";
        gametime = "zeit";
        initLabels();
        
        Drawable test = new TextureRegionDrawable();
        wpnIcon = new Image();
        //wpnIcon.set
        
        hud = new Table();
        hud.setFillParent(true);
        //hud.setWidth(width);
        //hud.setHeight(height);

        // first Row: Level Indicator
        hud.add(timeLbl).width(75).pad(10);
        //hud.add(timeLbl).expandX().height(50).right().pad(10); // middle
        hud.row();

        // second row:Highscore indicatro
        hud.add().width(75).pad(10);
        hud.add(scoreLbl).expandX().height(50).right().pad(10);
        hud.row();

        // third row:
        // left: weapon
        // right: life + heart
        //hud.add(tableObjects.get("weapon")).expandY().width(75).bottom().pad(10);
        //hud.add(tableObjects.get("life")).expandX().pad(10).bottom().right();
        hud.debug();
    }
    
    private void initLabels() {
        LabelStyle style = GuiManager.getInstance().getLabelStyle();
        scoreLbl = new Label(score, style);
        hpLbl = new Label(hp, style);
        timeLbl = new Label(gametime, style);
    }
    
    public Table getHUD() {
        return hud;
    }
    

}
