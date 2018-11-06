/**
 * 
 */
package com.github.lhrb.myshooter.graphix;

import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_REPEAT;
import static org.lwjgl.opengl.GL11.GL_RGBA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_S;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_T;
import static org.lwjgl.opengl.GL11.GL_UNPACK_ALIGNMENT;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL11.glPixelStorei;
import static org.lwjgl.opengl.GL11.glTexImage2D;
import static org.lwjgl.opengl.GL11.glTexParameteri;
import static org.lwjgl.opengl.GL46.GL_QUADS;
import static org.lwjgl.opengl.GL46.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL46.glBegin;
import static org.lwjgl.opengl.GL46.glBindTexture;
import static org.lwjgl.opengl.GL46.glEnd;
import static org.lwjgl.opengl.GL46.glPopMatrix;
import static org.lwjgl.opengl.GL46.glPushMatrix;
import static org.lwjgl.opengl.GL46.glTexCoord2f;
import static org.lwjgl.opengl.GL46.glTranslatef;
import static org.lwjgl.opengl.GL46.glVertex2f;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;

import de.matthiasmann.twl.utils.PNGDecoder;


/**
 * @author Lukas Block
 * @version 0.1
 * Simple data class, that holds texture informations 
 * 
 * Contains atm some test methods that dont belong in this class
 */
public class Texture {
    
    private final int target = GL_TEXTURE_2D;
    
    // OpenGL ID
    private final int texID;
    
    private int width;
    private int height;
    
    /**
     * Constructs a new Texture from a given path that has to point 
     * to a png image
     * @param filePath path to a png image
     * @throws IllegalArgumentException gets thrown if either filePath is null or has length 0 
     * @throws MalformedURLException gets thrown if URL construction from filePath failed 
     * @throws IOException thrown either by stream or pngdecoder class
     */
    public Texture(String filePath) throws IllegalArgumentException,
                                              MalformedURLException,
                                                         IOException{
        if(filePath == null | filePath.length() == 0) {
            throw new IllegalArgumentException("The given path is not valid");
        }
        try {
            URL pngFile = new File(filePath).toURI().toURL();
            
            final int bpp = 4; // bytes per pixel => RGBA format
            ByteBuffer byteBuffer;
            
            //try with resources
            try(InputStream input = pngFile.openStream()){
                PNGDecoder decoder = new PNGDecoder(input);
                
                width = decoder.getWidth();
                height = decoder.getHeight();
                
                byteBuffer = BufferUtils.createByteBuffer(bpp * width * height);
                decoder.decode(byteBuffer, width * bpp, PNGDecoder.Format.RGBA);
                input.close();
            }catch(IOException e) {
                throw e;
            }
            
            //flip buffer into read mode 
            byteBuffer.flip();
            
            //unnecessary ?
            glEnable(target);
            
            texID = glGenTextures();
            
            bind();
            
            glPixelStorei(GL_UNPACK_ALIGNMENT, 1);

            /** 
             * set texture parameters
             * https://www.khronos.org/registry/OpenGL-Refpages/gl4/html/glTexParameter.xhtml
             */
            glTexParameteri(target, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
            glTexParameteri(target, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
            glTexParameteri(target, GL_TEXTURE_WRAP_S, GL_REPEAT);
            glTexParameteri(target, GL_TEXTURE_WRAP_T, GL_REPEAT);
            
            /**
             * pass to OpenGL
             * https://www.khronos.org/registry/OpenGL-Refpages/gl4/html/glTexImage2D.xhtml    
             */
             glTexImage2D(target, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, byteBuffer);   
            
            
        }catch(MalformedURLException e) {
            //TO-DO costum exception?
            throw e;
        }
        
    }
    
    /*
     * Binds opengl context to the texture
     */
    public void bind() {
        glBindTexture(target, texID);
    }
    

    /*
     * this code is for test purposes and not meant to stay here
     */
    public void debugTex(int x, int y) {
        
        glPushMatrix();
        glBindTexture(GL_TEXTURE_2D, texID);
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
