/**
 * 
 */
package com.github.lhrb.myshooter.graphix;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author exa
 *
 */
public class TestTextureRegion {
    Texture mockedTexture;
    TextureRegion testTR;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        mockedTexture = mock(com.github.lhrb.myshooter.graphix.Texture.class);
        when(mockedTexture.getHeight()).thenReturn(128);
        when(mockedTexture.getWidth()).thenReturn(64);
        testTR = new TextureRegion(mockedTexture,0,1,2,3);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        testTR = null;
    }

    /**
     * Test method for {@link com.github.lhrb.myshooter.graphix.TextureRegion#TextureRegion(com.github.lhrb.myshooter.graphix.Texture)}.
     */
    @Test
    public void testTextureRegionTexture() {
        try {
            new TextureRegion(null);
            fail("Exception not thrown");
        }catch(Exception e) {
            assertTrue(e instanceof NullPointerException);
        }
        TextureRegion testTR1 = new TextureRegion(mockedTexture);
        assertTrue(testTR1.getOriginX() == 0);
        assertTrue(testTR1.getOriginY() == 0);
        assertTrue(testTR1.getRegionHeight() == 128);
        assertTrue(testTR1.getRegionWidth() == 64);
        
    }
    /**
     * Test method for {@link com.github.lhrb.myshooter.graphix.TextureRegion#TextureRegion(com.github.lhrb.myshooter.graphix.Texture)}.
     */
    @Test
    public void testTextureRegionTexture2() {
        TextureRegion testTR1 = new TextureRegion(mockedTexture);
        assertTrue(testTR1.getOriginX() == 0);
        assertTrue(testTR1.getOriginY() == 0);
        assertTrue(testTR1.getRegionHeight() == 128);
        assertTrue(testTR1.getRegionWidth() == 64);
        
    }
    

    /**
     * Test method for {@link com.github.lhrb.myshooter.graphix.TextureRegion#TextureRegion(com.github.lhrb.myshooter.graphix.Texture, int, int, int, int)}.
     */
    @Test
    public void testTextureRegionTextureIntIntIntInt() {
        try {
            new TextureRegion(null, 0,0,0,0);
            fail("Exception not thrown");
        }catch(Exception e) {
            assertTrue(e instanceof NullPointerException);
        }
        TextureRegion testTR = new TextureRegion(mockedTexture,0,1,2,3);
        assertTrue(testTR.getTexture().equals(mockedTexture));
        assertTrue(testTR.getOriginX() == 0);
        assertTrue(testTR.getOriginY() == 1);
        assertTrue(testTR.getRegionWidth() == 2);
        assertTrue(testTR.getRegionHeight() == 3);
    }
    
    /**
     * Test method for {@link com.github.lhrb.myshooter.graphix.TextureRegion#TextureRegion(com.github.lhrb.myshooter.graphix.Texture, int, int, int, int)}.
     */
    @Test
    public void testTextureRegionTextureIntIntIntInt2() {
        TextureRegion testTR = new TextureRegion(mockedTexture,1,2,3,4);
        assertTrue(testTR.getTexture().equals(mockedTexture));
        assertTrue(testTR.getOriginX() == 1);
        assertTrue(testTR.getOriginY() == 2);
        assertTrue(testTR.getRegionWidth() == 3);
        assertTrue(testTR.getRegionHeight() == 4);
    }

    /**
     * Test method for {@link com.github.lhrb.myshooter.graphix.TextureRegion#getTexture()}.
     */
    @Test
    public void testGetTexture() {
        assertTrue(testTR.getTexture().equals(mockedTexture));
    }

    /**
     * Test method for {@link com.github.lhrb.myshooter.graphix.TextureRegion#setTexture(com.github.lhrb.myshooter.graphix.Texture)}.
     */
    @Test
    public void testSetTexture() {
        Texture mockedTexture2 = mock(com.github.lhrb.myshooter.graphix.Texture.class);
        assertTrue(!testTR.getTexture().equals(mockedTexture2));
        testTR.setTexture(mockedTexture2);
        assertTrue(testTR.getTexture().equals(mockedTexture2));
    }

    /**
     * Test method for {@link com.github.lhrb.myshooter.graphix.TextureRegion#getOriginX()}.
     */
    @Test
    public void testGetOriginX() {
        assertTrue(testTR.getOriginX() == 0);
    }

    /**
     * Test method for {@link com.github.lhrb.myshooter.graphix.TextureRegion#setOriginX(int)}.
     */
    @Test
    public void testSetOriginX() {
        testTR.setOriginX(10);
        assertTrue(testTR.getOriginX() == 10);
    }

    /**
     * Test method for {@link com.github.lhrb.myshooter.graphix.TextureRegion#getOriginY()}.
     */
    @Test
    public void testGetOriginY() {
        assertTrue(testTR.getOriginY() == 1);
    }

    /**
     * Test method for {@link com.github.lhrb.myshooter.graphix.TextureRegion#setOriginY(int)}.
     */
    @Test
    public void testSetOriginY() {
        testTR.setOriginY(10);
        assertTrue(testTR.getOriginY() == 10);
    }

    /**
     * Test method for {@link com.github.lhrb.myshooter.graphix.TextureRegion#getRegionWidth()}.
     */
    @Test
    public void testGetRegionWidth() {
        assertTrue(testTR.getRegionWidth() == 2);
    }

    /**
     * Test method for {@link com.github.lhrb.myshooter.graphix.TextureRegion#setRegionWidth(int)}.
     */
    @Test
    public void testSetRegionWidth() {
        testTR.setRegionWidth(30);
        assertTrue(testTR.getRegionWidth() == 30);
    }

    /**
     * Test method for {@link com.github.lhrb.myshooter.graphix.TextureRegion#getRegionHeight()}.
     */
    @Test
    public void testGetRegionHeight() {
        assertTrue(testTR.getRegionHeight() == 3);  
    }

    /**
     * Test method for {@link com.github.lhrb.myshooter.graphix.TextureRegion#setRegionHeight(int)}.
     */
    @Test
    public void testSetRegionHeight() {
        testTR.setRegionHeight(20);
        assertTrue(testTR.getRegionHeight() == 20);
    }

}
