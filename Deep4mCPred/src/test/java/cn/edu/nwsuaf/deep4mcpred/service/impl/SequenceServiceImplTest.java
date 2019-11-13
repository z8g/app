package cn.edu.nwsuaf.deep4mcpred.service.impl;

import cn.edu.nwsuaf.deep4mcpred.service.SequenceServiceImpl;
import cn.edu.nwsuaf.deep4mcpred.model.InputData;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author zhaoxuyang
 */
public class SequenceServiceImplTest {

    public SequenceServiceImplTest() {
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
     * Test of run method, of class SequenceServiceImpl.
     */
//    @Test
//    public void testRun() {
//        System.out.println("run");
//        List<InputData> inputDataList = null;
//        Integer category = null;
//        SequenceServiceImpl instance = new SequenceServiceImpl();
//        List<InputData> expResult = null;
//        List<InputData> result = instance.run(inputDataList, category);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of getExample method, of class SequenceServiceImpl.
     */
    @Test
    public void testGetExample() {
        System.out.println("getExample");
        Integer id = 1;
        SequenceServiceImpl instance = new SequenceServiceImpl();
        String expResult = ">P_2\nGAAGAAGTAGGCCATTATCTCGAATGAGCCAAACTAGTATTGAAGAAGTAGGCCATTATCTCGAATGAGCCAAACTAGTATT";
        String result = instance.getExample(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of getInputDataListFromString method, of class SequenceServiceImpl.
     */
    @Test
    public void testGetInputDataListFromString() {
        System.out.println("getInputDataListFromString");

//        String sequenceList = ">P_1\n"
//                + "GAAGAAGTAGGCCATTATCTCGAATGAGCCAAACTAGTATTGAAGAAGTAGGCCATTATCTCGAATGAGCCAAACTAGTATT\n"
//                + ">P_2\n"
//                + "GAAGAAGTAGGCCATTATCTCGAATGAGCCAAACTAGTATTGAAGAAGTAGGCCATTATCTCGAATGAGCCAAACTAGTATT\n"
//                + ">P_3\n"
//                + "GAAGAAGTAGGCCATTATCTCGAATGAGCCAAACTAGTATTGAAGAAGTAGGCCATTATCTCGAATGAGCCAAACTAGTATT\n"
//                + ">P_4\n"
//                + "GAAGAAGTAGGCCATTATCTCGAATGAGCCAAACTAGTATTGAAGAAGTAGGCCATTATCTCGAATGAGCCAAACTAGTATT\n"
//                + ">P_5\n"
//                + "GAAGAAGTAGGCCATTATCTCGAATGAGCCAAACTAGTATTGAAGAAGTAGGCCATTATCTCGAATGAGCCAAACTAGTATT\n"
//                + ">P_6\n"
//                + "GAAGAAGTAGGCCATTATCTCGAATGAGCCAAACTAGTATTGAAGAAGTAGGCCATTATCTCGAATGAGCCAAACTAGTATT\n";


String sequenceList = ">P_1\n"
                + "GAAGAAGTAGGCCATTATCTCGAATGAGCCAAACTAGTATTGAAGAAGTAGGCCATTATCTCGAATGAGCCAAACTAGTATT"
             ;
        System.out.println(sequenceList);
        SequenceServiceImpl instance = new SequenceServiceImpl();
        List<InputData> result = instance.getInputDataListFromString(sequenceList);
        System.out.println(result);
        assertEquals(true, !result.isEmpty());
    }

    /**
     * Test of getInputDataListFromFile method, of class SequenceServiceImpl.
     */
//    @Test
//    public void testGetInputDataListFromFile() {
//        System.out.println("getInputDataListFromFile");
//        String filePath = "/home/zhaoxuyang/Desktop/testInput.txt";
//        SequenceServiceImpl instance = new SequenceServiceImpl();
//        List<InputData> result = instance.getInputDataListFromFile(filePath);
//        System.out.println(result);
//        assertEquals(true, !result.isEmpty());
//        
//    }

}
