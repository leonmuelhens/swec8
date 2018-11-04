/**
 * 
 */
package com.github.lhrb.myshooter.graphix;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;

/**
 * @author Lukas Block
 * @version 0.1
 * Simple data class, that holds texture informations 
 * 
 * Contains atm some test methods that dont belong in this class
 */
public class Texture {
    
    //opengl target generally GL_TEXTURE_2D
    private int target;
    
    // OpenGL ID
    private int textureId;
    
    //width of the tex image
    private int imgWidth;
    
    //height of the tex image
    private int imgHeight;
    
    /**
     * Constructs a new Texture with given parameters
     * @param target the opengl target 
     * @param textureId the opengl id
     * @param imgWidth widht of the image
     * @param imgHeight height of the image
     */
    public Texture(int target, int textureId, int imgWidth, int imgHeight) {
        this.target = target;
        this.textureId = textureId;
        this.imgWidth = imgWidth;
        this.imgHeight = imgHeight;
    }
    
    /*
     * Binds opengl context to the texture
     */
    public void bind() {
        glBindTexture(target, textureId);
    }
    
    /**
     * @return the image width
     */
    public int getImgWidth() {
        return imgWidth;
    }

    /**
     * @return the image height
     */
    public int getImgHeight() {
        return imgHeight;
    }
    
    
    /*
     * this code is for test purposes and not meant to stay here
     */
    public void draw(int x, int y) {
        int width = imgWidth;
        int height = imgHeight;
        
        glPushMatrix();
        glBindTexture(GL_TEXTURE_2D, textureId);
        glTranslatef(x,y,0);
        glBegin(GL_QUADS);
        {
            glTexCoord2f(0,0);
            glVertex2f(0,0);
            
            glTexCoord2f(0, height);
            glVertex2f(0, height);
            
            glTexCoord2f(width,height);
            glVertex2f(width,height);
            
            glTexCoord2f(width,0);
            glVertex2f(width,0);            
        }
        glEnd();
        glPopMatrix();      
        
    }



}
