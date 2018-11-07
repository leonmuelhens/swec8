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
     * Test method for {@link com.github.lhrb.myshooter.graphix.Texture#Texture(String)}.
     */
    @Test
    public void testTexture() {
        try {
            Texture testT = new Texture(null);
            fail("Exception not thrown");
        }catch(Exception e) {
           assertTrue(e instanceof NullPointerException); 
        }
        
        try {
            Texture testT = new Texture("");
            fail("Exception not thrown");
        }catch(Exception e) {
           assertTrue(e instanceof IllegalArgumentException); 
        }
        
        try {
            Texture testT = new Texture("/");
            fail("Exception not thrown");
        }catch(Exception e) {
           assertTrue(e instanceof IOException); 
        }
        
    }
    

}
