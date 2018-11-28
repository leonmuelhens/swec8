/**
 * 
 */
package com.github.lhrb.nemo.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

/**
 * @author exa
 *
 */
public class GuiManager {
    
    private static GuiManager guiMng;
    private LabelStyle labelStyle;
    private TextButtonStyle txtBtnStyle;
    
    private GuiManager() {
            FreeTypeFontGenerator fontGenerator = 
                new FreeTypeFontGenerator(Gdx.files.internal("font/5x5_pixel.ttf"));
            FreeTypeFontParameter fontParameters = new FreeTypeFontParameter();
            fontParameters.size = 48;
            fontParameters.color = Color.WHITE;
            fontParameters.borderWidth = 2;
            fontParameters.borderColor = Color.BLACK;
            fontParameters.borderStraight = true;
            fontParameters.minFilter = TextureFilter.Linear;
            fontParameters.magFilter = TextureFilter.Linear;

            BitmapFont customFont = fontGenerator.generateFont(fontParameters);

            labelStyle = new LabelStyle();
            labelStyle.font = customFont;
            
            txtBtnStyle = new TextButtonStyle();

            Texture   buttonTex   = new Texture( Gdx.files.internal("button.png") );
            NinePatch buttonPatch = new NinePatch(buttonTex, 24,24,24,24);
            txtBtnStyle.up    = new NinePatchDrawable( buttonPatch );
            txtBtnStyle.font      = customFont;
            txtBtnStyle.fontColor = Color.BLACK;
    }
    
    public static GuiManager getInstance() {
        if(guiMng == null) {
            guiMng = new GuiManager();
        }
        return guiMng;
    }
    
    public LabelStyle getLabelStyle() {
        return labelStyle;
    }
    
    public TextButtonStyle getTxtBtnStyle() {
        return txtBtnStyle;
    }
    
    
}
