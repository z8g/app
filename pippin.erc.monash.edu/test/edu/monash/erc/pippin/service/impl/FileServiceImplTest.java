/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.monash.erc.pippin.service.impl;

import edu.monash.erc.pippin.model.Protein;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Administrator
 */
public class FileServiceImplTest {
    
    public FileServiceImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }


    /**
     * Test of saveStringToFile method, of class FileServiceImpl.
     */
    @Test
    public void testSaveStringToFile() {
        System.out.println("saveStringToFile");
        String jobId = "";
        String content = "";
        FileServiceImpl instance = new FileServiceImpl();
        boolean expResult = false;
        boolean result = instance.saveStringToFile(jobId, content);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createFolder method, of class FileServiceImpl.
     */
    @Test
    public void testCreateFolder() {
        System.out.println("createFolder");
        String jobId = "";
        FileServiceImpl instance = new FileServiceImpl();
        boolean expResult = false;
        boolean result = instance.createFolder(jobId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveFile method, of class FileServiceImpl.
     */
    @Test
    public void testSaveFile() {
        System.out.println("saveFile");
        String jobId = "";
        MultipartFile uploadFile = null;
        FileServiceImpl instance = new FileServiceImpl();
        boolean expResult = false;
        boolean result = instance.saveFile(jobId, uploadFile);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeFolder method, of class FileServiceImpl.
     */
    @Test
    public void testRemoveFolder() {
        System.out.println("removeFolder");
        String jobId = "";
        FileServiceImpl instance = new FileServiceImpl();
        boolean expResult = false;
        boolean result = instance.removeFolder(jobId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProteinListFromOutputFile method, of class FileServiceImpl.
     */
    @Test
    public void testGetOutputFile() {
        System.out.println("getOutputFile");
        String jobId = "user00002";
        FileServiceImpl instance = new FileServiceImpl();
       
        List<Protein> result = instance.getProteinListFromOutputFile(jobId);
        System.out.println(result);
        assertEquals(true, result!=null && !result.isEmpty());
    }
    
}
