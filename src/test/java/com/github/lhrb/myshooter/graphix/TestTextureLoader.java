/**
 * 
 */
package com.github.lhrb.myshooter.graphix;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author exa
 *
 */
public class TestTextureLoader {

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
     * Test method for {@link com.github.lhrb.myshooter.graphix.TextureLoader#TextureLoader()}.
     */
    @Test
    public void testTextureLoader() {
        try {
            TextureLoader.loadImage(null);
            fail("Exception not thrown");
        }catch(IOException e) {
            //bad
            assertTrue(e != null);
        }
 
    }

    /**
     * Test method for {@link com.github.lhrb.myshooter.graphix.TextureLoader#loadImage(java.lang.String)}.
     */
    @Test
    public void testLoadImage() {
        fail("Not yet implemented");
    }

}
