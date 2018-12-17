/**
 * 
 */
package com.github.lhrb.nemo.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

/**
 * @author exa
 *
 */
public class GuiManager {
    
    private static GuiManager guiMng;
    private LabelStyle labelStyle;
    private LabelStyle labelStyleBig;

    private TextButtonStyle txtBtnStyle;
    private TextButtonStyle txtBtnStyleLittle;
    private TextButtonStyle txtBtnStyleBig;

    private TextFieldStyle txtFldStyle;

    private GuiManager() {
        // Create Label Styles
        labelStyle = new LabelStyle(createFont(24),Color.WHITE);
        labelStyleBig = new LabelStyle(createFont(48),Color.WHITE);

        // Create button Styles
        txtBtnStyle = new TextButtonStyle(createTextButtonStyle(36));
        txtBtnStyleLittle = new TextButtonStyle(createTextButtonStyle(24));
        txtBtnStyleBig = new TextButtonStyle(createTextButtonStyle(48));

        //Create TextField Style
        txtFldStyle =new TextFieldStyle();
        txtFldStyle.font = new BitmapFont();
        txtFldStyle.fontColor = Color.WHITE;

    }


    private TextButtonStyle createTextButtonStyle(int fontSize) {
        return createTextButtonStyle(fontSize,Color.BLACK);
    }

    private TextButtonStyle createTextButtonStyle(int fontSize, Color color) {
        TextButtonStyle tmp = new TextButtonStyle();
        Texture   buttonTex   = new Texture( Gdx.files.internal("button.png") );
        NinePatch buttonPatch = new NinePatch(buttonTex, 10,10,8,8);
        tmp.up    = new NinePatchDrawable( buttonPatch );
        tmp.font      = createFont(fontSize);
        tmp.fontColor = Color.BLACK;
        return tmp;
    }

    // Creates font with size X and Color white as default
    private BitmapFont createFont(int fontSize) {
        return createFont(fontSize, Color.WHITE);
    }

    // Creates font with size X anc Color Y
    private BitmapFont createFont(int fontSize, Color color) {
        FreeTypeFontGenerator fontGenerator =
                new FreeTypeFontGenerator(Gdx.files.internal("font/pixelfj8pt1.normal.ttf"));
        FreeTypeFontParameter fontParameters = new FreeTypeFontParameter();
        fontParameters.size = fontSize;
        fontParameters.color = color;
        fontParameters.borderWidth = 1;
        fontParameters.borderColor = Color.BLACK;
        fontParameters.borderStraight = false;
        fontParameters.minFilter = TextureFilter.Linear;
        fontParameters.magFilter = TextureFilter.Linear;

        return fontGenerator.generateFont(fontParameters);
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

    public LabelStyle getLabelStyleBig() {
        return labelStyleBig;
    }

    public TextButtonStyle getTxtBtnStyle() {
        return txtBtnStyle;
    }

    public TextButtonStyle getTxtBtnStyleBig() {
        return txtBtnStyleBig;
    }
    
    public TextButtonStyle getTxtBtnStyleSmall() {
        return txtBtnStyleLittle;
    }

    public TextFieldStyle getTxtFldStyle(){return txtFldStyle;}
    
}
