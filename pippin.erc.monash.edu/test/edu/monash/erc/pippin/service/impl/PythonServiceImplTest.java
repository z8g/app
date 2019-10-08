/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.monash.erc.pippin.service.impl;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lr
 */
public class PythonServiceImplTest {
    
    public PythonServiceImplTest() {
    }

    /**
     * Test of run method, of class PythonServiceImpl.
     */
    @Test
    public void testRun() {
        System.out.println("run");
        String jobId = "user00003";
        PythonServiceImpl instance = new PythonServiceImpl();
        boolean expResult = true;
        boolean result = instance.run(jobId);
        System.out.println("结果:"+result);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }
    
}
