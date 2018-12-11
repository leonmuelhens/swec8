/**
 * 
 */
package com.github.lhrb.nemo.ui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.github.lhrb.nemo.actors.Player;
import com.github.lhrb.nemo.actors.powerups.CType;
import com.github.lhrb.nemo.util.AnimationLoader;
import com.github.lhrb.nemo.util.GuiManager;

/**
 * @author exa
 *
 */
public class HUD implements PropertyChangeListener{
    
    private Player player;
    
    private Table hud;
    
    private String score, hp, gametime;
    private Label scoreLbl, hpLbl, timeLbl;
    private Image wpnIcon;
    private ImageButton hpBtn;
    
    
    private HashMap<CType, Drawable> wpnIcons;
    
    public HUD(Player player) {
        
        this.player = player;
        player.addPropertyChangeListener(this);
        
        score ="testscore";
        hp = "3";
        gametime = "zeit";
        
        initLabels();
        
        wpnIcons = new HashMap<CType, Drawable>();
        wpnIcons.put(CType.Normal, new TextureRegionDrawable(AnimationLoader.get()
                                       .texture("IconNormal.png").getKeyFrame(0)));
        wpnIcons.put(CType.Spread, new TextureRegionDrawable(AnimationLoader.get()
                                       .texture("IconSpread.png").getKeyFrame(0)));
        wpnIcons.put(CType.Laser, new TextureRegionDrawable(AnimationLoader.get()
                                        .texture("IconLaser.png").getKeyFrame(0)));
        
        
        //TextureRegionDrawable test = new TextureRegionDrawable(AnimationLoader.get().texture("IconNormal.png").getKeyFrame(0));
        wpnIcon = new Image(wpnIcons.get(CType.Normal));
        //hpIcon = new Image(new TextureRegionDrawable(AnimationLoader.get()
        //                            .texture("heart.png").getKeyFrame(0)));
        hpBtn = new ImageButton(new TextureRegionDrawable(AnimationLoader.get()
                                        .texture("heart.png").getKeyFrame(0)));
        hpBtn.add(hpLbl);
        
        hud = new Table();
        hud.setFillParent(true);
        
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
        hud.add(wpnIcon).expandY().width(75).bottom().pad(10);
        hud.add(hpBtn).expandX().pad(10).bottom().right();
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
    
    public void setWpnIcon(CType type) {
        wpnIcon.setDrawable(wpnIcons.get(type));
    }
    
    public void setScore(String s) {
        scoreLbl.setText(s);
    }
   
    public void setHp(String s) {
        hpLbl.setText(s);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // TODO Auto-generated method stub
        
    }
    

}
