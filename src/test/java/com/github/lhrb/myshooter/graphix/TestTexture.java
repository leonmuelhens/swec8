/**
 * 
 */
package com.github.lhrb.myshooter.graphix;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author exa
 *
 */
public class TestTexture {

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
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for {@link com.github.lhrb.myshooter.graphix.Texture#Texture(int, int, int, int)}.
     */
    @Test
    public void testTexture() {
        Texture test = new Texture(1,2,3,4);
        assertTrue(!test.equals(null));
        assertTrue(test.getImgWidth() == 3);
        assertTrue(test.getImgHeight() == 4);
    }

    /**
     * Test method for {@link com.github.lhrb.myshooter.graphix.Texture#getImgWidth()}.
     */
    @Test
    public void testGetImgWidth() {
        assertTrue((new Texture(0,0,1,0).getImgWidth()) == 1);
    }

    /**
     * Test method for {@link com.github.lhrb.myshooter.graphix.Texture#getImgHeight()}.
     */
    @Test
    public void testGetImgHeight() {
        assertTrue((new Texture(0,0,0,1).getImgHeight()) == 1);
    }

}
