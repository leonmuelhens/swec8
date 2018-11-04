/**
 * 
 */
package com.github.lhrb.myshooter.graphix;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import javax.imageio.ImageIO;
import org.lwjgl.BufferUtils;
import static org.lwjgl.opengl.GL11.*;

/**
 * @author Lukas Block
 * @version 0.1
 * 
 * This is a protype and by all means not rdy to use
 * 
 * Loads an image and decodes it into a RGBA byte array 
 *
 */
public class TextureLoader {
    
    public TextureLoader() {
        
    }
    
    /**
     * Loads a resource from a given path and binds it to opengl
     * returns a texture associated with that image
     * @param filePath to the resource 
     * @return a new Texture
     * @throws IOException if resource path is not found
     */
    public static Texture loadImage(String filePath) throws IOException {
        final int bpp = 4; // bytes per pixel => RGBA format
        BufferedImage img;
        try {
            img = ImageIO.read(new File(filePath));         
            
        }catch(IOException e) {
            System.out.println("Not able to read the file path");
            // throw new CostumException() ?
            throw new IOException(e);
        }
        if(img.getType() != BufferedImage.TYPE_4BYTE_ABGR) {
            // needs some tweaks 
            img = new TextureLoader().convertToRgba(img);
        }
        
        ByteBuffer byteBuffer = BufferUtils.createByteBuffer(bpp * img.getWidth() * img.getHeight());
        byte[] pixels = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
        for(byte b : pixels) {
            byteBuffer.put(b);
        }
        
        //flip buffer into read mode 
        byteBuffer.flip();
        
        
        glEnable(GL_TEXTURE_2D);
        int texId = glGenTextures();
                
        return new Texture(GL_TEXTURE_2D, texId, img.getWidth(), img.getHeight());
    }
        
    /**
     * Converts a buffered Image into 4 byte rgba representation
     * @param img the image to convert
     * @return a buffered image 4byte rgba format
     */
    private BufferedImage convertToRgba(BufferedImage img) {
        BufferedImage rgba = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        rgba.createGraphics().drawImage(img, 0, 0, img.getWidth(), img.getHeight(), null);
        return rgba;
    }
   

}
