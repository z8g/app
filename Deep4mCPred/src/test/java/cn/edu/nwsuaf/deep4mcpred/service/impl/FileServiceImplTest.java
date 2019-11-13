///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package cn.edu.nwsuaf.deep4mcpred.service.impl;
//
//import cn.edu.nwsuaf.deep4mcpred.model.OutputData;
//import java.util.List;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import org.springframework.web.multipart.MultipartFile;
//
///**
// *
// * @author zhaoxuyang
// */
//public class FileServiceImplTest {
//    
//    public FileServiceImplTest() {
//    }
//    
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
//    
//    @Before
//    public void setUp() {
//    }
//    
//    @After
//    public void tearDown() {
//    }
//
//    /**
//     * Test of saveSequenceToFile method, of class FileServiceImpl.
//     */
//    @Test
//    public void testSaveSequenceToFile() {
//        System.out.println("saveSequenceToFile");
//        String sequence = "123456";
//        String jobId = "12";
//        FileServiceImpl instance = new FileServiceImpl();
//        boolean expResult = true;
//        boolean result = instance.saveSequenceToFile(sequence, jobId);
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of saveFile method, of class FileServiceImpl.
//     */
//    @Test
//    public void testSaveFile() {
//        System.out.println("saveFile");
//        String jobId = "";
//        MultipartFile uploadFile = null;
//        FileServiceImpl instance = new FileServiceImpl();
//        boolean expResult = false;
//        boolean result = instance.saveFile(jobId, uploadFile);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of readOutputFile method, of class FileServiceImpl.
//     */
//    @Test
//    public void testReadOutputFile() {
//        System.out.println("readOutputFile");
//        String jobId = "";
//        FileServiceImpl instance = new FileServiceImpl();
//        String expResult = "";
//        String result = instance.readOutputFile(jobId);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getOutputDataList method, of class FileServiceImpl.
//     */
//    @Test
//    public void testGetOutputDataList() {
//        System.out.println("getOutputDataList");
//        String jobId = "";
//        FileServiceImpl instance = new FileServiceImpl();
//        List<OutputData> expResult = null;
//        List<OutputData> result = instance.getOutputDataList(jobId);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
//}
