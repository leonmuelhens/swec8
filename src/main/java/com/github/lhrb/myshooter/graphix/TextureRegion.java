/**
 * 
 */
package com.github.lhrb.myshooter.graphix;

/**
 * @author exa
 * @version 0.1
 */
public class TextureRegion {
    
    private Texture texture;
    
    private int originX;
    private int originY;
    private int regionWidth;
    private int regionHeight;
    
    /**
     * Constructs a texture region from given texture with the 
     * origins 0,0 and height and width are the same as the texture 
     * height and width
     * @param texture 
     */
    public TextureRegion(Texture texture) {
        if(texture == null) {
            throw new IllegalArgumentException("Texture can not be null");
        }
        this.texture = texture;
        this.originX = 0;
        this.originY = 0;
        this.regionHeight = texture.getHeight();
        this.regionWidth = texture.getWidth();
    }
    
    /**
     * Constructs a texture region from a given texture, the origin defined by the 
     * given arguments, and height and width defined by the given arguments
     * @param texture 
     * @param originX origin of the x coordinate
     * @param originY origin of the y coordinate
     * @param width of the region
     * @param height of the region
     */
    public TextureRegion(Texture texture, int originX, int originY, int width, int height) {
        if(texture == null) {
            throw new IllegalArgumentException("Texture can not be null");
        }
        this.texture = texture;
        this.originX = originX;
        this.originY = originY;
        this.regionWidth = width;
        this.regionHeight = height;
    }

    /**
     * @return the texture
     */
    public Texture getTexture() {
        return texture;
    }

    /**
     * @param texture the texture to set
     */
    public void setTexture(Texture texture) {
        if(texture == null) return;
        this.texture = texture;
    }

    /**
     * @return the originX
     */
    public int getOriginX() {
        return originX;
    }

    /**
     * @param originX the originX to set
     */
    public void setOriginX(int originX) {
        this.originX = originX;
    }

    /**
     * @return the originY
     */
    public int getOriginY() {
        return originY;
    }

    /**
     * @param originY the originY to set
     */
    public void setOriginY(int originY) {
        this.originY = originY;
    }

    /**
     * @return the regionWidth
     */
    public int getRegionWidth() {
        return regionWidth;
    }

    /**
     * @param regionWidth the regionWidth to set
     */
    public void setRegionWidth(int regionWidth) {
        this.regionWidth = regionWidth;
    }

    /**
     * @return the regionHeight
     */
    public int getRegionHeight() {
        return regionHeight;
    }

    /**
     * @param regionHeight the regionHeight to set
     */
    public void setRegionHeight(int regionHeight) {
        this.regionHeight = regionHeight;
    }
    
}
